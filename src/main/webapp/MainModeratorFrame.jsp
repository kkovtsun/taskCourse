<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="/resources/style.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <title>SPD</title>
    <style>
        body{background-color: #c5ddff;background-image: url("static/images/i1.gif");}
        #btnLogOut, #btnSearch, #btnMove, #btnAddGr, #btnEditGrFinaly, #btnEditGr, #btnDelGr,
        #btnAddSt, #btnEditSt, #btnDelSt, #btnEditStFinaly, #btnAddDp, #btnEditDp, #btnDelDp, #btnEditDpFinaly{
            color: black;padding: 3px 14px;text-align: center;
            text-decoration: none;display: inline-block;
            font-size: 16px;border: 2px solid #3c4a90;
            background-color: #ffffff;cursor: pointer;}
        #btnLogOut:hover, #btnSearch:hover, #btnMove:hover, #btnAddGr:hover, #btnEditGrFinaly:hover,
        #btnEditGr:hover, #btnDelGr:hover, #btnAddSt:hover, #btnEditSt:hover, #btnDelSt:hover,
        #btnEditStFinaly:hover, #btnAddDp:hover, #btnEditDp:hover, #btnDelDp:hover, #btnEditDpFinaly:hover {
            background-color: #3c4a90;color: white;}
        #btnLogOut{margin-left: 1236px;margin-top: 9px;}
        #divSearchC{margin-top: 7px;margin-left: 225px;}
        #panel{width: 99.7%;height: 47px;background: #fff5b2;border-style: groove;}
        #yearSearch{font-size: 23px;margin-left: -113px;margin-top: 4px;}
        #deptSearch{font-size: 23px;margin-left: 40px;margin-top: 2px;}
        #groupSearch{margin-left: 45px;margin-top: 3px;font-size: 23px;}
        #txtYearSearch{margin-left: -50px;margin-top: -48px;cursor: pointer;
            border: 2px solid #3c4a90;font-size: 15px;}
        #txtDeprSearch{width: 100px;margin-left: 200px;
            margin-top: -48px;cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;}
        #txtGroupSearch{width: 100px;margin-left: 162px;
            margin-top: -48px;cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;}
        #btnSearch{margin-left: 148px;margin-top: -22px;}
        #divSearch{height: 45px;}
        #studentPartMove{float: right;width: 627px;
            background: #fff5b2;height: 58px;border: groove;}
        #tblMove{}
        #yearSearchM{font-size: 20px;margin-left: 21px;margin-top: -19px;}
        #groupSearchM{font-size: 20px;margin-left: 30px;margin-top: -2px;}
        #txtYearSearchM{margin-left: 11px;cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;}
        #txtGroupSearchM{width: 100px;margin-left: 128px;margin-top: -41px;
            cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;}
        #lblMoveM{margin-left: 25px;font-size: 19px;}
        #btnMove{margin-top: -20px;margin-left: 19px;}
        #listSearch{color: #221c70;font-size: 21px;margin-left: 37px;}
        #listGroups{color: #221c70;font-size: 21px;margin-left: 197px;}
        #listDepartment{color: #221c70;font-size: 21px;margin-left: 40px;}
        .title{font-size: 18px;color: rgb(22, 18, 74);}
        #tblS{width: 35%;float: left;}
        #tblSI{margin-left: 5px;margin-top: 14px;}
        #tblSM{margin-bottom:16px;margin-top: 16px;
            border-style: groove;background: white;
            width: 435px;margin-left: 15px;}
        #tblG{float: left;width: 41%;height: 100%;}
        #tblGI{margin-top: 14px;margin-left: 2px;}
        #tblGM{margin-top: 16px;margin-bottom: 16px;
            background: white;border-style: groove;}
        #tblSModerC{margin-right: 10px;
            margin-left: 10px;border-color: #ece5e5;
            margin-top: 16px;margin-bottom: 5px;
            background: #fff5b2;border-style: groove;}
        #btnAddSt{}
        #btnEditSt{margin-left: -54px;}
        #btnDelSt{margin-left: -115px; }
        #lblL{ margin-left: 10px;}
        #lblF{ margin-left: 10px;}
        #lblP{margin-left: 10px;}
        #lblD{margin-left: 10px;}
        #lblS{margin-left: 10px;}
        #lblDpHT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblDpNT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblGrCT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblGrHT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblGrNT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblGrST{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblLT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblFT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblPT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblDT{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 145px;}
        #lblST{cursor: pointer;border: 2px solid #3c4a90;font-size: 15px;width: 116px;
            margin-top: 5px;}
        #btnEditStFinaly{margin-left: -63px;}
        #tblGModerC{width: 93.2%;margin-left: 18px;border-color: #ece5e5;
            margin-top: 16px;margin-bottom: 5px;
            background: #fff5b2;border-style: groove;}
        #btnAddGr{float: left;margin-left: 27px;margin-top: 10px;}
        #btnEditGr{margin-top: 10px;margin-left: -57px;float: left;}
        #btnDelGr{margin-top: 10px;margin-left: -74px;float: left;}
        #lblGrN{margin-top: -22px;margin-left: 17px;}
        #lblGrC{margin-left: -225px;margin-top: 40px;}
        #lblGrS{margin-left: -169px;margin-top: -22px;}
        #lblGrH{margin-left: -224px;margin-top: 35px;}
        #btnEditGrFinaly{margin-bottom: 7px;margin-left: 93px;}
        #tblD{float: left;width: 22%;}
        #tblDI{margin-left: 73px;margin-top: 13px;}
        #tblSM{margin-bottom:16px;margin-top: 16px;
            border-style: groove;background: white;
            width: 435px;margin-left: 15px;}
        #tblDM{margin-top: 16px;margin-bottom:16px; width: 96%;
            border-style: groove;margin-left: 16px;background: white;}
        #btnAddDp{margin-top: -24px;margin-left: 5px;}
        #btnEditDp{margin-top: -24px;margin-left: -18px;}
        #btnDelDp{margin-left: -118px;margin-top: 36px;}
        #tblDModerC{margin-bottom: 5px;margin-right: 6px;margin-left: 6px;
            background: #fff5b2;border: groove;margin-top: 20px;}
        #btnEditDpFinaly{margin-left: -6px;margin-top: -32px;}
        #lblDpN{margin-left: 8px;margin-top: -45px;}
        #lblDpH{margin-left: -248px;margin-top: 62px;}
        #lblHEAD{margin-left: -167px;margin-top: 20px;}
    </style>
    <script>$(function(){$("#lblID").hide(); $("#lblGrID").hide(); $("#lblDpID").hide();});</script>
