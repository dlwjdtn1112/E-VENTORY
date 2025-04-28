<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>출고 요청 | E-ventory</title>
    <style>
        body {
            margin: 0;
            font-family: "Noto Sans KR", sans-serif;
            background: #f4f9ff;
            display: flex;
        }

        .sidebar {
            width: 160px;
            background-color: #0053a0;
            color: white;
            height: 100vh;
            padding: 40px 20px;
            display: flex;
            flex-direction: column;
            box-shadow: 2px 0 10px rgba(0,0,0,0.05);
        }

        .sidebar h2 {
            font-size: 1.3rem;
            margin-bottom: 30px;
        }

        .menu-btn {
            display: block;
            margin: 8px 0;
            padding: 12px;
            border-radius: 10px;
            background: white;
            color: #0053a0;
            text-decoration: none;
            text-align: center;
            font-weight: bold;
            transition: background 0.3s;
        }

        .menu-btn:hover {
            background-color: #eeeeee;
        }

        .main {
            flex: 1;
            padding: 20px 30px;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 10px;
        }

        .user-info {
            background: white;
            padding: 10px 14px;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            font-size: 8px;
            line-height: 1.6;
            min-width: 170px;
            text-align: left;
        }

        .user-info div {
            margin-bottom: 4px;
        }

        h1 {
            font-size: 1.2rem;
            margin: 0;
            font-weight: 600;
            color: #0053a0;
        }

        .form-box {
            background: white;
            border-radius: 12px;
            padding: 16px; /* padding 줄임 */
            box-shadow: 0 4px 10px rgba(0,0,0,0.08);
            width: 500px; /* 약간 줄임 */
            margin-top: 5px; /* margin-top 줄임 */
        }

        .form-box label {
            display: block;
            font-weight: bold;
            margin: 6px 0 4px; /* label 간격 줄임 */
        }

        .form-box input, .form-box select {
            width: 100%;
            padding: 8px; /* input padding 줄임 */
            margin-bottom: 6px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        .form-box button {
            width: 100%;
            background-color: #0053a0;
            color: white;
            border: none;
            padding: 10px;
            font-weight: bold;
            border-radius: 8px;
            cursor: pointer;
        }

        /* 로그아웃 버튼 추가 스타일 */
        .logout-btn {
            margin-top: 6px;
            background: #0053a0;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            font-size: 11px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<!-- 사이드바 -->
<div class="sidebar">
    <h2>👤 회원 메뉴</h2>
    <a href="/todo/inbound" class="menu-btn">입고 목록</a>
    <a href="/todo/outbound" class="menu-btn">출고 목록</a>
    <a href="/todo/inventory" class="menu-btn">재고 목록</a>
    <a href="/todo/inbound/requestForm" class="menu-btn">입고 요청</a>
    <a href="/todo/outbound/requestForm" class="menu-btn">출고 요청</a>
</div>

<!-- 메인 -->
<div class="main">
    <div class="top-bar">
        <h1>🚚 출고 요청</h1>
        <div class="user-info">
            <div><strong><c:out value="${sessionScope.name}" /></strong>님 환영합니다</div>
            <div>📧 <c:out value="${sessionScope.email}" /></div>
            <div>📞 <c:out value="${sessionScope.phone}" /></div>
            <div>🏢 <c:out value="${sessionScope.company}" /></div>
            <div>🔐 <c:out value="${sessionScope.role}" /></div>

            <form action="/todo/logout" method="post" style="margin-top: 8px; text-align: right;">
                <button type="submit" class="logout-btn">
                    로그아웃
                </button>
            </form>
        </div>
    </div>

    <div class="form-box">
        <form action="/todo/outbound/request" method="post">
            <label for="product_id">상품 ID</label>
            <input type="text" name="product_id" id="product_id" required>

            <label for="outbound_quantity">출고 수량</label>
            <input type="number" name="outbound_quantity" id="outbound_quantity" required>

            <label for="warehouse_id">창고</label>
            <select name="warehouse_id" id="warehouse_id" required>
                <option value="">-- 창고 선택 --</option>
                <option value="Busan0629">부산</option>
                <option value="Chungcheong1220">충청</option>
                <option value="Daegu0722">대구</option>
                <option value="Gangwon0712">강원</option>
                <option value="Hanam0329">하남</option>
            </select>

            <button type="submit">출고 요청</button>
        </form>
    </div>
</div>

</body>
</html>
