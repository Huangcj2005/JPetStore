package web.servlet;

import domain.Item;
import domain.Product;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ItemFormServlet extends HttpServlet {
    private static final String ITEM_FORM = "/WEB-INF/jsp/item.jsp";
    private CatalogService catalogService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        catalogService = new CatalogService();
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();

        HttpSession session = req.getSession();
        session.setAttribute("item",item);
        session.setAttribute("product",product);
        req.getRequestDispatcher(ITEM_FORM).forward(req,resp);
    }
}
