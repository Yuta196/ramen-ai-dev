<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>おすすめラーメン店</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #fffbe7; margin: 0; }
        main { padding: 2rem; }
        h1 { color: #e63946; }
        ul { font-size: 1.2rem; }
        a { color: #e63946; text-decoration: none; }
    </style>
</head>
<body>
    <main>
        <h1>おすすめラーメン店</h1>
        <ul>
            <% 
                java.util.List<String> shops = (java.util.List<String>)request.getAttribute("recommendList");
                if (shops != null && !shops.isEmpty()) {
                    for (String shop : shops) {
            %>
                <li><%= shop %></li>
            <% 
                    }
                } else { 
            %>
                <li>該当するラーメン店がありませんでした。</li>
            <% } %>
        </ul>
        <a href="index.html">← ホームに戻る</a>
    </main>
</body>
</html>
