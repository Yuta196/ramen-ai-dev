package com.ramen.controller;

import com.ramen.entity.RamenShop;
import com.ramen.model.RamenShopDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopEdit")
public class ShopEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        RamenShop shop = null;
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                shop = new RamenShopDao().findById(id);
            } catch (NumberFormatException ignored) {}
        }
        request.setAttribute("shop", shop);
        request.getRequestDispatcher("/shopEdit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        int id = Integer.parseInt(idStr);
        RamenShop shop = new RamenShop(id, name, address, description);
        boolean result = new RamenShopDao().update(shop);
        if (result) {
            response.sendRedirect("recommend");
        } else {
            request.setAttribute("error", "更新に失敗しました");
            request.setAttribute("shop", shop);
            request.getRequestDispatcher("/shopEdit.jsp").forward(request, response);
        }
    }
}
