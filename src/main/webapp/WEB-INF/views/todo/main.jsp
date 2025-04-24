<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WMS 메인</title>
    <style>
        body {
            font-family: "Apple SD Gothic Neo", "Noto Sans KR", sans-serif;
            background: linear-gradient(to bottom right, #f0f8ff, #ffffff);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        h1 {
            margin-bottom: 40px;
            color: #333;
        }

        .menu-btn {
            display: block;
            width: 200px;
            padding: 15px;
            margin: 10px;
            font-size: 16px;
            text-align: center;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .menu-btn:hover {
            background-color: #125ea7;
        }

        .search-form {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h1>📦 창고 관리 시스템</h1>

<a href="/todo/inbound" class="menu-btn">1. 입고 목록</a>
<a href="/todo/outbound" class="menu-btn">2. 출고 목록</a>
<a href="/todo/inventory" class="menu-btn">3. 재고 목록</a>
<a href="/todo/inbound/requestForm" class="menu-btn">4. 입고 요청</a>
<a href="/todo/inbound/approveForm" class="menu-btn">5. 입고 승인</a>
<a href="/todo/outbound/requestForm" class="menu-btn">4. 출고 요청</a>

<div class="search-form">
    <form action="/todo/inbound/search" method="get">
        <input type="text" name="keyword" placeholder="상품명을 입력하세요" value="${param.keyword}" />
        <button type="submit">검색</button>
    </form>
</div>

</body>
</html>