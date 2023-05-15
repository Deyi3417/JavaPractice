package com.example.practice.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author : HP
 * @date : 2023/4/17
 */
@Slf4j
public class FileUtil {

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

}
