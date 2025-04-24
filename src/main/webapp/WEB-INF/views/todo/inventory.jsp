<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Inventory List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 20px;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: auto;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th {
            background-color: #1f78d1;
            color: white;
            padding: 12px;
        }

        td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ccc;
        }

        tr:nth-child(even) {
            background-color: #f0f8ff; /* 연한 파랑 */
        }

        h2 {
            text-align: center;
            color: #1f78d1;
        }
    </style>
</head>
<body>

<h2>재고 목록</h2>

<table border="1">
    <thead>
    <tr>
        <th>창고 ID</th>
        <th>상품 ID</th>
        <th>재고 수량</th>
        <th>회원</th>>
        <th>최종 수정일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dtoList3}" var="dto">
        <tr>
            <td><c:out value="${dto.warehouse_id}"/></td>
            <td><c:out value="${dto.product_id}"/></td>
            <td><c:out value="${dto.quantity}"/></td>
            <td><c:out value="${dto.userid}"/></td>
            <td><c:out value="${dto.updated_at}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
