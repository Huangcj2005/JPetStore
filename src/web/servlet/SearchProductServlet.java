package web.servlet;

import domain.Product;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCT_FORM = "/WEB-INF/jsp/catalog/searchProduct.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");       // 获取关键字 用于数据库匹配

        CatalogService catalogService = new CatalogService();
        List<Product> viewProduct = catalogService.searchProductList(keyword);

        HttpSession session = req.getSession();
        session.setAttribute("viewProduct",viewProduct);
        session.setAttribute("flag",1);

        req.getRequestDispatcher(SEARCH_PRODUCT_FORM).forward(req,resp);
    }
}
