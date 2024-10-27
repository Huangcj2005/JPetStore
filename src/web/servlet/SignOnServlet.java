package web.servlet;

import domain.Account;
import domain.Product;
import service.AccountService;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {
    private String username;
    private String password;
    private String msg;

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username = req.getParameter("username");
        password = req.getParameter("password");

        // 检验用户名密码的合法性
        if(!validate()){
            req.setAttribute("signOnMsg",this.msg); // 用于jsp中获取错误提示信息
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();
            Account loginAccount = accountService.getAccount(username,password);
            if(loginAccount == null){   // 未找到用户
                this.msg = "用户名或密码错误！";
                req.setAttribute("signOnMsg",this.msg);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
            }else{
                loginAccount.setPassword(null);
                HttpSession session = req.getSession();     // 会话跟踪
                session.setAttribute("loginAccount",loginAccount);

                // 页面右侧 favorite
                if(loginAccount.isListOption()){
                    CatalogService catalogService = new CatalogService();
                    List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                    session.setAttribute("myList",myList);
                }

                resp.sendRedirect("mainForm");
            }
        }
    }

    private boolean validate(){
        if(this.username == null || this.username.equals("")){
            msg = "用户名不能为空！";
            return false;
        }
        if(this.password == null || this.password.equals("")){
            msg = "密码不能为空！";
            return false;
        }
        return true;
    }
}
