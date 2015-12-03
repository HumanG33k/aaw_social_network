<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="resources/css/foundation.css">
        <link type="text/css" rel="stylesheet" href="resources/css/app.css">
        <link type="text/css" rel="stylesheet" href="resources/icon/foundation-icons.css">

        <script src="resources/js/jquery.js"></script>
        <script src="resources/js/foundation.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Social Network project</title>
    </head>

    <body>
        <%@ include file="../fragments/_header.jsp" %>

        <div class="primary callout text-center">
            <p>Friends</p>
        </div>
        <div class="row small-up-1 medium-up-2 large-up-4">
            <% for (int i = 0; i < 10; i += 1) {%>
            <div class="column media-object">
                <div class="media-object-section left">
                    <div class="thumbnail ">
                        <img src= "resources/img/profile.jpg" class="profile_picture">
                    </div>
                </div>
                <div class="media-object-section">
                    <a href="<%=request.getContextPath()%>/profile.htm" class="margin-bottom-1 text-left">Jean-Michel</a>
                    
                    <div class="preview-glyphs">
                        <a href="" class="step fi-x size-12 warning "></a>
                    </div>
                </div>
            </div>
            <% }%>

            <button type="button" class="success button expanded">Save</button>
        </div>

        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
