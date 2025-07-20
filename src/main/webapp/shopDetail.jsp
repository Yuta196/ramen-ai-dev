<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.ramen.entity.RamenShop" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ラーメン店詳細</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #fffbe7; margin: 0; }
        main { padding: 2rem; }
        h1 { color: #e63946; }
        a { color: #e63946; text-decoration: none; }
    </style>
</head>
<body>
    <main>
        <% RamenShop shop = (RamenShop)request.getAttribute("shop"); %>
        <% if (shop != null) { %>
            <h1><%= shop.getName() %></h1>
            <p><b>住所:</b> <%= shop.getAddress() %></p>
            <p><b>特徴:</b> <%= shop.getDescription() %></p>
        <% } else { %>
            <h1>店舗情報が見つかりませんでした</h1>
        <% } %>
        <a href="recommend">← 一覧に戻る</a>
    </main>
</body>
</html>
