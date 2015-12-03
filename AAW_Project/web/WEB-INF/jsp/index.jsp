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
        <%@ include file="./fragments/_header.jsp" %>
        <h1 class="text-center">Welcome to Social Network XYT-7 </h1>

        <div class="row medium-uncollapse large-collapse">
            <div class="medium-4 columns container-hover">
                <label>Display Name
                    <input type="text" aria-describedby="DisplayNameHelpText">
                </label>
                <p class="help-text" id="DisplayNameHelpText">Your display name.</p>
                <%@ include file="./fragments/_login_form.jsp" %>
                <button type="button" class=" button expanded">Sign up</button>
                    
            </div>
            <div class="medium-4 columns container-hover">
                <div class="glyph">
                    <div class="preview-glyphs text-center">
                        <i class="step fi-share size-72 "></i>

                    </div>
                </div>  
            </div>

            <div class="medium-4 columns container-hover">
                <%@ include file="./fragments/_login_form.jsp" %>
                <button type="button" class="success button expanded">Sign In</button>
            </div>
        </div>
        <%@ include file="./fragments/_footer.jsp" %>

    </body>
</html>
