<%-- 
    Document   : header
    Created on : 27 nov. 2015, 12:20:03
    Author     : nvillemi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>        
    <div class="top-bar">
        <div class="top-bar-left">
            <ul class="menu">
                <li class="menu-text">AAW</li>
                <li><a href="<%=request.getContextPath()%>/home.htm">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/friends.htm">Friends</a></li>
                <li><a href="<%=request.getContextPath()%>/${currentUser.getId()}/profile.htm">Profile</a></li>
                <li><a href="<%=request.getContextPath()%>/${currentUser.getId()}/files.htm">Files</a></li>
                <c:choose>
                    <c:when test="${nbNotifs == 0}">
                        <li><a href="<%=request.getContextPath()%>/notifications.htm">Notifications</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a style="font-weight: bold" href="<%=request.getContextPath()%>/notifications.htm">Notifications (${nbNotifs})</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
           
        <form method="post" action="<%=request.getContextPath()%>/search.htm">
            <div class="top-bar-right">
                <ul class="menu">
                    <li class="menu-text">${currentUser.getName()}</li>
                    <li><input type="search" placeholder="Search" name="searchName"></li>
                    <li><button type="submit" class="button">Search</button></li>
                    <li><a class="button alert" href="<%=request.getContextPath()%>/signOut.htm">Sign out</a></li>
                </ul>
            </div>
        </form>
    </div>
</header>
