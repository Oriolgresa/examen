<%--
  Created by IntelliJ IDEA.
  User: OriolGresa
  Date: 23/11/2016
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="cookie.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

<form>
    <div class="form-group">
        <label>Nom:</label>
        <input id="nom" type="text" name="nom">
    </div>
    <div class="form-group">
        <label>Descripcio:</label>
        <input id="descripcio" type="text" name="descripcio">
    </div>
    <div class="form-group">
        <label>Tipus:</label>
        <input id="tipus" type="text" name="tipus">
    </div>

    <input id="newEtakemon" type="submit" value="Afegir etakemon" class="btn btn-default">
</form>
Etakemons:<br>
<div id="lista" class="container">

</div>

<script>
    $(document).ready(function() {
        $.get("/etakemon?" + Cookies.get('name'), function (res) {
            console.log(res);
        });
        $("#newEtakemon").click(function (e) {
            e.preventDefault();
            var etakemon = {
                usuario: Cookies.get('name'),
                nom: $("#nom").val(),
                descripcio: $("#descripcio").val(),
                tipus: $("#tipus").val()
            }
            $.post("/etakemon", etakemon, function (res) {
                $.get("/etakemon?" + Cookies.get('name'), function (res) {
                    console.log(res);
                    var html = "<table class=\"table table-condensed\"><tr><th>nom</th><th>descripcio</th><th>tipus</th></tr>"
                    var r = res.split(":")
                    for(var i = 0; i < r.length -1; i++){
                        var p = r[i].split(",");
                        html += "<tr><td>" + p[0] + "</td><td>" + p[1] + "</td><td>" + p[2] + "</td></tr>";
                    }
                    html += "</table>";
                    $("#lista").html(html);
                });
            });
        })
    });
</script>
</body>
</html>
