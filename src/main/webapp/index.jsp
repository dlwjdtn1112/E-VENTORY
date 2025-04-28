<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-ventory 로그인</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            font-size: 14px;
            background-color: #ffffff;
            color: #003366;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            width: 320px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            padding: 30px 24px;
            text-align: center;
        }
        .logo-text {
            font-size: 28px;
            font-weight: bold;
            color: #0053a0;
            margin-bottom: 20px;
        }
        .login-form {
            display: flex;
            flex-direction: column;
        }
        .login-form input {
            padding: 10px 12px;
            margin-bottom: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 13.5px;
        }
        .login-form button {
            padding: 11px;
            background-color: #0053a0;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .login-form button:hover {
            background-color: #003f7f;
        }
        .signup-link {
            margin-top: 14px;
            font-size: 13px;
        }
        .signup-link a {
            color: #0053a0;
            text-decoration: none;
        }
        .signup-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="login-container">
    <!-- 🔥 여기 수정 (이미지 대신 텍스트로) -->
    <div class="logo-text">E-VENTORY</div>

    <h2 style="margin-bottom: 20px;">로그인</h2>

    <form class="login-form" action="/todo/login" method="post">
        <input type="text" name="userid" placeholder="아이디 또는 전화번호" required />
        <input type="password" name="password" placeholder="비밀번호" required />
        <button type="submit">로그인</button>
    </form>

    <div class="signup-link">
        <a href="/todo/register">회원가입하기 →</a>
    </div>
</div>

</body>
</html>
