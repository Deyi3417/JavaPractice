package com.example.practice.util;

import java.io.File;

/**
 * @author : HP
 * @date : 2023/4/17
 */
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
}
