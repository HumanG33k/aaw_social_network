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
            <p>Files</p>
        </div>


        <!--        Select image to upload:
                <input type="file" name="fileToUpload" id="fileToUpload">
                <input type="submit" value="Upload Image" name="submit">-->


        <form action="upload.php" method="post" enctype="multipart/form-data">
            <div class="input-group">
                <!--<label class="input-group-label">$</label>-->
                <label class="fi-download size-36 input-group-field ">

                </label>

                <input type="file" name="fileToUpload" id="fileToUpload">
                <p class="help-text" id="emailHelpText">You must have a correct file.</p>
                <div class="input-group-button">
                    <input type="submit" class="button" value="Submit">
                </div>
            </div>
        </form>

        <!-- 
        .fi-archive 
        .fi-book
        .fi-download
        .fi-folder
        .fi-photo
        .fi-play-video
        .fi-page
        -->
        <%@ include file="../fragments/_file_pdf.jsp" %>
        <%@ include file="../fragments/_footer.jsp" %>

    </body>
</html>
