package com.ramen.controller;

import com.ramen.entity.RamenShop;
import com.ramen.model.RamenShopDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopAdd")
public class ShopAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/shopAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        RamenShop shop = new RamenShop(0, name, address, description);
        String error = new RamenShopDao().insert(shop);
        if (error == null) {
            response.sendRedirect("recommend");
        } else {
            request.setAttribute("error", "登録に失敗しました: " + error);
            // Tomcatログにも出力
            System.err.println("[ShopAddServlet] 登録エラー: " + error);
            request.getRequestDispatcher("/shopAdd.jsp").forward(request, response);
        }
    }
}
