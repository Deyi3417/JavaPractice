package com.example.practice.common.convert.concrete;

import com.example.practice.common.convert.FileConvert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author : HP
 * @date : 2023/5/17
 */
@Service
public class TextConvertToImg extends FileConvert {

    private static final String FILE_SAVE_PATH = "D:\\tmp\\usercenter\\tempFile\\png_file\\liudy23.png";

    @Override
    public BufferedImage covertToImgStream(File file) {
        try {
            Path path = Paths.get(file.getPath());
            String textString = Files.lines(path).collect(Collectors.joining("\n"));
            System.out.println("输出文本" + textString);
            BufferedImage bufferedImage = convertTextToImage(textString);
            ImageIO.write(bufferedImage, "png", new File(FILE_SAVE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage convertTextToImage(String text) {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        FontMetrics fontMetrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
                .getGraphics().getFontMetrics(font);

        int lineHeight = fontMetrics.getHeight();
        int margin = 20;

        // 计算文本的行数和最大行宽
        String[] lines = text.split("\n");
        int lineCount = lines.length;
        int maxLineWidth = 0;

        for (String line : lines) {
            int lineWidth = fontMetrics.stringWidth(line);
            maxLineWidth = Math.max(maxLineWidth, lineWidth);
        }

        // 计算图像的宽度和高度
        int imageWidth = maxLineWidth + 2 * margin;
        int imageHeight = lineHeight * lineCount + 2 * margin;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);

        int currentY = margin;

        for (String line : lines) {
            graphics.drawString(line, margin, currentY);
            currentY += lineHeight;
        }

        graphics.dispose();
        return image;
    }

}
