package com.example.practice.service;

import com.example.practice.domain.vo.ExportDataVO;

import java.util.List;

/**
 * @author : HP
 * @date : 2023/4/17
 */
public interface ExcelHandlerService {

    void exportBatches(List<ExportDataVO> data);

    void exportDataTestThread(List<ExportDataVO> data);

    List<ExportDataVO> getExcelData(int size);
}
