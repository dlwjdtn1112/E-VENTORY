<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>WMS 대시보드</title>
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
            box-shadow: 2px 0 10px rgba(0, 0, 0, 0.05);
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
            position: absolute;
            top: 20px;
            right: 20px;
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

        .dashboard {
            margin-top: 30px;
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
        }

        .card {
            background: white;
            border-radius: 15px;
            padding: 24px 20px;
            width: 100px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
        }

        .card-title {
            font-size: 14px;
            color: #888;
            margin-bottom: 6px;
        }

        .card-value {
            font-size: 22px;
            font-weight: bold;
            color: #0053a0;
        }

        .table-container {
            background: white;
            margin-top: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.05);
            overflow: hidden;
            max-height: 320px;
            overflow-y: auto;
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
            padding: 14px;
            text-align: center;
            font-size: 11px;
        }

        tr:nth-child(even) {
            background-color: #f2f6fb;
        }

        .badge {
            padding: 6px 12px;
            border-radius: 9999px;
            font-size: 10px;
        }

        .approved {
            background: #e0f2ff;
            color: #0053a0;
        }

        .pending {
            background: #ffe9e6;
            color: #c62828;
        }

        .intro-box {
            margin-top: 40px;
            background: white;
            border-radius: 12px;
            padding: 24px 30px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.05);
            line-height: 1.6;
            font-size: 13px;
            color: #333;
        }

        .intro-box h2 {
            color: #0053a0;
            font-size: 1.2rem;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>

<!-- 좌측 사이드바 -->
<div class="sidebar">
    <h2>👤 회원 메뉴</h2>
    <a href="/todo/inbound" class="menu-btn">입고 목록</a>
    <a href="/todo/outbound" class="menu-btn">출고 목록</a>
    <a href="/todo/inventory" class="menu-btn">재고 목록</a>
    <a href="/todo/inbound/requestForm" class="menu-btn">입고 요청</a>
    <a href="/todo/outbound/requestForm" class="menu-btn">출고 요청</a>
</div>

<!-- 메인 콘텐츠 -->
<div class="main">
    <div class="top-bar">
        <div>
            <h1>👤 회원 전용 페이지입니다.</h1>
            <p style="font-size: 0.9rem; color: #0053a0; margin-top: 6px;">
                E-ventory에 로그인하신 것을 환영합니다!
            </p>
        </div>
        <div class="user-info">
            <div><strong><c:out value="${sessionScope.name}" /></strong>님 환영합니다</div>
            <div>📧 <c:out value="${sessionScope.email}" /></div>
            <div>📞 <c:out value="${sessionScope.phone}" /></div>
            <div>🏢 <c:out value="${sessionScope.company}" /></div>
            <div>🔐 <c:out value="${sessionScope.role}" /></div>

            <form action="/todo/logout" method="post" style="margin-top: 8px; text-align: right;">
                <button type="submit" style="margin-top: 6px; background: #0053a0; color: white; border: none; padding: 6px 12px; border-radius: 6px; font-size: 11px; cursor: pointer;">
                    로그아웃
                </button>
            </form>
        </div>
    </div>

    <!-- 회사 소개 섹션 -->
    <div class="intro-box">
        <h2>회사 소개</h2>
        <p>
            E-ventory는 중소기업을 위한 <strong>스마트 창고관리 시스템(WMS)</strong>을 제공합니다.<br>
            재고 현황을 실시간으로 파악하고, 입고·출고 이력을 체계적으로 관리할 수 있도록 설계된 이 플랫폼은<br>
            업무 효율성 향상과 물류 비용 절감을 동시에 실현합니다.
        </p>
        <p>
            사용자 친화적인 인터페이스와 권한 기반 관리 시스템을 통해<br>
            관리자와 일반 사용자 모두가 직관적으로 시스템을 활용할 수 있습니다.
        </p>
        <p>
            E-ventory는 앞으로도 다양한 산업군에 맞춘 <strong>맞춤형 WMS 솔루션</strong>을 통해<br>
            고객사의 디지털 전환을 지원하겠습니다.
        </p>
    </div>
</div>

</body>
</html>
