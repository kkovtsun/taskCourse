<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <title>SPD</title>
    <style>
         body{background-color: #c5ddff;background-image: url("static/images/i1.gif");}
        .txt1{margin-left: 10px;cursor: pointer;}
        .txt2{margin-left: 15px;cursor: pointer;}
        .txt3{cursor: pointer;
            height: 28px;margin-left: 96px;width: 130px;
            border-color: blue;border-style: groove;}
        .lblError{color: red;margin-left: 597px;font-size: 23px;}
        .btn{margin-left: 133px;}
        .lbl{font-size: 23px;margin-left: 38px;}
        .h1IndexTitle{
            text-shadow: 1px 1px 1px rgba(NaN,NaN,NaN,1);
            font-weight: bold;color: #221C70;letter-spacing: 1pt;
            word-spacing: 2pt;font-size: 32px;text-align: left;
            font-family: arial black, sans-serif;line-height: 1;
            padding-left: 45px;padding-top: 19px;
            margin-left: 336px;margin-top: 96px;}
        .mainDiv{
            margin: auto;margin-top: 38px;
            border-style: groove;height: 217px;
            background-color: white;width: 362px;}
        .btn, .btnR{
            margin-top: -6px;color: black;
            padding: 3px 14px;text-align: center;
            text-decoration: none;display: inline-block;
            font-size: 16px;border: 2px solid #3c4a90;
            background-color: #ffffff;cursor: pointer;}
         .btn:hover, .btnR:hover {background-color: #3c4a90;color: white;}
    </style>
    <script>$(".btn").click(function(){href="${pageContext.request.contextPath}/main"});
    $(".btnR").click(function(){href="${pageContext.request.contextPath}/registration"});
    </script>
</head>
<body>
    <h1 class="h1IndexTitle">Student personnel department</h1>
    <form action="WelcomeServlet" method="post">
        <div class="mainDiv">
            <p class="lbl">Username: <input class="txt1" type="text" name="username"></p>
            <p class="lbl">Password: <input class="txt2"type="password" name="password"></p>
            <p class="lbl">Status:<select class="txt3"name = "status">
                <option value = "user">User</option>
                <option value = "moderator">Moderator</option>
                <option value = "admin">Admin</option>
            </select></p>
            <input class="btn" type="submit" name="logIn" value="Log In" />
            <input class="btnR" type="submit" name="aReg" value="Sign up" />
        </div>
    </form>
    <p class="lblError">${logIn}</p>
</body>
</html>
