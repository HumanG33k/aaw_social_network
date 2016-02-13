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
        <title>Notifications - Social Network Project</title>
    </head>

    <body>
        <%@ include file="../fragments/_header.jsp" %>

        <div class="primary callout text-center size-36">
            <p>Notifications</p>
        </div>

        <div class="rows ">
            <div class="row small-uncollapse large-collapse">
                <c:choose>
                <c:when test="${notifs.size() == 0}">
                    <div class="text-center">
                        You don't have any notifications.
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="notif" items="${notifs}">
                        <div class="column media-object">
                            <div class="media-object-section left">
                                <div class="thumbnail ">
                                    <img src= "resources/img/profile.jpg" class="profile_picture">
                                </div>
                            </div>
                            <div class="media-object-section">
                                <a href="<%=request.getContextPath()%>/${notif.getSender().getId()}/profile.htm" class="margin-bottom-1 text-left size-24">${notif.getSender().getName()}</a>
                                sent you a friend request.
                                <a class="button success" href="<%=request.getContextPath()%>/${notif.getId()}/acceptFriend.htm">Accept</a>
                                <a class="button alert" href="<%=request.getContextPath()%>/${notif.getId()}/denyFriend.htm">Deny</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </div>
        </div>
        
        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
