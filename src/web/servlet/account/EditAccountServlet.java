package web.servlet.account;

import domain.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/editAccount.jsp";   // 编辑失败时跳转

    private String msg;

    private String password;
    private String repeatedPassword;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        password = req.getParameter("password");
        repeatedPassword = req.getParameter("repeatedPassword");


        // 检验合法性
        if(!validate()){
            req.setAttribute("editMsg",this.msg); // 用于jsp中获取错误提示信息
            req.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();
            Account currentAccount = (Account) req.getSession().getAttribute("loginAccount");
            initAccount(currentAccount,req);
            accountService.updateAccount(currentAccount);  // 修改数据库信息

            req.getSession().setAttribute("loginAccount",null);

            resp.sendRedirect("mainForm");    // 跳转回购物主界面
        }

    }
    private boolean validate(){
        if(this.password == null || this.password.isEmpty()){
            msg = "密码不能为空！";
            return false;
        }
        if(this.repeatedPassword == null || this.repeatedPassword.isEmpty()){
            msg = "请重复确认密码！";
            return false;
        }
        if(!this.password.equals(this.repeatedPassword)){
            msg = "两次输入的密码不一致，请重新输入！";
            return false;
        }
        // 其他部分的判断逻辑
        return true;
    }

    private void initAccount(Account account,HttpServletRequest req){
        account.setPassword(this.password);
        account.setFirstName(req.getParameter("account.firstName"));
        account.setLastName(req.getParameter("account.lastName"));
        account.setEmail(req.getParameter("account.email"));
        account.setPhone(req.getParameter("account.phone"));
        account.setAddress1(req.getParameter("account.address1"));
        account.setAddress2(req.getParameter("account.address2"));
        account.setCity(req.getParameter("account.city"));
        account.setState(req.getParameter("account.state"));
        account.setZip(req.getParameter("account.zip"));
        account.setCountry(req.getParameter("account.country"));
        account.setLanguagePreference(req.getParameter("account.languagePreference"));
        account.setFavouriteCategoryId(req.getParameter("account.favouriteCategoryId"));
        account.setListOption(Boolean.parseBoolean(req.getParameter("account.listOption")));
        account.setBannerOption(Boolean.parseBoolean(req.getParameter("account.bannerOption")));
    }
}
