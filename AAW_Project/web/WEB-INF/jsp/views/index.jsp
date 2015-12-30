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
        <h1 class="text-center">Welcome to Social Network XYT-7</h1>
        <h5 class="text-center" id="indexMessage">Please sign up or sign in if you already have an account.</h5>
        
        <div class="row medium-uncollapse large-collapse">
            <div class="medium-4 columns container-hover">
                <form method="post" action="index.htm">
                    <%@ include file="../fragments/_profile_form.jsp" %>
                    <button type="submit" class="button expanded">Sign up</button>
                </form>
            </div>
            <div class="medium-4 columns container-hover">
                <div class="glyph">
                    <div class="preview-glyphs text-center">
                        <i class="step fi-share size-72 "></i>
                    </div>
                </div>
            </div>

            <div class="medium-4 columns container-hover">
                <form method="post" action="home.htm">
                    <%@ include file="../fragments/_login_form.jsp" %>
                    <button type="button" class="success button expanded">Sign In</button>
                </form>
            </div>
        </div>
        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
