package com.example.practice.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author : HP
 * @date : 2023/6/9
 */
@Slf4j
@Service
@Component
public class ImageUtils {

    /**
     * 添加多处水印
     *
     * @param image     输入的图片流文件
     * @param watermark 水印内容
     * @param font      字体
     * @param color     颜色
     * @param alpha     水印深度
     * @return
     */
    public static Graphics2D addMultipleWatermarkToImage(BufferedImage image, String watermark, Font font, Color color, float alpha) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(color);
        // 计算水印宽度和高度
        FontMetrics fontMetrics = g2d.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(watermark);
        int textHeight = fontMetrics.getHeight();

        // 计算图片中心坐标
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int centerX = imageWidth / 2;
        int centerY = imageHeight / 2;
        int spacingX = (int) (1.5 * textWidth);
        int spacingY = 5 * textHeight;
        // 调整旋转角度
        g2d.rotate(Math.toRadians(-30), centerX, centerY);
        // 在图片上多个位置添加水印
        log.info("{}====imageHeight:{},====imageWidth:{},====spacingX:{},====spacingY:{},", DateUtil.getDefaultTime(), imageHeight, imageWidth, spacingX, spacingY);
        for (int y = 0; y < imageHeight; y += spacingY) {
            for (int x = -imageWidth; x < imageWidth * 2; x += spacingX) {
                g2d.drawString(watermark, x, y);
            }
        }
        return g2d;
    }

}
