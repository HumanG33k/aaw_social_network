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
        
        <div class="rows ">
            <div class="columns small-centered small-4">
                <%@ include file="../fragments/_profile_form.jsp" %>
                <button type="button" class="success button expanded">Save</button>
            </div>
        </div>
       
        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
