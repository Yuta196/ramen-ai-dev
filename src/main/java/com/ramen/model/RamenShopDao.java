package com.ramen.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RamenShopDao {
    private static final String URL = "jdbc:mysql://localhost:3306/ramen_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // 環境に合わせて変更

    public List<String> findRecommendShops(String taste, String soup, String spicy) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT name FROM ramen_shops WHERE taste=? OR soup=? OR spicy=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, taste);
            ps.setString(2, soup);
            ps.setString(3, spicy);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
package com.ramen.model;

import com.ramen.entity.RamenShop;
import java.util.ArrayList;
import java.util.List;

public class RamenShopDao {
    public List<RamenShop> findAll() {
        List<RamenShop> list = new ArrayList<>();
        list.add(new RamenShop("ラーメン一郎", "東京都新宿区", "濃厚豚骨スープが自慢"));
        list.add(new RamenShop("ラーメン二郎", "東京都渋谷区", "野菜たっぷりガッツリ系"));
        list.add(new RamenShop("ラーメン三郎", "東京都豊島区", "あっさり醤油味"));
        return list;
    }
}
