<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 | WMS</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background: linear-gradient(to bottom right, #e6f0ff, #ffffff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .register-box {
            background: white;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        .register-box h2 {
            margin-bottom: 20px;
            color: #003366;
        }

        .register-box input[type="text"],
        .register-box input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-top: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 14px;
        }

        .register-box button {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            font-size: 15px;
            cursor: pointer;
        }

        .register-box button:hover {
            background-color: #125ea7;
        }

        .register-box a {
            display: block;
            margin-top: 14px;
            color: #1976d2;
            font-size: 13px;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="register-box">
    <h2>회원가입</h2>
    <form action="/todo/register" method="post">
        <input type="text" name="userid" placeholder="아이디" required />
        <input type="password" name="password" placeholder="비밀번호" required />
        <input type="text" name="name" placeholder="이름" required />
        <input type="text" name="email" placeholder="이메일" />
        <input type="text" name="phone" placeholder="전화번호" />
        <input type="text" name="company" placeholder="회사명" />
        <input type="text" name = "role" placeholder="역할" />

        <button type="submit">회원가입</button>
    </form>

    <!-- ✅ 역할 선택 -->


    <a href="/index.jsp">← 로그인으로 돌아가기</a>
</div>

</body>
</html>
