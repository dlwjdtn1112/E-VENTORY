<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>입고 요청 | E-ventory</title>
    <style>
        body {
            font-family: "Apple SD Gothic Neo", "Noto Sans KR", sans-serif;
            background: linear-gradient(to bottom right, #e6f0ff, #ffffff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: white;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #003366;
            margin-bottom: 20px;
        }

        input[type="text"],
        input[type="number"] {
            width: 90%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 15px;
        }

        button {
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 12px 24px;
            font-size: 16px;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 20px;
        }

        button:hover {
            background-color: #125ea7;
        }

        a.back-link {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #1976d2;
            font-size: 14px;
        }

        a.back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>입고 요청</h2>
    <form action="/todo/inbound/request" method="post">
        <input type="text" name="product_id" placeholder="상품 ID" required /><br>
        <input type="number" name="inbound_quantity" placeholder="입고 수량" required /><br>
        <input type="text" name="warehouse_id" placeholder="창고 ID" required /><br>
        <button type="submit">입고 요청</button>
    </form>

    <a href="/todo" class="back-link">← 메인으로 돌아가기</a>
</div>

</body>
</html>
