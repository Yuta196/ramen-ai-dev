<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>おすすめラーメン店</title>
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
        <h2 style="margin-top:0; font-size:1.2rem; color:#b30000;">おすすめラーメン店</h2>
        <ul style="list-style:none; padding:0;">
            <li><a href="<%= request.getContextPath() %>/shop1.html">ラーメン一番星</a></li>
            <li><a href="<%= request.getContextPath() %>/shop2.html">麺屋ドラゴン</a></li>
            <li><a href="<%= request.getContextPath() %>/shop3.html">中華そば福来</a></li>
            <li><a href="<%= request.getContextPath() %>/shop4.html">味噌丸</a></li>
            <li><a href="<%= request.getContextPath() %>/shop5.html">塩ラーメン青空</a></li>
        </ul>
    </nav>
    <div class="content">
        <header>
            <h1>おすすめラーメン検索</h1>
        </header>
        <main>
            <section>
                <h2>あなたの好みでおすすめラーメンを検索</h2>
                <form method="post" action="<%= request.getContextPath() %>/recommend">
                    <label>味：
                        <select name="taste">
                            <option value="">選択してください</option>
                            <option value="醤油">醤油</option>
                            <option value="味噌">味噌</option>
                            <option value="塩">塩</option>
                            <option value="とんこつ">とんこつ</option>
                        </select>
                    </label>
                    <label>スープ：
                        <select name="soup">
                            <option value="">選択してください</option>
                            <option value="あっさり">あっさり</option>
                            <option value="こってり">こってり</option>
                        </select>
                    </label>
                    <label>辛さ：
                        <select name="spicy">
                            <option value="">選択してください</option>
                            <option value="辛い">辛い</option>
                            <option value="普通">普通</option>
                            <option value="なし">なし</option>
                        </select>
                    </label>
                    <button type="submit">おすすめを検索</button>
                </form>
                <div style="margin-top:2em;">
                    <h3>おすすめラーメン店リスト</h3>
                    <ul>
                        <% 
                            java.util.List shopList = (java.util.List)request.getAttribute("shopList");
                            if (shopList != null && !shopList.isEmpty()) {
                                for (Object shop : shopList) {
                        %>
                            <li><%= shop %></li>
                        <% 
                                }
                            } else { 
                        %>
                            <li>該当するラーメン店がありませんでした。</li>
                        <% } %>
                    </ul>
                </div>
                <a href="<%= request.getContextPath() %>/index.html">← ホームに戻る</a>
            </section>
        </main>
        <footer>
            &copy; 2025 ラーメンAI
        </footer>
    </div>
</body>
</html>
