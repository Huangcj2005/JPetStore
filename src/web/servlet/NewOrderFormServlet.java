package web.servlet;

import domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 跳转至新建订单
public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_SIGN_ON_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");  // 取用户 判断是否登录

        if(loginAccount == null){   // 未登录 跳转至登录
            resp.sendRedirect("signonForm");
        }else{
            req.getRequestDispatcher(NEW_SIGN_ON_FORM).forward(req,resp);
        }
    }
}
