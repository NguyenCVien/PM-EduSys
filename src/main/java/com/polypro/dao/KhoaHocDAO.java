package com.polypro.dao;

import com.polypro.entity.KhoaHoc;
import com.polypro.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, String> {
    
        public List<Integer> selectYears() {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.executeQuery("select distinct year(NgayKG) as nam from KhoaHoc order by year(NgayKG) desc");
                while (rs.next()) {
                    int nam = rs.getInt(1);
                    list.add(nam);
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public void insert(KhoaHoc model) {
        String sql = "INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV)"
                + "VALUES(?,?,?,?,?,?)";
        XJdbc.executeUpdate(sql,
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV());
    }

    public void update(KhoaHoc model) {
        String sql = "UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=?"
                + " WHERE MaKH=?";
        XJdbc.executeUpdate(sql,
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaKH()
        );
    }


    public void delete(String MaKH) {
        String sql = "DELETE FROM KhoaHoc WHERE MaKH=?";
        XJdbc.executeUpdate(sql, MaKH);
    }

    public List<KhoaHoc> selectAll() {
        String sql = "SELECT * FROM KhoaHoc";
        return selectBySql(sql);
    }

    public KhoaHoc selectById(String makh) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaKH=?";
        List<KhoaHoc> list = selectBySql(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.executeQuery(sql, args);
                while (rs.next()) {
                    KhoaHoc entity = new KhoaHoc();
                    entity.setMaKH(rs.getString("MaKH"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setNgayKG(rs.getDate("NgayKG"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayTao(rs.getDate("NgayTao"));
                    entity.setMaCD(rs.getString("MaCD"));
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
    
    public List<KhoaHoc> selectByChuyenDe(String macd) {
        String sql = "SELECT * FROM KhoaHoc where MaCD=?";
        return this.selectBySql(sql, macd);
    }

//    private List<KhoaHoc> select(String sql, Object... args) {
//        List<KhoaHoc> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                rs = XJdbc.executeQuery(sql, args);
//                while (rs.next()) {
//                    KhoaHoc model = readFromResultSet(rs);
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
//
//    private KhoaHoc readFromResultSet(ResultSet rs) throws SQLException {
//        KhoaHoc model = new KhoaHoc();
//        model.setMaKH(rs.getInt("MaKH"));
//        model.setHocPhi(rs.getDouble("HocPhi"));
//        model.setThoiLuong(rs.getInt("ThoiLuong"));
//        model.setNgayKG(rs.getDate("NgayKG"));
//        model.setGhiChu(rs.getString("GhiChu"));
//        model.setMaNV(rs.getString("MaNV"));
//        model.setNgayTao(rs.getDate("NgayTao"));
//        model.setMaCD(rs.getString("MaCD"));
//
//        return model;
//    }

}
