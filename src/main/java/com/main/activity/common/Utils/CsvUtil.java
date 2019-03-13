package com.main.activity.common.Utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: csv格式文件操作工具类
 * @Date: 2019/3/5 11:14
 */
@Slf4j
public class CsvUtil {

    /**
     * @author fengguang xu
     * @description 读csv
     * @date 2019/3/5 14:11
     * @param file	csv文件
     * @param header	表头
     * @param encoding	编码格式
     * @param delimiter	分隔符
     * @param quote	引用符号
     * @return java.util.List<org.apache.commons.csv.CSVRecord>
     */
    public List<CSVRecord> readCsvFile(File file, String[] header, String encoding, String delimiter, String quote) {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;
        // 创建CSVFormat（header mapping）
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(header);
        // CSVFormat csvFileFormat = CSVFormat.newFormat(delimiter);
        /*if (delimiter != null && delimiter.toCharArray().length > 0 && stopCode != null && stopCode.toCharArray().length > 0) {
            if ((delimiter.contains(",") || delimiter.contains("，")) && (stopCode.contains("\"") || stopCode.contains("“"))) {
                csvFileFormat = CSVFormat.DEFAULT.withHeader(header);
            } else {
                final char Delimiter = delimiter.toCharArray()[0];
                final char stop_code = stopCode.toCharArray()[0];
                csvFileFormat = CSVFormat.EXCEL.withDelimiter(Delimiter)
                        .withHeader(header).withQuote(stop_code)
                        .withIgnoreEmptyLines();
            }
        } else {
            csvFileFormat = CSVFormat.DEFAULT.withHeader(header);
        }*/

        List<CSVRecord> csvRecords = new ArrayList<>();
        try {
            // 初始化FileReader object
            fileReader = new FileReader(file);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encoding);
            // 初始化 CSVParser object
            csvFileParser = new CSVParser(isr, csvFileFormat);
            // CSV文件records
            csvRecords = csvFileParser.getRecords();

            /*for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                String name = record.get("name");
                System.out.println("name:"+name);
                Iterator<String> iterator = record.iterator();
                listValue.add(iterator);
            }*/
            return csvRecords;
        } catch (Exception e) {
            log.error("读取csv异常", e);
            return null;
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (csvFileParser != null) {
                    csvFileParser.close();
                }
                return csvRecords;
            } catch (IOException e) {
                log.error("读取csv关闭流时异常", e);
                return null;
            }
        }
    }
}
