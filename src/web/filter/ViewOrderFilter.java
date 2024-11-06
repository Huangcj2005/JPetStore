package web.filter;

import javax.servlet.*;
import java.io.IOException;

public class ViewOrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 设置编码
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        StringBuffer creditCardBuffer = new StringBuffer(servletRequest.getParameter("order.creditCard"));   // 转为 StringBuffer 对象进行字符串替换
        creditCardBuffer.replace(4,13,"**** ****");
        String creditCard = creditCardBuffer.toString();

        System.out.println("ViewOrderFilter catches req..........");

        servletRequest.setAttribute("order.creditCard",creditCard); // 将修改后信息放回 req
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
