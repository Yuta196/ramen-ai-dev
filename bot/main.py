import os
import openai
import requests
from github import Github
from datetime import datetime

openai.api_key = os.getenv("OPENAI_API_KEY")
github_token = os.getenv("GITHUB_TOKEN")
repo_name = "Yuta196/ramen-ai-dev"

g = Github(github_token)
repo = g.get_repo(repo_name)

issues = repo.get_issues(state='open')
issue = next(iter(issues), None)

if issue:
    prompt = f"""
以下はGitHub Issueの内容。このIssueに基づいてJavaFXでUIを作成してください。

【Issueタイトル】
{issue.title}

【Issue本文】
{issue.body}

コード全体（1ファイル）と簡単な説明をMarkdown形式で出力してください。
"""

    print(f"[{datetime.now()}] 問い合わせ中: {issue.title}")
    response = openai.ChatCompletion.create(
        model="gpt-4o",
        messages=[{"role": "user", "content": prompt}],
        temperature=0.3,
    )

    output = response["choices"][0]["message"]["content"]

    file_path = f"output/{issue.number}_{issue.title.replace(' ', '_')}.md"
    os.makedirs("output", exist_ok=True)
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(output)

    print("✅ 出力完了:", file_path)
else:
    print("📭 OpenなIssueは見つかりませんでした。")
