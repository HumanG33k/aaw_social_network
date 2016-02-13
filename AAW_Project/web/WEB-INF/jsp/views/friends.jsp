<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/foundation.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/icon/foundation-icons.css">
        <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/foundation.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friends - Social Network Project</title>
    </head>

    <body>
        <%@ include file="../fragments/_header.jsp" %>

        <div class="primary callout text-center size-36">
            <p>Friends</p>
        </div>

        <div class="row small-up-1 medium-up-2 large-up-4">
            <c:choose>
                <c:when test="${friends.size() == 0}">
                    <div class="text-center">
                        You don't have any friends!
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="friend" items="${friends}">
                        <div class="column media-object">
                            <div class="media-object-section left">
                                <div class="thumbnail ">
                                    <img src= "resources/img/profile.jpg" class="profile_picture">
                                </div>
                            </div>
                            <div class="media-object-section">
                                <a class="size-36" href="<%=request.getContextPath()%>/${friend.getId()}/profile.htm" class="margin-bottom-1 text-left">${friend.getName()}</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            
        </div>

        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
