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
       
        
        <div class="text-center size-24">
            <c:choose>
                <c:when test="${myProfile == true}">
                    <form method="post" action="<%=request.getContextPath()%>/${user.getId()}/userInfo.htm">
                        Public information :
                        <input name="infoInput" value="${user.getInformation()}">
                        <button type="submit" class="button success">Save</button>
                    </form>
                </c:when>
                <c:when test="${myFriend == false}">
                    Public information :
                    <i>${user.getInformation()}</i>
                    <c:choose>
                        <c:when test="${requestSent == true}">
                            <a class="button disabled" href="<%=request.getContextPath()%>/${user.getId()}/sendRequest.htm">Request sent</a>
                        </c:when>
                        <c:when test="${requestReceived == true}">
                            <a class="button success" href="<%=request.getContextPath()%>/${notifId}/acceptFriend.htm">Accept request</a>
                        </c:when>
                        <c:otherwise>
                            <a class="button" href="<%=request.getContextPath()%>/${user.getId()}/sendRequest.htm">Add friend</a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    Public information :
                    <i>${user.getInformation()}</i>
                    <a class="button alert" href="<%=request.getContextPath()%>/${user.getId()}/removeFriend.htm">Remove friend</a>
                </c:otherwise>
            </c:choose>
        </div>
        
        <hr/>
        
        <c:choose>
            <c:when test="${myProfile == false && myFriend == false}">
                <div class="text-center">
                    Add this user as a friend to see their posts.
                </div>
            </c:when>
            <c:otherwise>
                <%@ include file="../fragments/_posts.jsp" %>
            </c:otherwise>
        </c:choose>

        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
