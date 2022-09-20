package com.polypro.dao;

import com.polypro.entity.ChuyenDe;
import com.polypro.utils.XJdbc;
import com.polypro.utils.XJdbc1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    //ma chuyen de: String ->key -> search
    public void insert(ChuyenDe model) {
        String sql = "INSERT INTO ChuyenDe(MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES(?,?,?,?,?,?)";
        XJdbc.executeUpdate(sql,
                model.getMaCD(),
                model.getTenCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getHinh(),
                model.getMoTa());
    }

    public void update(ChuyenDe model) {
        String sql = "UPDATE ChuyenDe SET TenCD=?, HocPhi=?,ThoiLuong=?,Hinh=?,MoTa=? WHERE MaCD=?";
        XJdbc.executeUpdate(sql,
                model.getTenCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getHinh(),
                model.getMoTa(),
                model.getMaCD());
    }

    public void delete(String MaCD) {
        String sql = "DELETE FROM ChuyenDe WHERE MaCD=?";
        XJdbc.executeUpdate(sql, MaCD);
    }

    public List<ChuyenDe> selectAll() {
        String sql="SELECT * FROM ChuyenDe";
        return selectBySql(sql);
    }

    public ChuyenDe selectById(String macd) {
        String sql = "SELECT * FROM ChuyenDe WHERE MaCD=?";
        List<ChuyenDe> list = selectBySql(sql, macd);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.executeQuery(sql, args);
                while (rs.next()) {
                    ChuyenDe entity = new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setMoTa(rs.getString("MoTa"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

//    protected List<ChuyenDe>selectBySql(String sql, Object...args) {
//        List<ChuyenDe>list=new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                rs = XJdbc.executeQuery(sql,args);
//                while(rs.next()) {
//                    ChuyenDe model=readFromResultSet(rs);
//                    list.add(model);
//                }
//            }
//            finally{
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return list;
//    }
//    private ChuyenDe readFromResultSet(ResultSet rs)throws SQLException{
//        ChuyenDe model = new ChuyenDe();
//        model.setMaCD(rs.getString("MaCD"));
//        model.setHinh(rs.getString("Hinh"));
//        model.setHocPhi(rs.getDouble("HocPhi"));
//        model.setMoTa(rs.getString("MoTa"));
//        model.setTenCD(rs.getString("TenCD"));
//        model.setThoiLuong(rs.getInt("ThoiLuong"));
//        return model;
//    }
}
