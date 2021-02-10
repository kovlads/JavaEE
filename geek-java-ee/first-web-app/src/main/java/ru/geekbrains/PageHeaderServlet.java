package ru.geekbrains;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageHeaderServlet", urlPatterns = {"/page_header"})
public class PageHeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = (String)req.getAttribute("pageHeader");
        resp.getWriter().println("<h1>" + header + "</h1>");

        resp.getWriter().println("<a href=\"" +req.getContextPath()+"/main\">Главная страница</a>");
        resp.getWriter().println("<a href=\"" +req.getContextPath()+"/catalog\">Каталог</a>");
        resp.getWriter().println("<a href=\"" +req.getContextPath()+"/cart\">Корзина</a>");
        resp.getWriter().println("<a href=\"" +req.getContextPath()+"/order\">Заказ</a>");
    }

}
