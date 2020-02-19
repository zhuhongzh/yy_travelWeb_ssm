<html>
<body>
<form action="/file/upload" method="post" enctype="multipart/form-data">
    <label>用户名：</label><input type="text" name="name"/><br/>
    <label>密 码：</label><input type="password" name="password"/><br/>
    <label>头 像1</label><input type="file" name="file"/><br/>
    <label>头 像2</label><input type="file" name="file"/><br/>
    <input type="submit" value="提  交"/>
</form>
    <c:forEach items="${imagesPathList}" var="image">
    <img src="${basePath}${image}"><br/>
    </c:forEach>
</body>
</html>
