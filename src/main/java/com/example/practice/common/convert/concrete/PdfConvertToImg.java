package com.example.practice.common.convert.concrete;

import com.example.practice.common.convert.FileConvert;
import com.example.practice.utils.FileUtil;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Pdf文件转图片流
 *
 * @author : HP
 * @date : 2023/4/18
 */
@Component
public class PdfConvertToImg extends FileConvert {
    @Override
    public BufferedImage covertToImgStream(File file) {
        return FileUtil.getImagStream(file);
//        PDDocument pdDocument = null;
//        BufferedImage bufferedImage;
//        try {
//            // 使用 PDFBox 将 pdf 转成图片
//            pdDocument = PDDocument.load(file);
//            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
//            int pageCount = pdDocument.getNumberOfPages();
//            int totalWidth = 0;
//            int totalHeight = 0;
//            // 获取每页pdf的大小，计算总大小
//            for (int i = 0; i < pageCount; i++) {
//                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150);
//                // 横向构图
//                // totalWidth += image.getWidth();
//                // totalHeight = Math.max(totalHeight, image.getHeight());
//                totalWidth = Math.max(totalWidth, image.getWidth());
//                totalHeight += image.getHeight();
//            }
//
//            // 合成一张空白图片
//            bufferedImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
//            int x = 0;
//            int y = 0;
//
//            // 将每页pdf的内容画到空白图片上
//            for (int i = 0; i < pageCount; i++) {
//                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150);
//                // bufferedImage.createGraphics().drawImage(image, x, 0, null);
//                // x += image.getWidth();
//                bufferedImage.createGraphics().drawImage(image, 0, y, null);
//                y += image.getHeight();
//            }
//            // bufferedImage = pdfRenderer.renderImage(0);
//            return bufferedImage;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (pdDocument != null) {
//                try {
//                    pdDocument.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
    }
}
