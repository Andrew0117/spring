<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div style="float: right; padding: 5px; margin: 5px; font-family:arial, verdana, sans-serif; font-size:11px; font-weight:bold; color:#ccc;">
    <div style="float: left;"><span style="color: white;"><s:message code="labelLanguege"/>   </span><c:import
            url="/WEB-INF/pages/tamplate/languege.jsp"/></div>
    <span style="white-space: pre;">   </span>
    <span style="color: white;"><s:message code="labelWelcome"/> 
            ${SPRING_SECURITY_CONTEXT.authentication.principal}&nbsp;|&nbsp;
    </span>
    <a style="color: white;" href='<c:url value="/exit" />'><span><s:message code="labelExit"/></span></a>
</div>
