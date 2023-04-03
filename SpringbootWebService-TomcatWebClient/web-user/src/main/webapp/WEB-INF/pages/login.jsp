<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.css"/>" type="text/css"/>
    <style>
        .btn-login {
            color: #fff;
            background-color: #2354b3;
            border-color: #151515;
        }
    </style>
</head>
<body>
<div class="container mt-4 pt-4">
    <div style="opacity: 0.9;">
        <div style="float: left; width: 400px; height: 165px;">
            <form id="f" name="f" action="/<c:url value="check" />" method="POST">
                <table style="width: 100%">
                    <tr style="font-family: verdana, arial, sans-serif; color: white; font-size: 12px; font-style: italic; font-weight: bold;">
                        <td><s:message code="labelUser"/></td>
                    </tr>
                    <tr>
                        <td><input id="ju" style="border: 2px solid #bdc7d8; margin: 0.25rem; width: 100%;
                                                          border-radius: 4px; padding: 0.5rem; font-size: 1rem;
                                                          font-family: verdana, arial, sans-serif; transition: border-color 0.5s ease-out"
                                   type="text" name="ju" value=""></td>
                    </tr>
                    <tr style="font-family: verdana, arial, sans-serif; color: white; font-size: 12px; font-style: italic; font-weight: bold;">
                        <td><s:message code="labelPass"/></td>
                    </tr>
                    <tr>
                        <td><input style="border: 2px solid #bdc7d8; margin: 0.25rem; width: 100%;
                                                          border-radius: 4px; padding: 0.5rem;
                                                          font-size: 1rem; font-family: verdana, arial, sans-serif;
                                                          transition: border-color 0.5s ease-out"
                                   type="password" name="jp" value=""/></td>
                    </tr>
                    <tr>
                        <td><c:if test="${param.error != null}">
                                <span style="font-family: verdana, arial, sans-serif; color: #ef0c0c; font-size: 12px; font-style: italic; font-weight: bold;">
                                    <s:message code="labelErrorLogin"/>
                                </span>
                        </c:if></td>
                    </tr>
                    <tr>
                        <td><input name="submit" type="submit" style="float: right;" class="btn btn-login"
                                   value="<s:message code="labelButtonLogin"/>"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
