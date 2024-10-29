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
    private String username;
    private String password;
    private String repeatedPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username = req.getParameter("username");
        password = req.getParameter("password");
        repeatedPassword = req.getParameter("repeatedPassword");
        firstName = req.getParameter("account.firstName");
        lastName = req.getParameter("account.lastName");
        email = req.getParameter("account.email");
        phone = req.getParameter("account.req");
        address1 = req.getParameter("account.address1");
        address2 = req.getParameter("account.address2");
        city = req.getParameter("account.city");
        state = req.getParameter("account.state");
        zip = req.getParameter("account.zip");
        country = req.getParameter("account.country");
        favouriteCategoryId = req.getParameter("account.favouriteCategoryId");
        languagePreference = req.getParameter("account.languagePreference");
        listOption = Boolean.parseBoolean(req.getParameter("account.listOption"));
        bannerOption = Boolean.parseBoolean(req.getParameter("account.bannerOption"));

        // 检验合法性
        if(!validate()){
            req.setAttribute("editMsg",this.msg); // 用于jsp中获取错误提示信息
            req.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();
            Account updateAccount = new Account();
            initAccount(updateAccount);
            accountService.updateAccount(updateAccount);  // 修改数据库信息

            resp.sendRedirect("mainForm");    // 跳转回购物主界面
        }

    }
    private boolean validate(){
        if(this.password == null || this.password.equals("")){
            msg = "密码不能为空！";
            return false;
        }
        if(this.repeatedPassword == null || this.repeatedPassword.equals("")){
            msg = "密码不能为空！";
            return false;
        }
        if(!this.password.equals(this.repeatedPassword)){
            msg = "两次输入的密码不一致，请重新输入！";
            return false;
        }
        // 其他部分的判断逻辑
        return true;
    }

    private void initAccount(Account account){
        account.setUsername(this.username);
        account.setPassword(this.password);
        account.setBannerOption(this.bannerOption);
        account.setFirstName(this.firstName);
        account.setLastName(this.lastName);
        account.setEmail(this.email);
        account.setPhone(this.phone);
        account.setAddress1(this.address1);
        account.setAddress2(this.address2);
        account.setCity(this.city);
        account.setState(this.state);
        account.setZip(this.zip);
        account.setCountry(this.country);
        account.setFavouriteCategoryId(this.favouriteCategoryId);
        account.setLanguagePreference(this.languagePreference);
        account.setListOption(this.listOption);
        account.setBannerOption(this.bannerOption);
    }
}
