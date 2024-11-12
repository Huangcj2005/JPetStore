package web.filter;

import dao.LogDao;
import dao.impl.LogDaoImpl;

import javax.servlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddItemToCartFilter implements Filter {
    private LogDao logDao;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logDao = new LogDaoImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 设置编码
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();

        String time = sdf.format(date);
        String username = servletRequest.getParameter("username");
        String workingItemId = servletRequest.getParameter("workingItemId");

        String msg = "Add " + workingItemId + " to cart";
        logDao.insertLog(username,time,msg);

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        logDao = null;
    }
}
