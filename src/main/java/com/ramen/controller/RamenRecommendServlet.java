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

@WebServlet({"/recommend", "/admin"})
public class RamenRecommendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RamenShopDao dao = new RamenShopDao();
        String action = request.getServletPath();
        if ("/recommend".equals(action)) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String description = request.getParameter("description");
            List<RamenShop> shopList;
            if ((name != null && !name.isEmpty()) || (address != null && !address.isEmpty()) || (description != null && !description.isEmpty())) {
                shopList = dao.findByKeyword(name, address, description);
            } else {
                shopList = dao.findAll();
            }
            request.setAttribute("shopList", shopList);
            request.getRequestDispatcher("/recommend.jsp").forward(request, response);
        } else if ("/admin".equals(action)) {
            List<RamenShop> shopList = dao.findAll();
            request.setAttribute("shopList", shopList);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }
    }
}
