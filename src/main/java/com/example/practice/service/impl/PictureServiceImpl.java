package com.example.practice.service.impl;

import com.example.practice.service.PictureService;
import com.example.practice.utils.DateUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author : HP
 * @date : 2023/4/14
 */
@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    /**
     * 在图片上添加水印
     *
     * @param srcFile   原始图片文件路径
     * @param destFile  添加水印后的图片文件路径
     * @param watermark 水印文本
     * @param font      水印字体
     * @param color     水印颜色
     * @param x         水印横坐标
     * @param y         水印纵坐标
     * @param alpha     水印透明度
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
        this.addWatermarkToImage(srcFile, destFile, watermark, arial, Color.BLACK, x, y, 0.5f);
    }

    @Override
    public void addWatermarkToImage(String srcFile, String destFile, String watermark, int x, int y, HttpServletResponse response) throws IOException {
        Font arial = new Font("微软雅黑", Font.BOLD, 70);
        this.addWatermarkToImage(srcFile, destFile, watermark, arial, Color.RED, x, y, 0.1f, response);
    }

    /**
     * 在一处添加水印
     *
     * @param srcFile
     * @param destFile
     * @param watermark
     * @param font
     * @param color
     * @param x
     * @param y
     * @param alpha
     * @param response
     * @throws IOException
     */
    public void addWatermarkToImage(String srcFile, String destFile, String watermark, Font font, Color color, int x, int y, float alpha, HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        BufferedImage image = ImageIO.read(new File(srcFile));
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        // 设置水印透明度
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

        g2d.drawString(watermark, x, y);
        ImageIO.write(image, "jpg", os);
        g2d.dispose();
        os.close();
    }


    /**
     * 添加多处水印
     */
    public void addMultipleWatermarksToImage() {
        String imagePath = "C:\\Users\\HP\\Desktop\\PIC\\fileToImg.png";
        String watermarkText = "liudy23 80048349 SANY TECH";
        int spacing = 400; // 水印之间的间距
        try {
            // 读取原始图片
            BufferedImage image = ImageIO.read(new File(imagePath));
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
            g2d.setComposite(alphaComposite);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Font arial = new Font("微软雅黑", Font.BOLD, 60);
            g2d.setFont(arial);
            g2d.setColor(Color.RED);


            FontMetrics fontMetrics = g2d.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(watermarkText);
            int textHeight = fontMetrics.getHeight();

            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            System.out.println("====textWidth:" + textWidth + "====textHeight:" + textHeight);
            int spacingX = (int) (1.5 * textWidth);
            int spacingY = (int) (5 * textHeight);
            int centerX = imageWidth / 2;
            int centerY = imageHeight / 2;
            g2d.rotate(Math.toRadians(-25), centerX, centerY);
            // 在图片的多个位置添加水印
            System.out.println("====imageHeight:" + imageHeight + "====imageWidth:" + imageWidth + "====spacingY:" + spacingY + "====spacingX:" + spacingX);
            for (int y = 0; y < imageHeight; y += spacingY) {
                for (int x = -imageWidth; x < imageWidth * 2; x += spacingX) {
                    g2d.drawString(watermarkText, x, y);
                }
            }

            // 将水印图像保存到文件
            String outputImagePath = "C:\\Users\\HP\\Desktop\\PIC\\outputImage3.png";
            ImageIO.write(image, "jpg", new File(outputImagePath));
            g2d.dispose();
            System.out.println("水印添加成功，已保存为：" + outputImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateQRCodeImage(String text, HttpServletResponse response) {
        this.generateQRCodeImage(text, 50, 50, response);
    }

    @Override
    public void generateQRCodeImage(String text, int width, int height, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        OutputStream os = null;
        try {
            // 创建二维码对象
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            byte[] contentBytes = text.getBytes(StandardCharsets.UTF_8);
            BitMatrix bitMatrix = qrCodeWriter.encode(new String(contentBytes, "ISO-8859-1"), BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            // 将二维码图片写入response对象
            response.setContentType("image/png");
            os = response.getOutputStream();
            ImageIO.write(bufferedImage, "png", os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
