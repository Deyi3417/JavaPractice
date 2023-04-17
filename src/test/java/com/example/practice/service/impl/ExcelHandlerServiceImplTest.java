package com.example.practice.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author : HP
 * @date : 2023/4/17
 */
@SpringBootTest
public class ExcelHandlerServiceImplTest {
    @Resource
    private ExcelHandlerServiceImpl excelHandlerService;

    @Test
    public void exportBatches() {

        excelHandlerService.exportBatches(excelHandlerService.getExcelData(120));
    }
}