package web.filter;

import dao.LogDao;
import dao.impl.LogDaoImpl;

import javax.servlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignOnFilter implements Filter {
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

        String msg = "Sign on";
        logDao.insertLog(username,time,msg);

        System.out.println("SignOnFilter catches req........");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("SignOnFilter catches resp........");
    }

    @Override
    public void destroy() {
        logDao = null;
    }
}
