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
        <title>Home - Social Network Project</title>
    </head>

    <body>
        <%@ include file="../fragments/_header.jsp" %>
        
        <div class="primary callout text-center size-36">
            <p>Home</p>
        </div>

        <div class="medium-9 medium-centered columns">
            <div class="row">
                <div class="column text-center medium-1">
                    <img src= "resources/img/profile.jpg" >
                </div>

                <form method="post" action="${pageContext.request.contextPath}/createPost.htm">
                    <div class="column medium-11">
                        <label>
                            <input type="text" placeholder="Express yourself" name="postContent">
                        </label>
                    </div>

                    <div class="column">
                        <button type="submit" class="button expanded">Send a message to your friends</button>
                    </div>
                </form>
            </div>
        </div>
        <hr/>
        <div class="rows ">
            <div class="row small-uncollapse large-collapse">
                <c:forEach var="post" items="${posts}">
                    <article class="column media-object">
                        <div class="media-object-section left">
                            <div class="thumbnail">
                                <img src= "resources/img/profile.jpg" class="profile_picture">
                            </div>
                        </div>
                        <div class="media-object-section">
                            <a class="size-21" href="<%=request.getContextPath()%>/${post.getSender().getId()}/profile.htm">${post.getSender().getName()}</a>
                            <c:if test="${post.getSender() != post.getTarget()}">
                                => 
                                <a href="<%=request.getContextPath()%>/${post.getTarget().getId()}/profile.htm">${post.getTarget().getName()}</a>
                            </c:if>
                            <span class="size-12">(${post.getDate()})</span>
                            <div>
                                ${post.getContent()}
                            </div>
                        </div>
                    </article>
                </c:forEach>
            </div>
        </div>

        <%@ include file="../fragments/_footer.jsp" %>
    </body>
</html>
