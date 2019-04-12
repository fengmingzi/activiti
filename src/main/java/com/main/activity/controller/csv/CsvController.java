package com.main.activity.controller.csv;


import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @Author: 15227
 * @Description: 用的javacsv包
 * @Date: 2019/3/4 16:54
 */
public class CsvController {

    public void readeCsv() {
        try {

            ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
            //注明：或者可以通过前端上传的文件，用一个方法获取上传文件名uploadName(String)
            String csvFilePath = "C:\\Users\\Administrator\\Desktop/05122017.csv";
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("GBK"));    //解决中文编码

            String[] headers = reader.getHeaders();
            reader.readHeaders(); // 跳过表头,如果需要表头的话，不要写这句。

            while (reader.readRecord()) { //逐行读入除表头的数据
                csvList.add(reader.getValues());
            }
            reader.close();

            for (int row = 0; row < csvList.size(); row++) {

                String[] csvarr = csvList.get(row);

                String cell = csvList.get(row)[0]; //取得第row行第0列的数据，可以指定获取内容

                System.out.println("姓名：" + cell);

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }


    }
}