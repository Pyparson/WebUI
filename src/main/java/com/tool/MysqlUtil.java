package com.tool;

import com.logger.LoggerControler;

import java.sql.*;
import java.util.ArrayList;

public class MysqlUtil {
    private static LoggerControler log = LoggerControler.getLog(FileUnit.class);
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PWD = "123456";
    Connection conn = null;
    Statement stmt = null;
    String title =null;

    /**
     * SQL查询，具体返回某一列的值
     * @param sql 查询SQL语句
     * @param key 具体字段值
     * @return 查询结果
     */
    public ArrayList getSqlValues(String sql,String key){
        ArrayList list = new ArrayList();
        try{
            Class.forName(JDBC_DRIVER);
            log.info("Connect Database...");
            conn = DriverManager.getConnection(DB_URL,USER,PWD);
            log.info("实例化Statement对象...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                title = rs.getString(key);
                list.add(title);
                log.info("查询结果Title:"+title);
//                return title;
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException se){
            log.error(se.getMessage(),se);
//            se.printStackTrace();
        }catch (Exception e){
            log.error(e.getMessage(),e);
//            e.printStackTrace();
        }finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch (SQLException se2){
                log.error(se2.getMessage(),se2);
            }try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se){
                log.error(se.getMessage(),se);
            }
        }
        return list;
    }
}
