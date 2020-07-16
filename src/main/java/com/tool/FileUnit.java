package com.tool;

import com.action.Element;
import com.logger.LoggerControler;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.yaml.snakeyaml.Yaml;
import sun.plugin.javascript.navig.LinkArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileUnit {
    private static LoggerControler log = LoggerControler.getLog(FileUnit.class);

    /**
     * 读取txt文件,结果存入一个动态数组返回
     * @param path 文件路径
     * @return 结果存入动态数据返回
     */
    public ArrayList readTxtFile(String path){
        ArrayList list=new ArrayList();
        try{
            String encoding = "utf-8";
            File file = new File(path);
            if(file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null){
                    list.add(lineTxt);
                }
                read.close();
            }else
                log.error("File is not found:"+path);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return list;
    }

    /**
     * 通过追加的形式写入txt文件
     * @param path 文件路径
     * @param content 写入内容
     */
    public void writeTxtFile(String path,String content){
        BufferedWriter out = null;
        try{
            File file =new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            out.write(content+"\r\n");
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            try{
                out.close();
            }catch (IOException e){
                log.error(e.getMessage(),e);
            }
        }
    }

    public void readExcelColumn(String path,int colNum){
        try{
            POIFSFileSystem fileSystem =new POIFSFileSystem(new FileInputStream(path));
            HSSFWorkbook workbook =new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workbook.getSheet("sheet1");
            int totalRow = sheet.getLastRowNum();
            log.info("TotalRow:"+totalRow);
            for(int i=1;i<=totalRow;i++){
                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = row.getCell(colNum);
                log.info("第"+i+"列数据:");
            }
        }catch (IOException e){
            log.error(e.getMessage(),e);
        }
    }

    /**
     * 获取某个单元格的数据
     * @param path 文件路径
     * @param rowNum 行号
     * @param colNum 列号
     * @return 返回单元格内容
     */
    public String readExcelCell(String path, int rowNum, int colNum){
        String text =null;
        try{
            POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(path));
            HSSFWorkbook workbook =new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workbook.getSheet("sheet1");
            HSSFRow row = sheet.getRow(rowNum);
            HSSFCell cell =row.getCell(colNum);
            text = cell.toString();
            rowNum = rowNum +1;
            colNum = colNum+1;
            log.info("第"+ rowNum + "行第" + colNum + "列的数据：" + cell);
        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 解析Yaml文件
     * @param path Yaml文件路径
     * @return 返回一个Map类型数据
     */
    public Map<String,Object> getYaml(String path){
        Map<String,Object> map = null;
        try{
            FileInputStream file = new FileInputStream(path);
            Yaml yaml = new Yaml();
            map = (Map<String,Object>)yaml.load(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        return map;
    }
}
