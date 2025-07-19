package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RamenRecommendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String taste = request.getParameter("taste"); // 例: "醤油" など
        String soup = request.getParameter("soup");   // 例: "とんこつ" など
        String spicy = request.getParameter("spicy"); // 例: "辛い" など

        String url = "jdbc:mysql://localhost:3306/ramen_db?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String password = "password"; // 環境に合わせて変更

        String recommend = "";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT name FROM ramen_shops WHERE taste=? OR soup=? OR spicy=? LIMIT 1";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, taste);
                ps.setString(2, soup);
                ps.setString(3, spicy);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        recommend = rs.getString("name");
                    } else {
                        recommend = "条件に合うラーメン店が見つかりませんでした";
                    }
                }
            }
        } catch (SQLException e) {
            recommend = "DBエラー: " + e.getMessage();
        }

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"recommend\":\"" + recommend + "\"}");
        out.flush();
    }
}
