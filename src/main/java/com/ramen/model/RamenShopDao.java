package com.ramen.model;

import com.ramen.entity.RamenShop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RamenShopDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ramen_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Tokyo";
    private static final String DB_USER = "root"; // 必要に応じて変更
    private static final String DB_PASS = "password";     // 必要に応じて変更

    public List<RamenShop> findAll() {
        List<RamenShop> list = new ArrayList<>();
        String sql = "SELECT id, name, address, description FROM ramen_shops";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String description = rs.getString("description");
                list.add(new RamenShop(id, name, address, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public RamenShop findById(int id) {
        String sql = "SELECT id, name, address, description FROM ramen_shops WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new RamenShop(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("description")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 新規追加（エラー時はメッセージ返却、成功時はnull）
    public String insert(RamenShop shop) {
        String sql = "INSERT INTO ramen_shops (name, address, description) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, shop.getName());
            ps.setString(2, shop.getAddress());
            ps.setString(3, shop.getDescription());
            int result = ps.executeUpdate();
            if (result > 0) {
                return null;
            } else {
                String msg = "登録に失敗しました（SQL実行結果0件）";
                System.err.println(msg);
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // 編集
    public boolean update(RamenShop shop) {
        String sql = "UPDATE ramen_shops SET name=?, address=?, description=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, shop.getName());
            ps.setString(2, shop.getAddress());
            ps.setString(3, shop.getDescription());
            ps.setInt(4, shop.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 削除
    public boolean delete(int id) {
        String sql = "DELETE FROM ramen_shops WHERE id=?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
