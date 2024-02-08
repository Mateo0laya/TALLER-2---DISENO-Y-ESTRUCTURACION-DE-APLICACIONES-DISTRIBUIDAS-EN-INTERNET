function loadGetMsg() {
    let nameVar = document.getElementById("name").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let response = document.getElementById("getrespmsg");
        let jsonData = JSON.parse(this.responseText);
        let tableHtml = "<table border='1'>";
        for (let key in jsonData) {
            if (jsonData.hasOwnProperty(key)) {
                if (key == "Ratings" && Array.isArray(jsonData[key])) {
                    for (let i = 0; i < jsonData[key].length; i++) {
                        tableHtml += "<tr><td>" + jsonData[key][i].Source + "</td><td>" + jsonData[key][i].Value + "</td></tr>";
                    }
                } else {
                    tableHtml += "<tr><td>" + key + "</td><td>" + jsonData[key] + "</td></tr>";
                }
            }
        }
        tableHtml += "</table>";
        response.innerHTML = tableHtml;
    };
    xhttp.open("GET", "/hello?name=" + nameVar);
    xhttp.send();
}
