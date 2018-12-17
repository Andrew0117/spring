<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<span class="preload1"></span>
<span class="preload2"></span>

<ul id="nav">
    <c:import url="/WEB-INF/pages/tamplate/lang.jsp"/>
    <div id="navConteiner">
        <sec:authorize access="hasRole('ROLE_USER')">
            <li class="top">
                <a href="#" id="privacy" class="top_link">
                    <span>
                        <s:message code="lMenuFeedback"/>
                    </span>
                </a>
            </li>
            <li class="top">
                <a href="#" id="privacy" class="top_link">
                    <span>
                        <s:message code="labelMenuSettings"/>
                    </span>
                </a>
            </li>
        </sec:authorize>
    </div>
</ul>
