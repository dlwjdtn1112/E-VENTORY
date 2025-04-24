<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>입고 목록 | E-ventory</title>
    <style>
        body {
            margin: 0;
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(to bottom, #e6f0ff, #ffffff);
            color: #003366;
        }

        h2 {
            text-align: center;
            margin: 3rem 0 1.5rem;
            font-size: 2rem;
            color: #003366;
        }

        .table-container {
            max-width: 1100px;
            margin: 0 auto 4rem;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
            overflow: hidden;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            background-color: #0053a0;
            color: white;
        }

        th, td {
            padding: 14px 18px;
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: #f4f9ff;
        }

        .badge {
            padding: 6px 14px;
            border-radius: 9999px;
            font-size: 14px;
            font-weight: 500;
            display: inline-block;
        }

        .badge.approved {
            background-color: #e0f2ff;
            color: #0053a0;
        }

        .badge.pending {
            background-color: #fdecea;
            color: #c62828;
        }

        .negative {
            color: #d32f2f;
            font-weight: bold;
        }

        @media (max-width: 768px) {
            th, td {
                font-size: 14px;
                padding: 10px;
            }

            h2 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>

<h2>입고 목록</h2>

<div class="table-container">
    <table>
        <thead>
        <tr>
            <th>입고 ID</th>
            <th>상품 ID</th>
            <th>입고 수량</th>
            <th>요청일</th>
            <th>창고 ID</th>
            <th>회원 아이디</th>
            <th>상태</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dtoList1}" var="dto">
            <tr>
                <td><c:out value="${dto.inbound_id}"/></td>
                <td><c:out value="${dto.product_id}"/></td>
                <td>
                    <span class="${dto.inbound_quantity lt 0 ? 'negative' : ''}">
                        <c:out value="${dto.inbound_quantity}"/>
                    </span>
                </td>
                <td><c:out value="${dto.req_inbound_day}"/></td>
                <td><c:out value="${dto.warehouse_id}"/></td>
                <td><c:out value="${dto.userid}"/></td>
                <td>
                    <span class="badge ${dto.status eq '승인' ? 'approved' : 'pending'}">
                        <c:out value="${dto.status}"/>
                    </span>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
