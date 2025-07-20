<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ramen.entity.RamenShop" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ラーメン店管理画面</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #f4f8fb; margin: 0; }
        main { padding: 2rem; max-width: 800px; margin: auto; }
        h1 { color: #457b9d; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 2em; background: #fff; }
        th, td { border: 1px solid #bfc9d1; padding: 0.7em; text-align: left; }
        th { background: #a8dadc; color: #222; }
        tr:nth-child(even) { background: #f1f5f9; }
        a, button { color: #fff; background: #457b9d; border: none; border-radius: 3px; padding: 0.3em 0.9em; text-decoration: none; cursor: pointer; }
        a:hover, button:hover { background: #1d3557; }
        .back-link { background: #e63946; margin-bottom: 2em; display: inline-block; }
        .add-btn { background: #43aa8b; margin-bottom: 1em; }
        form { display: inline; }
    </style>
</head>
<body>
    <main>
        <a href="recommend" class="back-link">← トップページへ戻る</a>
        <h1>ラーメン店管理画面</h1>
        <a href="shopAdd" class="add-btn">＋ 新規追加</a>
        <table>
            <tr>
                <th>店名</th>
                <th>住所</th>
                <th>特徴</th>
                <th>操作</th>
            </tr>
            <% List<RamenShop> shopList = (List<RamenShop>)request.getAttribute("shopList"); %>
            <% if (shopList != null && !shopList.isEmpty()) { %>
                <% for (RamenShop shop : shopList) { %>
                    <tr>
                        <td><%= shop.getName() %></td>
                        <td><%= shop.getAddress() %></td>
                        <td><%= shop.getDescription() %></td>
                        <td>
                            <a href="shopEdit?id=<%= shop.getId() %>">編集</a>
                            <form method="post" action="shopDelete" style="display:inline;">
                                <input type="hidden" name="id" value="<%= shop.getId() %>">
                                <button type="submit" onclick="return confirm('本当に削除しますか？');">削除</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr><td colspan="4">登録されているラーメン店はありません。</td></tr>
            <% } %>
        </table>
    </main>
</body>
</html>
