
package com.ramen.model;

import com.ramen.entity.RamenShop;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class RamenShopDao {
    private static final String URL = "jdbc:mysql://localhost:3306/ramen_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root"; // あなたのMySQLユーザー名に合わせて変更
    private static final String PASSWORD = "password"; // あなたのMySQLパスワードに合わせて変更

    public List<RamenShop> findAll() {
        List<RamenShop> list = new ArrayList<>();
        String sql = "SELECT name, address, description FROM ramen_shops";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String description = rs.getString("description");
                list.add(new RamenShop(name, address, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
