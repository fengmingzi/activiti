package com.main.activity.csv;

import com.main.activity.common.utils.CsvUtil;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.List;

/**
 * @Author: 15227
 * @Description: //TODO
 * @Date: 2019/3/4 17:32
 */
public class CSVOrgController {


    public static void main(String[] args) {
        String encoding = "GB2312";
        String[] header = new String[]{"id","name","age"};
        String decollator = ",";
        String quote = "\"";
        File file = new File("C:\\Users\\15227\\Desktop\\任务中心相关\\test.csv");
//        File file = uploadFile.getFile();
        CsvUtil csvUtil = new CsvUtil();
        List<CSVRecord> csvRecords = csvUtil.readCsvFile(file, header, encoding, decollator, quote);
    }

}
