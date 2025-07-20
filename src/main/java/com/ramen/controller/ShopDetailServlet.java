package com.ramen.controller;

import com.ramen.entity.RamenShop;
import com.ramen.model.RamenShopDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopDetail")
public class ShopDetailServlet extends HttpServlet {
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
        request.getRequestDispatcher("/shopDetail.jsp").forward(request, response);
    }
}
