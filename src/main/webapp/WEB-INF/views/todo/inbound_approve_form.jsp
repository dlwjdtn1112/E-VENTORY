<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>입고 승인</title>
</head>
<body>
<h2>입고 승인</h2>
<form action="/todo/inbound/update" method="post">
    <input type="number" name="inbound_id" placeholder="승인할 입고 ID" required /><br>
    <button type="submit">입고 승인</button>
</form>
</body>
</html>
