package com.ramen.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.ramen.model.RamenShopDao;

public class RamenRecommendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String taste = request.getParameter("taste");
        String soup = request.getParameter("soup");
        String spicy = request.getParameter("spicy");

        RamenShopDao dao = new RamenShopDao();
        List<String> shops = dao.findRecommendShops(taste, soup, spicy);

        // APIリクエストの場合はJSONで返す（既存処理）
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"recommend\": " + shops.toString() + "}");
            out.flush();
        } else {
            // 通常の画面遷移の場合はJSPへフォワード
            request.setAttribute("recommendList", shops);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/recommend.jsp");
            dispatcher.forward(request, response);
        }
    }
}
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
