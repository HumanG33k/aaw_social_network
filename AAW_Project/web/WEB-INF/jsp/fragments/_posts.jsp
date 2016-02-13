<div class="medium-9 medium-centered columns">
    <div class="row">
        <div class="column text-center medium-1">
            <img src= "${pageContext.request.contextPath}/resources/img/profile.png" >
        </div>

        <form method="post" action="${pageContext.request.contextPath}/${user.getId()}/createPost.htm">
            <div class="column medium-11">
                <label>
                    <input type="text" placeholder="Express yourself" name="postContent">
                </label>
            </div>

            <div class="column">
                <button type="submit" class="button expanded">Send a message</button>
            </div>
        </form>
    </div>
</div>
<hr/>
<div class="rows ">
    <div class="row small-uncollapse large-collapse">
        <c:choose>
            <c:when test="${posts.size() == 0}">
                <div class="text-center">
                    No posts to show.
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="post" items="${posts}">
                    <article class="column media-object">
                        <div class="media-object-section left">
                            <div class="thumbnail">
                                <img src= "${pageContext.request.contextPath}/resources/img/profile.png" class="profile_picture">
                            </div>
                        </div>
                        <div class="media-object-section">
                            <a class="size-21" href="<%=request.getContextPath()%>/${post.getSender().getId()}/profile.htm">${post.getSender().getName()}</a>
                            <c:if test="${post.getSender() != post.getTarget()}">
                                => 
                                <a class="size-21" href="<%=request.getContextPath()%>/${post.getTarget().getId()}/profile.htm">${post.getTarget().getName()}</a>
                            </c:if>
                            <span class="size-12">(${post.getDate()})</span>
                            <c:if test="${post.getSender() == currentUser || post.getTarget() == currentUser}">
                                <a class="size-12" style="color: red; font-weight: bold" href="<%=request.getContextPath()%>/${post.getId()}/removePost.htm">Remove</a>
                            </c:if>
                            <div>
                                ${post.getContent()}
                            </div>
                        </div>
                    </article>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>