</head>
<body>
<form action="<c:url value="/mainmoderator"/>" method="POST">
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
    <div id="studentPartMove">
        <b id="lblMoveM">Move students to group</b>
        <table id="tblMove">
            <tr>
                <td><p id="yearSearchM">Year:<input id="txtYearSearchM" type="text" name="newYear" value="${form.year}"/><br/></td>
                <td><p id="groupSearchM">Group list:</p>
                    <select id="txtGroupSearchM" name="newGroupId">
                        <c:forEach var="group" items="${form.groups}">
                            <option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
                        </c:forEach>
                    </select>
                </td>
                <td><input id="btnMove" type="submit" name="MoveGroup" value="Move"/></td>
            </tr>
        </table>
        </div>
    <div id="tblS">
        <div id="tblSM">
            <b id="listSearch">List of students for selected parameters:</b>
            <table id="tblSI">
                <tr>
                    <th>&nbsp;</th>
                    <th class="title">Lastname</th>
                    <th class="title">Firstname</th>
                    <th class="title">Patronymic</th>
                    <th class="title">Date of birth</th>
                    <th class="title">Sex</th>
                </tr>
                <c:forEach var="student" items="${form.students}">
                    <tr>
                        <td><input id="rdBtn" type="radio" name="studentId" value="${student.studentId}"></td>
                        <td><c:out value="${student.firstName}"/></td>
                        <td><c:out value="${student.surName}"/></td>
                        <td><c:out value="${student.patronymic}"/></td>
                        <td><c:out value="${student.dateOfBirth}"/></td>
                        <td><c:out value="${student.sex}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <table id="tblSModerC">
                <tr>
                    <td><input id="btnAddSt" type="submit" value="Add student" name="Add"/></td>
                    <td><input id="btnEditSt" type="submit" value="Edit student" name="Edit"/></td>
                    <td><input id="btnDelSt" type="submit" value="Delete student" name="Delete"/></td>
                </tr>
                <tr>
                    <td><p id="lblL">Lastname:<input  id="lblLT" name="lblL" type="text" value="${student.surName}"/><br/></td>
                    <td><p id="lblF">Firstname:<input  id="lblFT" name="lblF" type="text" value="${student.firstName}"/><br/></td>
                </tr>
                <tr>
                    <td><p id="lblP">Patronymic:<input id="lblPT" name="lblP" type="text" value="${student.patronymic}"/><br/></td>
                    <td><p id="lblD">Date of birth:<input id="lblDT" name="lblD" type="text" value="${student.dateOfBirth}"/></td>
                </tr>
                <tr>
                    <td><p id="lblS">Sex:<select id="lblST" name = "lblS">
                        <option value = "f">F</option>
                        <option value = "m">M</option>
                    </select><br/></td>
                    <td><p id="lblID"><input name="lblID" type="text" value="${student.studentId}"/></td>
                    <td><input id="btnEditStFinaly" type="submit" value="Do edit" name="EditFinaly"/></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="tblG">
        <div id="tblGM">
            <b id="listGroups">List of all groups:</b>
            <table id="tblGI">
                <tr>
                    <th>&nbsp;</th>
                    <th>Name</th>
                    <th>Curator</th>
                    <th>Spesiality</th>
                    <th>Haed spesiality</th>
                </tr>
                <c:forEach var="group" items="${form.groups}">
                    <tr>
                        <td><input id="rdBtnG" type="radio" name="grId" value="${group.groupId}"></td>
                        <td><c:out value="${group.nameGroup}"/></td>
                        <td><c:out value="${group.curator}"/></td>
                        <td><c:out value="${group.speciality}"/></td>
                        <td><c:out value="${group.speHead}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <table id="tblGModerC">
                <tr>
                    <td><input id="btnAddGr" type="submit" value="Add group" name="AddGr"/></td>
                    <td><input id="btnEditGr" type="submit" value="Edit group" name="EditGr"/></td>
                    <td><input id="btnDelGr" type="submit" value="Delete group" name="DeleteGr"/></td>
                </tr>
                <tr>
                    <td><p id="lblGrN">Name:<input id="lblGrNT" name="lblGrN" type="text" value="${groupE.nameGroup}"/><br/></td>
                    <td><p id="lblGrC">Curator:<input id="lblGrCT" name="lblGrC" type="text" value="${groupE.curator}"/><br/></td>
                    <td><p id="lblGrS">Specialty:<input id="lblGrST" name="lblGrS" type="text" value="${groupE.speciality}"/><br/></td>
                    <td><p id="lblGrH">Head Sp:<input id="lblGrHT" name="lblGrH" type="text" value="${groupE.speHead}"/><br/></td>
                </tr>
                <tr>
                    <td><p id="lblGrID"><input name="lblGrID" type="text" value="${groupE.groupId}"/></td>
                    <td><input id="btnEditGrFinaly" type="submit" value="Do edit" name="EditFinalyGr"/></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="tblD">
        <div id="tblDM">
            <b id="listDepartment">List of all departments:</b>
            <table id="tblDI">
                <tr>
                    <th>&nbsp;</th>
                    <th class="title">Name</th>
                    <th class="title">Head</th>
                </tr>
                <c:forEach var="department" items="${form.departments}">
                    <tr>
                        <td><input id="rdBtnD" type="radio" name="dpId" value="${department.departmentId}"></td>
                        <td><c:out value="${department.nameDept}"/></td>
                        <td><c:out value="${department.head}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <table id="tblDModerC">
                <tr>
                    <td><input id="btnAddDp" type="submit" value="Add Dep" name="AddDp"/></td>
                    <td><input id="btnEditDp" type="submit" value="Edit Dep" name="EditDp"/></td>
                    <td><input id="btnDelDp" type="submit" value="Delete Dep" name="DeleteDp"/></td>
                </tr>
                <tr>
                    <td><p id="lblDpN">Name:<input  id="lblDpNT" name="lblDpN" type="text" value="${department.nameDept}"/><br/></td>
                    <td><p id="lblHEAD">Head:</p></td>
                    <td><p id="lblDpH"><input  id="lblDpHT" name="lblDpH" type="text" value="${department.head}"/><br/></td>
                </tr>
                <tr>
                    <td><p id="lblDpID"><input name="lblDpID" type="text" value="${department.departmentId}"/></td>
                    <td><input id="btnEditDpFinaly" type="submit" value="Do edit" name="EditFinalyDp"/></td>
                </tr>
            </table>
        </div>
    </div>
   </form>
</body>
</html>