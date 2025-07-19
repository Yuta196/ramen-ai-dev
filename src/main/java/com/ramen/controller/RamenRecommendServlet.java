package com.ramen.controller;

import com.ramen.entity.RamenShop;
import com.ramen.model.RamenShopDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recommend")
public class RamenRecommendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RamenShopDao dao = new RamenShopDao();
        List<RamenShop> shopList = dao.findAll();
        request.setAttribute("shopList", shopList);
        request.getRequestDispatcher("/recommend.jsp").forward(request, response);
    }
}
