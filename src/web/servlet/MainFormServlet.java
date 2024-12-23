package web.servlet;

import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainFormServlet extends HttpServlet {

    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("flag",1);
        req.getRequestDispatcher(MAIN_FORM).forward(req,resp);
    }

    // 接住由 doPost 传来的请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
