<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
    <head>
        <title><s:message code="labelProject"/></title>
        <link href="./css/l/l.css" type="text/css" rel="stylesheet"/>
        <link href="./css/tamplate/main.css" type="text/css" rel="stylesheet"/>
    </head>
    <body onload="document.f.ju.focus();">
        <div id="main">
            <div style="opacity: 0.9;" class="login">
                <div style="height: 82px; min-width: 980px;">
                    <div style="margin: 0 auto; padding-top: 13px; width: 980px;">
                        <div style="float: right;">
                            <form id="f" name="f" action="/<c:url value="check" />" method="POST">
                                <table>
                                    <tr style="font-family: verdana, arial, sans-serif; color: black; font-size: 12px; font-style: italic; font-weight: bold;">
                                        <td><s:message code="labelUser"/>:</td>
                                        <td><s:message code="labelPass"/>:</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td><input style="border: 1px solid #bdc7d8; margin: 0; width: 142px; height: 20px;
                                                          font-size: 12px; font-family: verdana, arial, sans-serif;" 
                                                   type="text" name="ju" value=""></td>
                                        <td><input style="border: 1px solid #bdc7d8; margin: 0; width: 142px; height: 20px;
                                                          font-size: 12px; font-family: verdana, arial, sans-serif;" 
                                                   type="password" name="jp" value="" /></td>
                                        <td><input name="submit" type="submit" style="float: right;" value="<s:message code="labelButtonLogin"/>"/></td>
                                    </tr>
                                </table>
                            </form>
                            <c:if test="${param.error != null}">
                                <span 
                                    style="font-family: verdana, arial, sans-serif; color: maroon; font-size: 12px; font-style: italic; font-weight: bold;">
                                    <s:message code="labelErrorLogin"/>
                                </span>
                            </c:if>
                            </div>
                        </div>
                        <div style="width: 130px; /*padding: 5px;*/ /*margin: 5px;*/ height: 20px;
                             font-family: arial, verdana, sans-serif; font-size:11px; font-weight:bold;">
                            <div style="float: left; padding: 2px; margin: 2px;">
                                <span style="color: white;"><s:message code="labelLanguege"/>   </span><c:import url="/WEB-INF/pages/tamplate/languege.jsp"/>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </body>
</html>
