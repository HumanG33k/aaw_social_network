<%-- 
    Document   : header
    Created on : 27 nov. 2015, 12:20:03
    Author     : nvillemi
--%>

<header>        
    <div class="title-bar" data-responsive-toggle="example-menu" data-hide-for="medium">
        <button class="menu-icon" type="button" data-toggle></button>
        <div class="title-bar-title">Menu</div>
    </div>

    <div class="top-bar" id="example-menu">
        <div class="top-bar-left">
            <ul class="dropdown menu" data-dropdown-menu>
                <li class="menu-text">XYT-7</li>
                <li><a href="<%=request.getContextPath() %>/home.htm">Home</a></li>
                <li><a href="<%=request.getContextPath() %>/profile.htm">Profile</a></li>
                <li><a href="<%=request.getContextPath() %>/friends.htm">Friends</a></li>
                <li><a href="<%=request.getContextPath() %>/files.htm">Files</a></li>
            </ul>
        </div>
            
        <div class="top-bar-right">
            <ul class="menu">
                <li><input type="search" placeholder="Search"></li>
                <li><button type="button" class="button">Search</button></li>
                <li><button type="button" class="button">Sign out</button></li>
            </ul>
        </div>
    </div>
</header>