package com.example.practice.service.impl;

import com.example.practice.service.PictureService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : HP
 * @date : 2023/4/14
 */
@Service
public class PictureServiceImpl implements PictureService {

    /**
     * 在图片上添加水印
     *
     * @param srcFile    原始图片文件路径
     * @param destFile   添加水印后的图片文件路径
     * @param watermark  水印文本
     * @param font       水印字体
     * @param color      水印颜色
     * @param x          水印横坐标
     * @param y          水印纵坐标
     * @param alpha      水印透明度
     * @throws IOException
     */
    public void addWatermarkToImage(String srcFile, String destFile, String watermark, Font font, Color color, int x, int y, float alpha) throws IOException {
        BufferedImage image = ImageIO.read(new File(srcFile));
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        Graphics2D g2d = (Graphics2D) image.getGraphics();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(color);
        int stringWidth = g2d.getFontMetrics().stringWidth(watermark);
        int stringHeight = g2d.getFontMetrics().getHeight();
        x = (imageWidth - stringWidth) / 2;
        y = (imageHeight - stringHeight) / 2;
        g2d.drawString(watermark, x, y);
        ImageIO.write(image, "jpg", new File(destFile));
        g2d.dispose();
    }

    @Override
    public void addWatermarkToImage(String srcFile, String destFile, String watermark, int x, int y) throws IOException {
        Font arial = new Font("Arial", Font.PLAIN, 20);
        this.addWatermarkToImage(srcFile,destFile,watermark,arial, Color.BLACK, x,y,0.5f);
    }

    @Override
    public void addWatermarkToImage(String srcFile, String destFile, String watermark, int x, int y, HttpServletResponse response) throws IOException {
        Font arial = new Font("微软雅黑", Font.BOLD, 70);
        this.addWatermarkToImage(srcFile,destFile,watermark,arial, Color.BLACK, x,y,0.2f, response);
    }

    public void addWatermarkToImage(String srcFile, String destFile, String watermark, Font font, Color color, int x, int y, float alpha, HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        BufferedImage image = ImageIO.read(new File(srcFile));
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(color);

        // 计算水印宽度和高度
        FontMetrics fontMetrics = g2d.getFontMetrics(font);
        int watermarkWidth = fontMetrics.stringWidth(watermark);
        int watermarkHeight = fontMetrics.getHeight();
        // 计算图片中心坐标
        int centerX = image.getWidth() / 2;
        int centerY = image.getHeight() / 2;
        // 计算水印起始坐标
        x = (int) (centerX - watermarkWidth / 2);
        y = (int) (centerY + watermarkHeight / 2);
        // 设置旋转角度和旋转中心
        g2d.rotate(Math.toRadians(325), centerX, centerY);

//        int imageWidth = image.getWidth();
//        int imageHeight = image.getHeight();
//        int stringWidth = g2d.getFontMetrics().stringWidth(watermark);
//        int stringHeight = g2d.getFontMetrics().getHeight();
//        x = (imageWidth - stringWidth) / 2;
//        y = (imageHeight - stringHeight) / 2;
        // 倾斜角度
//        g2d.rotate(Math.toRadians(315),x,y);
        g2d.drawString(watermark, x, y);
        ImageIO.write(image, "jpg",os);
        g2d.dispose();
        os.close();
    }

}
