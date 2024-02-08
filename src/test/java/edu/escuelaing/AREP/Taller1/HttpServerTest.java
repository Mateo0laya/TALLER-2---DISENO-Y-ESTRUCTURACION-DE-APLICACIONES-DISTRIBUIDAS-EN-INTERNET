package edu.escuelaing.AREP.Taller1;

import static org.junit.Assert.*;

import org.junit.Test;


public class HttpServerTest {
    
    @Test
    public void shoukdreturnMovie() {
        String uriString = "/hello?name=Avatar";
        String expectedOutputStart = "HTTP/1.1 200 OK\r\nContent-Type:text/html\r\n\r\n";
        String actualOutput = HttpServer.searchMovie(uriString);
        assertTrue(actualOutput.startsWith(expectedOutputStart));
    }

    @Test
    public void shouldReturnError() {
        HttpServer httpServer = new HttpServer();

        String result = httpServer.httpError();
        assertNotNull(result);
        assertTrue(result.contains("400 Not Found"));
        assertTrue(result.contains("<title>Error Not Found</title>"));
    }

    @Test
    public void shouldreturnHeaderofText() {
        String fileType = "txt";
        String expectedHeader = "HTTP/1.1 200 OK\r\nContent-Type:text/txt\r\n\r\n";
        String actualHeader = HttpServer.getHeader(fileType);
        assertEquals(expectedHeader, actualHeader);
    }

    @Test
    public void shouldReturnHeaderofImage() {
        String fileType = "jpg";
        String expectedHeader = "HTTP/1.1 200 OK\r\nContent-Type:image/jpg\r\n\r\n";
        String actualHeader = HttpServer.getHeader(fileType);
        assertEquals(expectedHeader, actualHeader);
    }
}
