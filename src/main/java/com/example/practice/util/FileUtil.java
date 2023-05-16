package com.example.practice.util;

import com.aspose.words.Document;
import com.aspose.words.PdfSaveOptions;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
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
     * @param wordFile 输入的文件路径
     */
    public static void asposeWordToPDF(String wordFile) {
        try {
            Document wordDocument = new Document(wordFile);
            PdfSaveOptions pso = new PdfSaveOptions();
            wordDocument.save(FILE_TEMP_PDF, pso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
