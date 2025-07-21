package com.ramen.model;

import com.ramen.entity.RamenShop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RamenShopDao {
private static final String JDBC_URL = "jdbc:mysql://192.168.2.201:3306/ramen_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Tokyo";
private static final String DB_USER = "ramen_user"; // CentOS MariaDB用
private static final String DB_PASS = "0213yutA";     // CentOS MariaDB用

    // 検索（店名・住所・特徴のいずれかにキーワードが含まれる場合）
    public List<RamenShop> findByKeyword(String name, String address, String description) {
        List<RamenShop> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, name, address, description FROM ramen_shops WHERE 1=1");
        List<String> params = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        if (address != null && !address.isEmpty()) {
            sql.append(" AND address LIKE ?");
            params.add("%" + address + "%");
        }
        if (description != null && !description.isEmpty()) {
            sql.append(" AND description LIKE ?");
            params.add("%" + description + "%");
        }
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String n = rs.getString("name");
                    String a = rs.getString("address");
                    String d = rs.getString("description");
                    list.add(new RamenShop(id, n, a, d));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

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
