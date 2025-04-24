<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>E-ventory</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            font-size: 14px;
            background-color: #ffffff;
            color: #003366;
        }
        header {
            background-color: #0053a0;
            color: white;
            padding: 0.8rem 1.2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        nav a {
            color: white;
            margin-left: 1rem;
            text-decoration: none;
            font-weight: 500;
            font-size: 13px;
        }
        nav a:hover {
            text-decoration: underline;
        }
        .auth-box input {
            padding: 0.2rem 0.4rem;
            margin-right: 0.3rem;
            border-radius: 3px;
            border: none;
            font-size: 13px;
        }
        .auth-box button {
            background-color: white;
            color: #0053a0;
            border: none;
            padding: 0.3rem 0.6rem;
            font-size: 13px;
            border-radius: 3px;
            font-weight: bold;
            cursor: pointer;
        }
        .auth-box button:hover {
            background-color: #e0e0e0;
        }
        .hero {
            text-align: center;
            padding: 3rem 1rem;
            background: linear-gradient(to bottom, #e6f0ff, #ffffff);
        }
        .hero h2 {
            font-size: 1.8rem;
            margin-bottom: 0.5rem;
        }
        .hero p {
            font-size: 0.95rem;
            margin-bottom: 1.5rem;
            color: #444;
        }
        .hero button {
            padding: 0.7rem 1.5rem;
            background-color: #0053a0;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 0.9rem;
            cursor: pointer;
        }
        .hero button:hover {
            background-color: #003f7f;
        }
        .features {
            background-color: #f0f6ff;
            padding: 2.5rem 1rem;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 1.2rem;
            max-width: 1000px;
            margin: 0 auto;
        }
        .card {
            background-color: white;
            border-radius: 6px;
            padding: 1.5rem;
            box-shadow: 0 1px 4px rgba(0,0,0,0.1);
        }
        .card h3 {
            margin-top: 0;
            font-size: 1.1rem;
        }
        .card p {
            font-size: 0.9rem;
        }
        .about, .contact {
            text-align: center;
            padding: 2.5rem 1rem;
            font-size: 0.95rem;
        }
        .contact {
            background-color: #dceeff;
        }
        footer {
            background-color: #0053a0;
            color: white;
            text-align: center;
            padding: 0.8rem;
            font-size: 13px;
        }
        img.logo {
            height: 30px;
        }
        .header-right {
            display: flex;
            align-items: center;
        }
        form {
            display: flex;
            align-items: center;
            margin-left: 1rem;
        }
    </style>
</head>
<body>
<header>
    <div class="header-left">
        <img src="/e-ventory-logo.png" alt="E-ventory Logo" class="logo" />
        <span style="font-size: 1.2rem; font-weight: bold; margin-left: 0.6rem;">E-ventory</span>
    </div>
    <div class="header-right">
        <nav>
            <a href="#features">기능</a>
            <a href="#about">회사 소개</a>
            <a href="#contact">문의하기</a>
        </nav>
        <form action="/todo/login" method="post">
            <input type="text" name="userid" placeholder="아이디" required />
            <input type="password" name="password" placeholder="비밀번호" required />
            <button type="submit">로그인</button>
        </form>
    </div>
</header>

<section class="hero">
    <h2>전자제품을 위한 스마트 창고관리 시스템</h2>
    <p>전자 기기를 쉽고 빠르게 관리하세요. 실시간 재고, 입출고 이력, 자동화된 알림까지.</p>
    <button>지금 시작하기</button>
</section>

<section id="features" class="features">
    <div class="card">
        <h3>실시간 재고 확인</h3>
        <p>전자기기별 실시간 재고 현황을 한눈에 파악할 수 있습니다.</p>
    </div>
    <div class="card">
        <h3>빠른 입출고 처리</h3>
        <p>입고 및 출고 과정을 효율적으로 관리하고 자동화합니다.</p>
    </div>
    <div class="card">
        <h3>스마트 리포트</h3>
        <p>재고 보고서, 품절 알림 등 다양한 기능을 제공합니다.</p>
    </div>
</section>

<section id="about" class="about">
    <h2>E-ventory란?</h2>
    <p>E-ventory는 전자기기 유통을 위한 최적화된 창고관리 솔루션으로, 스마트폰 기반 UI와 강력한 관리자 기능을 제공합니다.</p>
</section>

<section id="contact" class="contact">
    <h2>문의하기</h2>
    <p>시스템 도입 문의는 아래 이메일로 연락 주세요.</p>
    <p><strong>contact@e-ventory.co.kr</strong></p>
</section>

<footer>
    © 2025 E-ventory. All rights reserved.
</footer>
</body>
</html>
