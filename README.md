# ラーメンAI Webアプリ

## ディレクトリ構成（推奨MVC構成）

```
ramen-ai-dev/
├─ src/
│  └─ main/
│     ├─ java/
│     │  └─ com/
│     │     └─ ramen/
│     │        ├─ controller/
│     │        │    └─ RamenRecommendServlet.java
│     │        ├─ model/
│     │        │    └─ RamenShopDao.java
│     │        └─ entity/
│     │             └─ RamenShop.java
│     └─ webapp/
│         ├─ index.html
│         ├─ recommend.jsp
│         ├─ shop1.html
│         ├─ shop2.html
│         ├─ ...（各店舗ページ）
│         └─ WEB-INF/
│              └─ web.xml
├─ .vscode/
│   └─ settings.json
├─ README.md
```

- Javaパッケージ名は `com.ramen` に統一
- MVCごとに `controller`, `model`, `entity` で整理
- Webリソースは `src/main/webapp` 配下にまとめる
