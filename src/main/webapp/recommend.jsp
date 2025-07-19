<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ramen.entity.RamenShop" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ラーメンAI ホーム</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #fffbe7; margin: 0; }
        header { background: #e63946; color: white; padding: 1.5rem 2rem; }
        main { padding: 2rem; }
        h1 { margin-top: 0; }
        ul { font-size: 1.2rem; }
        a { color: #e63946; text-decoration: none; }
        nav { width:220px; background:#f1c40f; min-height:100vh; padding:2rem 1rem; box-sizing:border-box; float:left; }
        .content { margin-left:220px; }
        footer { background: #222; color: #fff; text-align: center; padding: 1rem; margin-top: 2rem; }
        label { margin-right: 1em; }
    </style>
</head>
<body>
    <nav>
        <h2 style="margin-top:0; font-size:1.2rem; color:#b30000;">ラーメン店一覧</h2>
        <ul style="list-style:none; padding:0;">
            <% List<RamenShop> shopList = (List<RamenShop>)request.getAttribute("shopList"); %>
            <% if (shopList != null && !shopList.isEmpty()) { %>
                <% for (RamenShop shop : shopList) { %>
                    <li><b><%= shop.getName() %></b><br><span style="font-size:0.9em; color:#555;">（<%= shop.getAddress() %>）</span></li>
                <% } %>
            <% } else { %>
                <li>登録店舗がありません</li>
            <% } %>
        </ul>
    </nav>
    <div class="content">
        <header>
            <h1>ラーメンAI ホーム</h1>
        </header>
        <main>
            <section>
                <h2>条件でラーメン店を検索</h2>
                <form method="get" action="recommend">
                    <label>店名：<input type="text" name="name" placeholder="例: ラーメン一郎"></label>
                    <label>住所：<input type="text" name="address" placeholder="例: 新宿区"></label>
                    <label>特徴：<input type="text" name="description" placeholder="例: 豚骨"></label>
                    <button type="submit">検索</button>
                </form>
                <div style="margin-top:2em;">
                    <h3>検索結果</h3>
                    <ul>
                        <% if (shopList != null && !shopList.isEmpty()) { %>
                            <% for (RamenShop shop : shopList) { %>
                                <li><b><%= shop.getName() %></b>（<%= shop.getAddress() %>）<br><span style="font-size:0.95em; color:#555;">特徴: <%= shop.getDescription() %></span></li>
                            <% } %>
                        <% } else { %>
                            <li>該当するラーメン店がありませんでした。</li>
                        <% } %>
                    </ul>
                </div>
            </section>
        </main>
        <footer>
            &copy; 2025 ラーメンAI
        </footer>
    </div>
</body>
</html>
