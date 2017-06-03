<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <title>Registration new user</title>
    <style>
        body{background-color: #c5ddff;
            background-image: url("static/images/i1.gif");}
        #txt1{margin-left: 10px;cursor: pointer;}
        #txt2{margin-left: 15px;cursor: pointer;}
        #txt1:hover, #txt2:hover{border-color: #3e30cf;}
        #btn{margin-left: 133px;margin-top: -6px;color: black;
            padding: 3px 14px;text-align: center;
            text-decoration: none;display: inline-block;
            font-size: 16px;border: 2px solid #3c4a90;
            background-color: #ffffff;cursor: pointer;}
        #btn:hover{background-color: #3c4a90;color: white;}
        .lbl{font-size: 23px;margin-left: 38px;}
        #h1IndexTitleReg{
            text-shadow: 1px 1px 1px rgba(NaN,NaN,NaN,1);
            font-weight: bold;color: #221C70;
            letter-spacing: 1pt;word-spacing: 2pt;font-size: 32px;
            text-align: left;font-family: arial black, sans-serif;
            line-height: 1;padding-left: 45px;padding-top: 19px;
            margin-left: 336px;margin-top: 96px;}
        #statUser{
            margin-top: -43px;height: 28px;
            margin-left: 221px;width: 130px;font-size: 23px;}
        #mainDiv{margin: auto;
            margin-top: 38px;border-style: groove;height: 217px;
            background-color: white;width: 362px;}
        #lblError{color: red;margin-left: 597px;font-size: 23px;}
    </style>
</head>
<body>
<h1 id="h1IndexTitleReg">Please fill in the data fields</h1>
<form action="<c:url value="/registration"/>" method="POST">
    <div id="mainDiv">
        <p class="lbl">Username: <input id="txt1" type="text" name="usernameR"></p>
        <p class="lbl">Password: <input id="txt2"type="password" name="passwordR"></p>
        <p class="lbl">Status: <p id="statUser">User</p></p>
        <input id="btn" type="submit" name="signIn" value="Sign In" />
    </div>
</form>
<p id="lblError">${logIn}</p>
</body>
</html>
