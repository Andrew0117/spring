<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:message code="labelProject"/></title>
    <c:import url="/WEB-INF/pages/form/head.jsp"/>
</head>
<body id="body-main">

<c:import url="/WEB-INF/pages/tamplate/menu.jsp"/>

<div id="block-form">
    <c:if test="${newUser != null}">
        ID: ${newUser.id}, Password: ${newUser.pass}, Active: ${newUser.active}, Role: ${newUser.roles.authority}, Date created: ${newUser.date}
    </c:if>

    <f:form method="post" action="/user/create" modelAttribute="userFilter">
        <div style="padding:0; margin:0; width:845px; height:90%; margin-left:auto; margin-right:auto;
                    background-repeat:repeat-x;">
            <div>
                <div>
                    <div style="width: 350px; height: 54px;">
                        <p><s:message code="labelName"/><s:message code="labelStar"/></p>
                        <f:input path="name" style="width: 200px; height: 28px;" maxlength="101" size="50"/>
                        <f:errors path="name">
                            <span><s:message code="labelErrorName"/></span>
                        </f:errors>
                    </div>
                    <div style="width: 350px; margin-left: 355px; margin-top: -54px; height: 54px;">
                        <p><s:message code="labelPass"/><s:message code="labelStar"/></p>
                        <f:input path="pass" style="width: 200px; height: 28px;" maxlength="101" size="50"/>
                        <f:errors path="pass">
                            <span><s:message code="labelErrorPass"/></span>
                        </f:errors>
                    </div>
                </div>
            </div>
            <div>
                <div style="width: 350px; height: 54px;">
                    <p><s:message code="labelActive"/><s:message code="labelStar"/></p>
                    <f:input path="active" style="width: 200px; height: 28px;" maxlength="101" size="50"/>
                    <f:errors path="active">
                        <span><s:message code="labelErrorActive"/></span>
                    </f:errors>
                </div>
                <div style="width: 350px; margin-left: 355px; margin-top: -54px; height: 54px;">
                    <p><s:message code="lRole"/><s:message code="labelStar"/></p>
                    <f:select path="role" cssStyle="width: 350px;">
                        <option value="0"></option>
                        <f:options itemValue="id" itemLabel="authority" items="${roles}"></f:options>
                    </f:select>
                    <f:errors path="role">
                        <span><s:message code="labelErrorRole"/></span>
                    </f:errors>
                </div>
            </div>

            <button style="float: right; margin-top: -24px;" class="input-ins" onClick="submit()">
                <s:message code="labelWrite"/>
            </button>
        </div>
    </f:form>
</div>
</body>
</html>
