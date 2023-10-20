<%--
  Created by IntelliJ IDEA.
  User: zharo
  Date: 20.10.2023
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>welcome</title>
</head>

<body>
<div >

                <%String res = (String) request.getAttribute("resultOfAuth");%>
                <h4 class="block__text"><%=res%></h4>

</div>

</body>
</html>
