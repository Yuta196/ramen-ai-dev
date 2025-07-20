<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ラーメン店 新規追加</title>
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
        <h1>ラーメン店 新規追加</h1>
        <% String error = (String)request.getAttribute("error"); if (error != null) { %>
            <div class="error"><%= error %></div>
        <% } %>
        <form method="post" action="shopAdd">
            <label>店名：<input type="text" name="name" required></label>
            <label>住所：<input type="text" name="address" required></label>
            <label>特徴：<textarea name="description" rows="3" required></textarea></label>
            <button type="submit">追加</button>
        </form>
        <a href="recommend">← 一覧に戻る</a>
    </main>
</body>
</html>
