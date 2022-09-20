
package com.polypro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XJdbc1 {
    private static String driver="com.microsoft.sqlsever.jdbc.SQLServerDriver";
    //com.mysql.jdbc.driver
    private static String dburl="jdbc:sqlserver://localhost:1433;databaseName=EduSys";
    //"jdbc:mysql://localhost:3306"
    private static String username="sa";
    private static String password="123";
    
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(XJdbc1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Xay dung PreparedStatement
    public static PreparedStatement preparedStatement(String sql, Object...args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){ //{call sp_nam ?}
            pstmt = connection.prepareCall(sql); //store procedure
        }else {
            pstmt = connection.prepareStatement(sql);
        }
        for(int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]); //ps.setString(1, hv.getHoTen());
        }
        return pstmt;
    }
    
    public static String getRank(double diem) {
        String xepLoai = "Xuất sắc";
        if (diem < 0) {
            xepLoai = "Chưa nhập";

        } else if (diem < 3) {
            xepLoai = "Kém";

        } else if (diem < 5) {
            xepLoai = "Yếu";

        } else if (diem < 6.5) {

            xepLoai = "Trung bình";

        } else if (diem < 7.5) {
            xepLoai = "Khá";

        } else if (diem < 9) {
            xepLoai = "Giỏi";

        }
        return xepLoai;
    }
    
//        public static PreparedStatement preparedStatement(String sql,Object...args)throws SQLException{
//        Connection con=DriverManager.getConnection(dburl, username, password);
//        PreparedStatement pstmt=null;
//        if(sql.startsWith("{"))pstmt=con.prepareCall(sql);    //có thể gán biến kiểu PreparedStatement là prepareCall (CallableStatement)
//        else pstmt=con.prepareStatement(sql);
//        for(int i=0; i<args.length;i++){
//            pstmt.setObject(i+1, args[i]);
//        }
//        return pstmt;
//    }
    
    //Thuc hien cau lenh SQL thaotac INSERT,UPDATE,DELETE hoac thu tuc luu thaoo tac du lieu
    public static void executeUpdate(String sql, Object...args) { //giup thuc hien cau leh INSERT,UPDATE,DELETE
        try {
            PreparedStatement stmt = preparedStatement(sql, args);
            try {
                stmt.executeUpdate();
            }
            finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static ResultSet executeQuery(String sql, Object...args) {//giup thuc hien cau lenh select hoac thu tuc luu truy van du leu
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try{
                    return pstmt.executeQuery();
        }finally {
                //pstmt.getConnection().close();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    public static Object value(String sql, Object...args) {//select, truy van tra ve duy nhat 1 bang ghi
        try {
            ResultSet rs = XJdbc1.executeQuery(sql, args);
            if(rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
