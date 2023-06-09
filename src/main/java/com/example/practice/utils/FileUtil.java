package com.example.practice.utils;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.words.Document;
import com.aspose.words.PdfSaveOptions;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author : HP
 * @date : 2023/4/17
 */
@Slf4j
@Service
@Component
public class FileUtil {

    public static final String FILE_TEMP_PDF = "D:\\tmp\\usercenter\\tempFile\\pdf_file\\Temp.pdf";

    /**
     * 如果文件夹不存在则创建文件夹，并返回该文件夹路径
     *
     * @param pathDirectory
     * @return
     */
    public static String getRootPath(String pathDirectory) {
        File file = new File(pathDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
        return pathDirectory = pathDirectory + File.separator;
    }

    /**
     * 加载ip白名单
     *
     * @param filePath ip白名单路径
     * @return 返回IP
     */
    public static Set<String> loadIpWhiteList(String filePath) {
        Set<String> ipWhitelist = new HashSet<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String ip = scanner.nextLine().trim();
                if (!ip.isEmpty()) {
                    ipWhitelist.add(ip);
                }
            }
        } catch (FileNotFoundException e) {
            log.info("无法加载IP白名单文件" + filePath);
            e.printStackTrace();
        }
        return ipWhitelist;
    }


    /**
     * word转pdf-在线预览
     *
     * @param response
     * @param file
     */
    public static void wordToPdf(HttpServletResponse response, File file) {
        XWPFDocument xwpfDocument = null;
        OutputStream outputStream = null;
        try {
            xwpfDocument = new XWPFDocument(new FileInputStream(file));
            PdfOptions pdfOptions = PdfOptions.create();
            outputStream = response.getOutputStream();
            PdfConverter.getInstance().convert(xwpfDocument, outputStream, pdfOptions);
            outputStream.close();
            xwpfDocument.close();
            log.info("pdf转word成功");
        } catch (IOException e) {
            log.info("word to pdf is error:{}", e.getMessage());
        } finally {
            try {
                if (xwpfDocument != null) {
                    xwpfDocument.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * word转pdf-保存到本地
     *
     * @param file
     */
    public static void wordToPdf(File file) {
        XWPFDocument xwpfDocument = null;
        FileOutputStream outputStream = null;
        try {
            xwpfDocument = new XWPFDocument(new FileInputStream(file));
            PdfOptions pdfOptions = PdfOptions.create();
            outputStream = new FileOutputStream(FILE_TEMP_PDF);
            PdfConverter.getInstance().convert(xwpfDocument, outputStream, pdfOptions);
            outputStream.close();
            xwpfDocument.close();
            log.info("pdf转word成功--fileUtil中的");
        } catch (IOException e) {
            log.info("word to pdf is error:{}", e.getMessage());
        } finally {
            try {
                if (xwpfDocument != null) {
                    xwpfDocument.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * word文档（.doc和.docx文件）-转pdf
     *
     * @param wordFilePath 输入的文件路径
     */
    public static void asposeWordToPDF(String wordFilePath) {
        try {
            Document wordDocument = new Document(wordFilePath);
            PdfSaveOptions pso = new PdfSaveOptions();
            wordDocument.save(FILE_TEMP_PDF, pso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * word文档（.doc和.docx文件）-转pdf
     *
     * @param wordSourceFilePath 输入的文件file
     */
    public static void asposeWordToPDF(File wordSourceFilePath, String outputFilePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(wordSourceFilePath);
            Document wordDocument = new Document(fileInputStream);
            PdfSaveOptions pso = new PdfSaveOptions();
            FileOutputStream outputStream = new FileOutputStream(outputFilePath);
            wordDocument.save(outputStream, pso);
            log.info("使用aspose方案将 word 转pdf成功===={}", DateUtil.getDefaultTime());
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ppt转pdf
     *
     * @param pptFilePath
     */
    public static void asposePptToPdf(String pptFilePath) {
        try {
            Presentation pres = new Presentation(pptFilePath);
            FileOutputStream outputStream = new FileOutputStream("D:\\tmp\\usercenter\\tempFile\\pdf_file\\outputPpt.pdf");
            pres.save(outputStream, SaveFormat.Pdf);
            log.info("使用aspose方案将 ppt 转pdf成功===={}", DateUtil.getDefaultTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ppf转pdf,输出到本地路径
     *
     * @param pptSourceFilePath 源路径
     * @param outputFilePath    输出路径
     */
    public static void asposePptToPdf(File pptSourceFilePath, String outputFilePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(pptSourceFilePath);
            Presentation pres = new Presentation(fileInputStream);
            FileOutputStream outputStream = new FileOutputStream(outputFilePath);
            pres.save(outputStream, SaveFormat.Pdf);
            log.info("使用aspose方案将 ppt 转pdf成功===={}", DateUtil.getDefaultTime());
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void asposeExcelToPdf(String excelFilePath) {
        try {
            Workbook workbook = new Workbook(excelFilePath);
            com.aspose.cells.PdfSaveOptions pdfSaveOptions = new com.aspose.cells.PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);
            // 获取第一个工作表
            Worksheet worksheet = workbook.getWorksheets().get(0);
            // 设置水印文本
            String watermarkText = "liudy23  " + DateUtil.getDefaultTime();
            worksheet.getPageSetup().setHeader(0, watermarkText);
            FileOutputStream outputStream = new FileOutputStream("D:\\tmp\\usercenter\\tempFile\\pdf_file\\outputExcel.pdf");
            workbook.save(outputStream, pdfSaveOptions);
            log.info("使用aspose方案将 excel 转 pdf 成功===={}", DateUtil.getDefaultTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * excel转pdf
     *
     * @param excelSourceFile 文件源路径
     * @param outputFilePath  输出到本地路径
     */
    public static void asposeExcelToPdf(File excelSourceFile, String outputFilePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(excelSourceFile);
            Workbook workbook = new Workbook(fileInputStream);
            com.aspose.cells.PdfSaveOptions pdfSaveOptions = new com.aspose.cells.PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);
            // 获取第一个工作表
            Worksheet worksheet = workbook.getWorksheets().get(0);
            // 设置水印文本
            String watermarkText = "liudy23  " + DateUtil.getDefaultTime();
            worksheet.getPageSetup().setHeader(0, watermarkText);
            FileOutputStream outputStream = new FileOutputStream(outputFilePath);
            workbook.save(outputStream, pdfSaveOptions);
            log.info("使用aspose方案将 excel 转 pdf 成功===={}", DateUtil.getDefaultTime());
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getLicence() {
        boolean result = false;
        try {
            InputStream is = FileUtil.class.getClassLoader().getResourceAsStream("D:\\az\\maven\\repository\\com\\aspose\\License.xml");
            // InputStream is = new FileInputStream(new File("D:\\az\\maven\\repository\\com\\aspose\\License.xml"));

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 输入文件获取图片流
     *
     * @param file
     * @return
     */
    public static BufferedImage getImagStream(File file) {
        PDDocument pdDocument = null;
        BufferedImage bufferedImage;
        try {
            // 使用 PDFBox 将 pdf 转成图片
            pdDocument = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
            int pageCount = pdDocument.getNumberOfPages();
            int totalWidth = 0;
            int totalHeight = 0;
            // 获取每页pdf的大小，计算总大小
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150);
                // 横向构图
                // totalWidth += image.getWidth();
                // totalHeight = Math.max(totalHeight, image.getHeight());
                totalWidth = Math.max(totalWidth, image.getWidth());
                totalHeight += image.getHeight();
            }

            // 合成一张空白图片
            bufferedImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            int x = 0;
            int y = 0;

            // 将每页pdf的内容画到空白图片上
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150);
                // bufferedImage.createGraphics().drawImage(image, x, 0, null);
                // x += image.getWidth();
                bufferedImage.createGraphics().drawImage(image, 0, y, null);
                y += image.getHeight();
            }
            // bufferedImage = pdfRenderer.renderImage(0);
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdDocument != null) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
