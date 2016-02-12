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
        <title>${user.getName()} Profile - Social Network Project</title>
    </head>

    <body>
        <%@ include file="../fragments/_header.jsp" %>
        
        <div class="primary callout text-center size-36">
            <p>${user.getName()} profile</p>
        </div>
       
        <form method="post" action="<%=request.getContextPath()%>/${user.getId()}/profile.htm">
            <div class="text-center size-24">
                Public information :
                <c:choose>
                    <c:when test="${myProfile == true}">
                        <input placeholder="${user.getInformation()}" name="infoInput">
                        <button type="submit" class="button success">Save</button>
                    </c:when>
                    <c:when test="${myFriend == false}">
                        <i>${user.getInformation()}</i>
                        <button type="submit" class="button">Add friend</button>
                    </c:when>
                    <c:otherwise>
                        <i>${user.getInformation()}</i>
                        <button type="submit alert" class="button">Remove friend</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
        
        <hr/>
        
        <c:choose>
            <c:when test="${myProfile == false && myFriend == false}">
                <div class="text-center">
                    Add this user as a friend to see their posts.
                </div>
            </c:when>
            <c:otherwise>
                <div class="text-center">
                    TODO Show posts here.
                </div>
            </c:otherwise>
        </c:choose>

        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
