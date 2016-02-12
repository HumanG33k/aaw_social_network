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
        <title>Search - Social Network Project</title>
    </head>

    <body>
        <%@ include file="../fragments/_header.jsp" %>

        <div class="primary callout text-center size-36">
            <p>Search</p>
        </div>
        
        <h5 class="text-center">Found ${users.size()} user(s).</h5>
        
        <div class="row small-up-1 medium-up-2 large-up-4">
            <c:forEach var="user" items="${users}">
                <div class="column media-object">
                    <div class="media-object-section left">
                        <div class="thumbnail">
                            <img src= "resources/img/profile.jpg" class="profile_picture">
                        </div>
                    </div>
                    <div class="media-object-section">
                        <a class="size-36" href="<%=request.getContextPath()%>/${user.getId()}/profile.htm" class="margin-bottom-1 text-left">${user.getName()}</a>
                    </div>
                </div>
            </c:forEach>
  
        </div>

        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
