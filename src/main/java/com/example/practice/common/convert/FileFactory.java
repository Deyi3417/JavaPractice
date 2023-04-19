package com.example.practice.common.convert;

/**
 * 文件转换工厂
 *
 * @author : HP
 * @date : 2023/4/18
 */
public interface FileFactory {

    /**
     * 根据类型创建不同的文件转换
     *
     * @param type 文件类型
     * @return
     */
    public FileConvert createConvert(String type);
}
