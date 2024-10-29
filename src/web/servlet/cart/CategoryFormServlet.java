package web.servlet.cart;

import domain.Category;
import domain.Product;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CategoryFormServlet extends HttpServlet {

    private static final String CATEGORY_FORM = "/WEB-INF/jsp/catalog/category.jsp";
    private CatalogService catalogService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        catalogService = new CatalogService();
        // 依据 categoryId 获取商品信息 并放入session
        Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        HttpSession session = req.getSession();
        session.setAttribute("categoryId",categoryId);
        session.setAttribute("productList",productList);
        req.getRequestDispatcher(CATEGORY_FORM).forward(req,resp);
    }
}
