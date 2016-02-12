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
        <title>Index - Social Network Project</title>
    </head>

    <body>
        <h1 class="text-center">Welcome to Social Network XYT-7</h1>
        <h5 class="text-center" name="indexMessage">${indexMessage}</h5>
        
        <div class="row medium-uncollapse large-collapse">
            <div class="medium-4 columns container-hover">
                <form method="post" action="${pageContext.request.contextPath}/signUp.htm">
                    <label>Display name
                        <input type="text" name="nameSignUp">
                    </label>
                    <p class="help-text">Your display name.</p>
                    <label>Email
                        <input type="email" name="emailSignUp">
                    </label>
                    <p class="help-text">You must have a valid email address.</p>
                    <label>Password
                        <input type="password" name="passwordSignUp">
                    </label>
                    <p class="help-text">Your password must have at least 8 characters.</p>
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
                <form method="post" action="${pageContext.request.contextPath}/signIn.htm">
                    <label>Display name
                        <input type="text" name="nameSignIn">
                    </label>
                    <label>Password
                        <input type="password" name="passwordSignIn">
                    </label>
                    <button type="submit" class="success button expanded">Sign In</button>
                </form>
            </div>
        </div>
        <%@ include file="../fragments/_footer.jsp" %>
    </body>
</html>
