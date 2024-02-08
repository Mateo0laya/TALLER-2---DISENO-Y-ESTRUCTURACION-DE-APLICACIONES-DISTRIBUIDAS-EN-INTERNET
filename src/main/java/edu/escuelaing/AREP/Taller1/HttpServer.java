package edu.escuelaing.AREP.Taller1;

import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.io.*;

public class HttpServer {

    private static HashMap<String, String> cache = new HashMap<String, String>();
    private static HttpConnection httpConnection = new HttpConnection();
    public static void main(String[] args) throws IOException, URISyntaxException {
        
        ServerSocket serverSocket = null;

        int port = 35000;
        
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port + ".");
            System.exit(1);
        }
        
        Socket clientSocket = null;
        
        boolean running = true;
        
        while (running) {

            try {
                System.out.println("Ready to recive ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
    
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean firstLine = true;
            String uriStr = "";

            while ((inputLine = in.readLine()) != null) {
                if(firstLine){
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            try{
                outputLine = httpResponse(uriStr, clientSocket);
            } catch(Exception e) {
                e.printStackTrace();
                outputLine = httpError();
            }
            
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    private static String httpResponse(String uriStr, Socket clientSocket) throws IOException {
        String outputLine;
        if(uriStr.startsWith("/hello")){
            outputLine = searchMovie(uriStr);
        }else{
            String fileType = uriStr.split("\\.")[1];
            outputLine = getHeader(fileType);

            if(fileType.equals("jpg")){
                sendImage(uriStr, clientSocket, outputLine);
                outputLine = "";
            } else {
                outputLine = outputLine + sendText(uriStr);
            }

        }
        return outputLine;
    }

    private static String sendText(String uriStr) throws IOException {
        String outputLine = "";
        Path file = Paths.get("target/classes/public" + uriStr);

        Charset charset = Charset.forName("UTF-8");
        BufferedReader reader = Files.newBufferedReader(file, charset);
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            outputLine = outputLine + line; 
        }
        return outputLine;
    }

    private static void sendImage(String uriStr, Socket clientSocket, String header) throws IOException {
        OutputStream out = clientSocket.getOutputStream();
        out.write(header.getBytes());
        File file = new File("target/classes/public" + uriStr);
        Files.copy(file.toPath(), out);
        out.close();
    }    

    public static String getHeader(String fileType) {
        fileType = (fileType.equals("jpg")) ? "image/"+fileType : "text/"+fileType;
        String header = "HTTP/1.1 200 OK\r\n"
        + "Content-Type:" + fileType + "\r\n"
        + "\r\n";
        return header; 
    }

    public static String searchMovie(String uriString) {
        String moviesName = uriString.split("=")[1];
        String outputLine = "HTTP/1.1 200 OK\r\n"
        + "Content-Type:text/html\r\n"
        + "\r\n";
        if(cache.containsKey(moviesName)){
            outputLine = outputLine + cache.get(moviesName);
            System.out.println("Del caché");
        }else{
            try {
                outputLine = outputLine + httpConnection.query(moviesName);
                cache.put(moviesName, outputLine);
            } catch (IOException e) {
                outputLine = httpError();
                System.out.println("Error: " + e.getMessage());
            }
        }
        return outputLine;
    }

    public static String httpError() {
        String outputLine =  "HTTP/1.1 400 Not Found\r\n"
        + "Content-Type:text/html\r\n"
        + "\r\n"
        + "<!DOCTYPE html>\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <title>Error Not Found</title>\n" +
            "        <meta charset=\"UTF-8\">\n" +
            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <h1>Error</h1>\n" +
            "    </body>\n";
        return outputLine;
    }
}