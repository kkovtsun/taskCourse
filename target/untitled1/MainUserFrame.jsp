<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="/resources/style.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <title>SPD</title>
    <style>
        body{background-color: #c5ddff;background-image: url("static/images/i1.gif");}
        #btnLogOut:hover, #btnSearch:hover {background-color: #3c4a90;color: white;}
        #btnLogOut, #btnSearch{
            color: black;padding: 3px 14px;text-align: center;
            text-decoration: none;display: inline-block;
            font-size: 16px;border: 2px solid #3c4a90;
            background-color: #ffffff;cursor: pointer;}
        #btnLogOut{margin-left: 1236px;margin-top: 9px;}
        #divSearchC{margin-top: 7px;margin-left: 225px;}
        #panel{width: 99.7%;height: 47px;background: #fff5b2;border-style: groove;}
        #yearSearch{font-size: 23px;margin-left: -113px;margin-top: 4px;}
        #deptSearch{font-size: 23px;margin-left: 40px;margin-top: 2px;}
        #groupSearch{margin-left: 45px;margin-top: 3px;font-size: 23px;}
        #txtYearSearch{margin-left: -50px;margin-top: -48px;cursor: pointer;
            border: 2px solid #9a9a9a;font-size: 15px;}
        #txtDeprSearch{width: 100px;margin-left: 200px;
            margin-top: -48px;cursor: pointer;border: 2px solid #9a9a9a;
            font-size: 15px;}
        #txtGroupSearch{width: 100px;margin-left: 162px;
            margin-top: -48px;cursor: pointer;border: 2px solid #9a9a9a;
            font-size: 15px;}
        tr:hover{color: #3c4a90;}
        th, td {text-align: left; padding: 3px;}
        tr:nth-child(even){background-color: #f2f2f2}
        #txtYearSearch:hover, #txtDeprSearch:hover, #txtGroupSearch:hover {border-color:  #221c70;}
        #btnSearch{margin-left: 148px;margin-top: -22px;}
        #divSearch{height: 45px;}
        #listSearch{color: #221c70;font-size: 21px;margin-left: 37px;}
        #listGroups{color: #221c70;font-size: 21px;margin-left: 218px;}
        #listDepartment{color: #221c70;font-size: 21px;margin-left: 24px;}
        .title{font-size: 18px;color: rgb(22, 18, 74);}
        #tblS{width: 35%;float: left;}
        #tblSI{margin-left: 11px;margin-top: 14px;margin-bottom: 16px;}
        #tblG{float: left;width: 44%;}
        #tblGI{margin-top: 14px; margin-left: 26px;margin-bottom: 16px;}
        #tblD{float: left;width: 19%;}
        #tblDI{margin-left: 50px;margin-top: 13px;margin-bottom: 16px;}
        #tblSM{margin-bottom:16px;margin-top: 16px;
            border-style: groove;background: white;
            width: 435px;margin-left: 15px;}
        #tblDM{margin-top: 16px;margin-bottom:16px; width: 96%;
            border-style: groove;margin-left: 16px;background: white;}
        #tblGM{margin-top: 16px;margin-bottom:16px; background: white;
            border-style: groove;}
    </style>
</head>
<body>
<form action="<c:url value="/main"/>" method="POST">
    <div id="panel">
        <input id="btnLogOut" type="submit" name="logOut" value="Log Out"/>
    </div>
    <div id="divSearch">
        <div id="divSearchC">
            <table>
            <tr>
                <td><p id="yearSearch">Year:</p><input type="text" id="txtYearSearch" name="year" value="${form.year}"/><br/></td>
                <td><p id="deptSearch">Department list:</p>
                    <select id="txtDeprSearch" name="departmentId">
                        <c:forEach var="department" items="${form.departments}">
                            <c:choose>
                                <c:when test="${department.departmentId==form.departmentId}">
                                    <option value="${department.departmentId}" selected><c:out value="${department.nameDept}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${department.departmentId}"><c:out value="${department.nameDept}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                <td><p id="groupSearch">Group list:</p>
                    <select id="txtGroupSearch" name="groupId">
                        <c:forEach var="group" items="${form.groups}">
                            <c:choose>
                                <c:when test="${group.groupId==form.groupId}">
                                    <option value="${group.groupId}" selected><c:out value="${group.nameGroup}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td><input id="btnSearch" type="submit" name="getList" value="Search"/></td>
            </tr>
            </table>
        </div>
    </div>
    <div id="tblS">
        <div id="tblSM">
            <b id="listSearch">List of students for selected parameters:</b>
            <table id="tblSI">
            <tr>
                <th class="title">Lastname</th>
                <th class="title">Firstname</th>
                <th class="title">Patronymic</th>
                <th class="title">Date of birth</th>
                <th class="title">Sex</th>
            </tr>
            <c:forEach var="student" items="${form.students}">
                <tr>
                    <td><c:out value="${student.firstName}"/></td>
                    <td><c:out value="${student.surName}"/></td>
                    <td><c:out value="${student.patronymic}"/></td>
                    <td><c:out value="${student.dateOfBirth}"/></td>
                    <td><c:out value="${student.sex}"/></td>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
    <div id="tblG">
        <div id="tblGM">
            <b id="listGroups">List of all groups:</b>
            <table id="tblGI">
            <tr>
                <th class="title">Name</th>
                <th class="title">Curator</th>
                <th class="title">Spesiality</th>
                <th class="title">Haed spesiality</th>
            </tr>
            <c:forEach var="group" items="${form.groups}">
            <tr>
                <td><c:out value="${group.nameGroup}"/></td>
                <td><c:out value="${group.curator}"/></td>
                <td><c:out value="${group.speciality}"/></td>
                <td><c:out value="${group.speHead}"/></td>
            </tr>
            </c:forEach>
            </table>
        </div>
    </div>
    <div id="tblD">
        <div id="tblDM">
            <b id="listDepartment">List of all departments:</b>
            <table id="tblDI">
            <tr>
                <th class="title">Name</th>
                <th class="title">Head</th>
            </tr>
            <c:forEach var="department" items="${form.departments}">
                <tr>
                    <td><c:out value="${department.nameDept}"/></td>
                    <td><c:out value="${department.head}"/></td>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
</form>
</body>
</html>