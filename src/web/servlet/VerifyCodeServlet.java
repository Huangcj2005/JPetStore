package web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 60;
        int height = 30;
        String data = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789abcdefghijklmnpqrstuvwxyz";
        Random random = new Random();

        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();    // 获取绘画对象(画板)

        // 设置边框
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(1, 1, width-2, height-2);

        // 存放4个随机字符
        StringBuilder builder = new StringBuilder();

        //设置字体颜色
        graphics.setFont(new Font("思源黑体", Font.BOLD&Font.ITALIC, 20));
        for(int i = 0 ; i < 4 ;i ++){
            //随机颜色
            graphics.setColor(new Color(random.nextInt(255),random.nextInt(255), random.nextInt(255)));

            //随机字符
            int index = random.nextInt(data.length());
            String str = data.substring(index, index + 1);
            builder.append(str);

            graphics.drawString(str, (width / 6) * (i + 1) , 20);
        }
        // 绘制噪音
        for(int j=0,n=random.nextInt(100);j<n;j++){
            graphics.setColor(Color.RED);
            graphics.fillRect(random.nextInt(width),random.nextInt(height),1,1);
        }

        String tempStr = builder.toString();
        req.getSession().setAttribute("verifyCode",tempStr);    // 验证用

        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
    }
}
