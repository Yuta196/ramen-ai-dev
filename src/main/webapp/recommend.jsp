<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ramen.entity.RamenShop" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>おすすめラーメン店</title>
    <style>
        body { font-family: 'Segoe UI', 'Hiragino Kaku Gothic ProN', Meiryo, sans-serif; background: linear-gradient(120deg, #fffbe7 60%, #ffe5ec 100%); margin: 0; }
        main { padding: 2.5rem 1rem 2rem 1rem; max-width: 700px; margin: 2rem auto; background: #fff; border-radius: 16px; box-shadow: 0 4px 24px #e6394622; }
        h1 { color: #e63946; font-size: 2.2rem; margin-bottom: 0.5em; text-align: center; }
        .ramen-img { display: block; margin: 0 auto 1.5em auto; max-width: 350px; border-radius: 12px; box-shadow: 0 2px 12px #e6394633; }
        ul { font-size: 1.15rem; padding-left: 0; list-style: none; }
        a { color: #e63946; text-decoration: none; }
        form { margin-bottom: 2em; display: flex; flex-wrap: wrap; gap: 1em; justify-content: center; }
        label { margin-right: 0.5em; font-weight: bold; }
        input[type="text"] { padding: 0.4em 0.7em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; }
        button[type="submit"] { background: #e63946; color: #fff; border: none; border-radius: 4px; padding: 0.5em 1.2em; font-size: 1em; cursor: pointer; transition: background 0.2s; }
        button[type="submit"]:hover { background: #b71c1c; }
        .shop-list-item { margin-bottom: 1.5em; background: #fff8f0; border-radius: 8px; padding: 1em 1.2em; box-shadow: 0 2px 8px #e6394611; }
        .shop-list-item b { font-size: 1.15em; }
        .shop-list-item span { display: block; margin-top: 0.3em; color: #555; font-size: 0.98em; }
        .admin-link { display:inline-block;margin-bottom:1em;padding:0.5em 1em;background:#457b9d;color:#fff;border-radius:4px;text-decoration:none;transition:background 0.2s; }
        .admin-link:hover { background: #1d3557; }
    </style>
</head>
<body>
    <main>
        <h1>おすすめラーメン店</h1>
        <img src="images/ramen.png" alt="濃厚で美味しそうなラーメン" class="ramen-img">
        <a href="admin" class="admin-link">管理画面へ</a>
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
                    <li class="shop-list-item">
                        <a href="shopDetail?id=<%= shop.getId() %>"><b><%= shop.getName() %></b></a>（<%= shop.getAddress() %>）<br>
                        <span>特徴: <%= shop.getDescription() %></span>
                    </li>
                <% } %>
            <% } else { %>
                <li>該当するラーメン店がありませんでした。</li>
            <% } %>
        </ul>
    </main>
</body>
</html>