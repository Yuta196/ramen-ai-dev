<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.ramen.entity.RamenShop" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ラーメン店 編集</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #fffbe7; margin: 0; }
        main { padding: 2rem; }
        h1 { color: #e63946; }
        form { margin-bottom: 2em; }
        label { display: block; margin-bottom: 1em; }
        input, textarea { width: 100%; max-width: 400px; }
        .error { color: red; }
    </style>
</head>
<body>
    <main>
        <h1>ラーメン店 編集</h1>
        <% String error = (String)request.getAttribute("error"); if (error != null) { %>
            <div class="error"><%= error %></div>
        <% } %>
        <% RamenShop shop = (RamenShop)request.getAttribute("shop"); if (shop != null) { %>
        <form method="post" action="shopEdit">
            <input type="hidden" name="id" value="<%= shop.getId() %>">
            <label>店名：<input type="text" name="name" value="<%= shop.getName() %>" required></label>
            <label>住所：<input type="text" name="address" value="<%= shop.getAddress() %>" required></label>
            <label>特徴：<textarea name="description" rows="3" required><%= shop.getDescription() %></textarea></label>
            <button type="submit">更新</button>
        </form>
        <% } else { %>
            <div>店舗情報が見つかりませんでした。</div>
        <% } %>
        <a href="recommend">← 一覧に戻る</a>
    </main>
</body>
</html>
