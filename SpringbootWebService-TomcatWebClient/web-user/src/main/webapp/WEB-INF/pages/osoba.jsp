<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap/bootstrap.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/modal.css"/>"/>
    <script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.js"/>"></script>
    <style>
        tr.tr-hover-class:hover {
            background: lightgreen !important;
        }
    </style>
</head>
<body id="body-main-data-control">
<div id="content-main-data-control">
    <div id="block-main-data-control">
        <div id="block-form">
            <f:form method="post" action="/osoba" modelAttribute="osoba">
                <div style="padding:0; margin:0; width:845px; height:90%; margin-left:auto; margin-right:auto;
                    background-repeat:repeat-x;">
                    <div style="width: 205px; height: 60px;">
                        <p><s:message code="labelname"/></p>
                        <f:input path="name" cssStyle="width: 200px;"/>
                    </div>
                    <div style="width: 205px; margin-left: 210px; margin-top: -60px; height: 60px;">
                        <p><s:message code="labelsurname"/></p>
                        <f:input path="surname" cssStyle="width: 200px;"/>
                    </div>
                    <div style="width: 205px; margin-left: 420px; margin-top: -60px; height: 60px;">
                        <p style="white-space: nowrap;"><s:message code="labelhomePhone"/></p>
                        <f:input path="homePhone" cssStyle="width: 200px;"/>
                    </div>
                    <div style="width: 205px; height: 60px;">
                        <p><s:message code="labelofficePhone"/></p>
                        <f:input path="officePhone" cssStyle="width: 200px;"/>
                    </div>
                    <div style="width: 205px; margin-left: 210px; margin-top: -60px; height: 60px;">
                        <p><s:message code="labelEMail"/></p>
                        <f:input path="eMail" cssStyle="width: 200px;"/>
                    </div>

                    <button style="float: right;" class="btn btn-success btn-success:hover mt-3" onClick="submit()">
                        <s:message code="lSearch"/>
                    </button>
                </div>
            </f:form>
        </div>
        <div style="height: 45px;">
            <button id="add" style="float: left;" class="btn btn-success btn-success:hover mt-3">Add</button>
        </div>
        <div>
            <p>Double click on row in table for open window for edit</p>
            <p>Click on anchor in row for delete record</p>
        </div>
        <div id="content-table">
            <table id="table-user" class="table_col">
                <thead>
                <tr>
                    <th><s:message code="labelname"/></th>
                    <th><s:message code="labelsurname"/></th>
                    <th><s:message code="labelhomePhone"/></th>
                    <th><s:message code="labelofficePhone"/></th>
                    <th><s:message code="labelEMail"/></th>
                    <th><s:message code="labelphotoOfAPerson"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${osobaVMList}" var="list" varStatus="vs">
                    <tr class='tr-hover-class' id="${list.id}">
                        <td>${list.name}</td>
                        <td>${list.surname}</td>
                        <td>${list.homePhone}</td>
                        <td>${list.officePhone}</td>
                        <td>${list.EMail}</td>
                        <td>
                            <c:choose>
                                <c:when test="${list.photoOfAPersonString != null}">
                                    <img class="example-image" src="data:image/png;base64, ${list.photoOfAPersonString}"
                                         alt="img-${vs.index}" width="150" height="150"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="" alt=""/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><a id="${vs.index}" name="delete" rel="${list.id}" style="cursor: pointer;">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="dialog" class="modal">
            <div style="background-color: #fefefe; margin: 15% auto; padding: 20px;
                        border-radius: 15px; border: 2px solid #4b9b29; width: 50%; height: 425px;">
                <div>
                    <span id="state"></span>
                </div>
                <form id="uploadForm" name="uploadForm" method="POST" enctype="multipart/form-data">
                    <div style="width: 205px; height: 60px;">
                        <p><s:message code="labelname"/></p>
                        <input id="name" type="text" value="" style="width: 200px; height: 28px;"/>
                    </div>
                    <div style="width: 255px; margin-left: 210px; margin-top: -60px; height: 60px;">
                        <p><s:message code="labelsurname"/></p>
                        <input id="surname" type="text" value="" style="width: 200px; height: 28px;"/>
                    </div>
                    <div style="width: 205px; height: 60px;">
                        <p><s:message code="labelhomePhone"/></p>
                        <input id="homePhone" type="text" value="" style="width: 200px; height: 28px;"/>
                    </div>
                    <div style="width: 255px; margin-left: 210px; margin-top: -60px; height: 60px;">
                        <p><s:message code="labelofficePhone"/></p>
                        <input id="officePhone" type="text" value="" style="width: 200px; height: 28px;"/>
                    </div>
                    <div style="width: 205px; height: 60px;">
                        <p><s:message code="labelEMail"/></p>
                        <input id="eMail" type="text" value="" style=" width: 200px; height: 28px;"/>
                    </div>
                    <div style="width: 255px; margin-left: 210px; margin-top: -60px; height: 60px;">
                        <input id="img-empty" name="img-empty" type="checkbox" value="img-empty">
                        <label for="img-empty">Do not upload photos</label>
                    </div>
                    <input id="file" name="file" type="file" accept="image/*" class="file-input" placeholder="Upload File">
                    <div id="preview" style="overflow-x: auto; white-space: nowrap;"></div>

                    <input id="cancel" type="button" value="<s:message code="labelCancel"/>" style="margin-top: 15px; float: right;"/>
                    <input id="success" type="submit" value="<s:message code="labelSubmit"/>" style="margin-top: 15px; float: right;"/>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/js/osoba.js"/>"></script>
</body>
</html>
