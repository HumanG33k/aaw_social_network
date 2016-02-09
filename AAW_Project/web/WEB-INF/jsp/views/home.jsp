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
        <title>Home - Social Network Project</title>
    </head>

    <body>
        <%@ include file="../fragments/_header.jsp" %>

        <div class="primary callout text-center">
            <p>Home</p>
        </div>

        <div class="medium-9 medium-centered columns">
            <div class="row">
                <div class="column text-center medium-1">
                    <img src= "resources/img/profile.jpg" >
                </div>

                <form method="post" action="home.htm">
                    <div class="column medium-11">
                        <label>
                            <input type="text" placeholder="Express yourself" name="postContent">
                        </label>
                    </div>

                    <div class="column">
                        <button type="submit" class="success button expanded">Send a message</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="rows ">
            <div class="row small-uncollapse large-collapse">

                <% for (int i = 0; i < 10; i += 1) {%>
                <article class="column media-object">
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
                </article>
                <% }%>
            </div>
        </div>

        <%@ include file="../fragments/_footer.jsp" %>
    </body>
</html>
