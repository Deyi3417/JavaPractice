package com.example.practice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.DateUtils;
import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.domain.vo.ExportDataVO;
import com.example.practice.service.ExcelHandlerService;
import com.example.practice.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author : HP
 * @date : 2023/4/17
 */
@Service
@Slf4j
public class ExcelHandlerServiceImpl implements ExcelHandlerService {

    @Resource
    private BasicProperties basicProperties;

    /**
     * 每批次导出的数据量
     */
    private static final int BATCH_SIZE = 100;

    /**
     * 异步线程池的大小
     */
    private static final int THREAD_POOL_SIZE = 11;

    

    @Override
    public void exportBatches(List<ExportDataVO> data) {
        ThreadPoolExecutor thread = new ThreadPoolExecutor(THREAD_POOL_SIZE, THREAD_POOL_SIZE, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture.runAsync(() ->{
            exportDataTestThread(data);
        }, thread);
        thread.shutdown();
    }

    @Override
    public void exportDataTestThread(List<ExportDataVO> data) {
        int totalSize = data.size();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // 计算分批次的数量
        int batchCount = (totalSize + BATCH_SIZE - 1) / BATCH_SIZE;
        String fileDirectory = basicProperties.getFileSavePath() + File.separator + DateUtils.format(new Date(), DateUtils.DATE_FORMAT_10);
        String filePathTemp = FileUtil.getRootPath(fileDirectory);
        for (int i = 0; i < batchCount; i++) {
            int startIndex = i * BATCH_SIZE;
            int endIndex = Math.min((i + 1) * BATCH_SIZE, totalSize);
            String fileName = "data_batch_" + System.currentTimeMillis() + ".xlsx";
            String filePath = filePathTemp + fileName;

            executorService.execute(() -> {

                List<ExportDataVO> batchData = data.subList(startIndex, endIndex);
                //将batchData导出到excel文件
//                ExcelWriter excelWriter = EasyExcel.write(filePath).build();
//                WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();
//                excelWriter.write(batchData, writeSheet);
//                excelWriter.finish();
                EasyExcel.write(filePath, ExportDataVO.class).sheet().doWrite(batchData);
                log.info("程序执行的怎么样了，are you ok ! everything will goes well");
            });
            log.info("程序执行了吗？==是的，执行了：{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            log.info("程序执行完毕");
        }
    }

    @Override
    public List<ExportDataVO> getExcelData(int size) {
        Random random = new Random();
        ArrayList<ExportDataVO> exportDataVOS = new ArrayList<>(size);
        for (int i = 0; i <= size; i++) {
            ExportDataVO vo = new ExportDataVO();
            vo.setId(i);
            vo.setUuid(UUID.randomUUID().toString());
            int suffix = random.nextInt(1001);
            String format = String.format("%d - liudy23", suffix);
            vo.setName(format);
            vo.setDate(DateUtils.format(new Date()));
            exportDataVOS.add(vo);
        }
        return exportDataVOS;
    }

    public static void main(String[] args) {

    }

}
