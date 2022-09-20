package com.polypro.dao;

import com.polypro.entity.HocVien;
import com.polypro.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAO extends EduSysDAO<HocVien, String> {
    
    public List<HocVien> selectByKhoaHoc(int makh) {
        String sql = "select * from HocVien where makh=?";
        return this.selectBySql(sql, makh);
    }

    public void insert(HocVien model) {
        String sql = "INSERT INTO HocVien (MaKH, MaNH, Diem) VALUES(?,?,?)";
        XJdbc.executeUpdate(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem());
    }

    public void update(HocVien model) {
        String sql = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        XJdbc.executeUpdate(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem(),
                model.getMaHV());
    }
    

    public void delete(String MaHV) {
        String sql = "DELETE FROM HocVien WHERE MaHV=?";
        XJdbc.executeUpdate(sql, MaHV);
    }

    public List<HocVien> selectAll() {
        String sql = "SELECT * FROM HocVien";
        return selectBySql(sql);
    }

    public  HocVien selectById(String mahv) {
        String sql = "SELECT * FROM HocVien WHERE MaHV=?";
        List<HocVien> list = selectBySql(sql, mahv);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.executeQuery(sql, args);
                while (rs.next()) {
                    HocVien entity = new HocVien();
                    entity.setMaHV(rs.getInt("MaHV"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setDiem(rs.getDouble("Diem"));
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

//    private List<HocVien>select(String sql, Object... args) {
//        List<HocVien> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                rs = XJdbc.executeQuery(sql, args);
//                while (rs.next()) {
//                    HocVien model = readFromResultSet(rs);
//                    list.add(model);
//                }
//            } finally {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return list;
//    }
//    private HocVien readFromResultSet(ResultSet rs) throws SQLException {
//        HocVien model = new HocVien();
//        model.setMaHV(rs.getInt("MaHV"));
//        model.setMaKH(rs.getInt("KH"));
//        model.setMaNH(rs.getString("MaNH"));
//        model.setDiem(rs.getDouble("Diem"));
//
//        return model;
//    }


}
