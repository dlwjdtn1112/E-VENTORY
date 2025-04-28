<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            padding: 30px; /* 🔥 */
            border-radius: 12px; /* 🔥 */
            box-shadow: 0 3px 8px rgba(0, 0, 0, 0.08); /* 🔥 */
            width: 320px; /* 🔥 */
            text-align: center;
        }

        .register-box h2 {
            margin-bottom: 16px; /* 🔥 */
            color: #003366;
            font-size: 20px; /* 🔥 */
        }

        .register-box input[type="text"],
        .register-box input[type="password"],
        .register-box select {
            width: 100%;
            padding: 8px; /* 🔥 */
            margin-top: 8px; /* 🔥 */
            border: 1px solid #ccc;
            border-radius: 6px; /* 🔥 */
            font-size: 13px; /* 🔥 */
        }

        .register-box button {
            width: 100%;
            padding: 10px; /* 🔥 */
            margin-top: 16px; /* 🔥 */
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 6px; /* 🔥 */
            font-weight: bold;
            font-size: 14px; /* 🔥 */
            cursor: pointer;
        }

        .register-box button:hover {
            background-color: #125ea7;
        }

        .register-box a {
            display: block;
            margin-top: 10px; /* 🔥 */
            color: #1976d2;
            font-size: 12px; /* 🔥 */
            text-decoration: none;
        }

        .error-message {
            color: red;
            font-size: 12px; /* 🔥 */
            margin-top: 8px; /* 🔥 */
        }
    </style>
</head>

<body>

<div class="register-box">
    <h2>회원가입</h2>

    <!-- 에러 메시지 출력 -->
    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

    <form action="/todo/register" method="post">
        <input type="text" name="userid" placeholder="아이디" required />
        <input type="password" name="password" placeholder="비밀번호" required />
        <input type="text" name="name" placeholder="이름" required />
        <input type="text" name="email" placeholder="이메일" />
        <input type="text" name="phone" placeholder="전화번호" />
        <input type="text" name="company" placeholder="회사명" />

        <!-- 역할 선택 드롭다운 -->
        <select name="role" required>
            <option value="">-- 역할 선택 --</option>
            <option value="관리자">관리자</option>
            <option value="사용자">사용자</option>
        </select>

        <button type="submit">회원가입</button>
    </form>

    <a href="/index.jsp">← 로그인으로 돌아가기</a>
</div>

</body>
</html>
