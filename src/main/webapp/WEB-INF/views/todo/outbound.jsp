<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Outbound List</title>
    <style>
        body {
            font-family: "Apple SD Gothic Neo", "Noto Sans KR", sans-serif;
            background: linear-gradient(to bottom right, #e8f0fe, #ffffff); /* 부드러운 배경 */
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: rgba(255, 255, 255, 0.95); /* 반투명 카드 */
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }

        h2 {
            text-align: center;
            font-size: 20px;
            color: #333333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 14px;
        }

        thead th {
            background-color: #1976d2;
            color: white;
            padding: 12px;
            text-align: center;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        tbody td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #e0e0e0;
            color: #444;
        }

        tbody tr:hover {
            background-color: #e3f2fd;
        }

        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>📦 출고 목록</h2>

    <table>
        <thead>
        <tr>
            <th>출고 ID</th>
            <th>상품 ID</th>
            <th>출고 수량</th>
            <th>요청일</th>
            <th>창고 ID</th>
            <th>회원 아이디</th>
            <th>상태</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dtoList2}" var="dto">
            <tr>
                <td><c:out value="${dto.outbound_id}"/></td>
                <td><c:out value="${dto.product_id}"/></td>
                <td><c:out value="${dto.outbound_quantity}"/></td>
                <td><c:out value="${dto.req_outbound_day}"/></td>
                <td><c:out value="${dto.warehouse_id}"/></td>
                <td><c:out value="${dto.userid}"/></td>
                <td><c:out value="${dto.status}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
