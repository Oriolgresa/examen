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
    <title>Part2</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="cookie.js"></script>
</head>
<body>
<form action="/login" method="post">
    <div class="form-group">
        <label>User:</label>
        <input id="user" type="text" name="user">
    </div>
    <div class="form-group">
        <label>Pass:</label>
        <input type="text" name="password">
    </div>

    <input type="submit" value="Login" class="btn btn-default">
</form>
<script>
    $(document).ready(function () {
        $("#user").on("change paste keyup", function () {
            Cookies.set('name', $(this).val());
        })
    });
</script>
</body>
</html>
