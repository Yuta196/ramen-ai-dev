<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ramen.entity.RamenShop" %>
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
        form { margin-bottom: 2em; }
        label { margin-right: 1em; }
    </style>
</head>
<body>
    <main>
        <h1>おすすめラーメン店</h1>
        <a href="shopAdd" style="display:inline-block;margin-bottom:1em;padding:0.5em 1em;background:#e63946;color:#fff;border-radius:4px;text-decoration:none;">＋ 新規追加</a>
        <form method="get" action="recommend">
            <label>店名：<input type="text" name="name" placeholder="例: ラーメン一郎"></label>
            <label>住所：<input type="text" name="address" placeholder="例: 新宿区"></label>
            <label>特徴：<input type="text" name="description" placeholder="例: 豚骨"></label>
            <button type="submit">検索</button>
        </form>
        <ul>
            <% List<RamenShop> shopList = (List<RamenShop>)request.getAttribute("shopList"); %>
            <% if (shopList != null && !shopList.isEmpty()) { %>
                <% for (RamenShop shop : shopList) { %>
                    <li style="margin-bottom:1em;">
                        <a href="shopDetail?id=<%= shop.getId() %>"><b><%= shop.getName() %></b></a>（<%= shop.getAddress() %>）<br>
                        <span style="font-size:0.95em; color:#555;">特徴: <%= shop.getDescription() %></span>
                        <div style="margin-top:0.5em;">
                            <a href="shopEdit?id=<%= shop.getId() %>" style="margin-right:0.5em;padding:0.2em 0.8em;background:#457b9d;color:#fff;border-radius:3px;text-decoration:none;">編集</a>
                            <form method="post" action="shopDelete" style="display:inline;">
                                <input type="hidden" name="id" value="<%= shop.getId() %>">
                                <button type="submit" style="background:#e63946;color:#fff;border:none;padding:0.2em 0.8em;border-radius:3px;cursor:pointer;" onclick="return confirm('本当に削除しますか？');">削除</button>
                            </form>
                        </div>
                    </li>
                <% } %>
            <% } else { %>
                <li>該当するラーメン店がありませんでした。</li>
            <% } %>
        </ul>
    </main>
</body>
</html>