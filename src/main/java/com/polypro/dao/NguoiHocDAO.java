package com.polypro.dao;


import com.polypro.entity.KhoaHoc;
import com.polypro.entity.NguoiHoc;
import com.polypro.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {
    
    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql = "select * from NguoiHoc where HoTen like ? and MaNH NOT IN (select manh from hocvien where makh=?)";
        return this.selectBySql(sql, "%"+keyword+"%", makh);
    }

    public void insert(NguoiHoc model) {
        String sql = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        XJdbc.executeUpdate(sql,
                model.getMaNH(),
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV());

    }

    public void update(NguoiHoc model) {
        String sql = "UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?,"
                + " MaNV=? WHERE MaNH=?";
        XJdbc.executeUpdate(sql,
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaNH()
        );
    }

    public void delete(String id) {
        String sql = "DELETE FROM NGUOIHOC WHERE MaNH=?";
        XJdbc.executeUpdate(sql, id);
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return selectBySql(sql, "%"+keyword+"%");
    }


    public List<NguoiHoc> selectByCourse(Integer makh) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return selectBySql(sql, makh);
    }
    
    public List<NguoiHoc> selectAll() {
        String sql = "SELECT * FROM KhoaHoc";
        return selectBySql(sql);
    }

//    public NguoiHoc selectById(String manh) {
//        String sql = "SELECT * FROM NguoiHoc WHERE MaNH=?";
//        List<NguoiHoc> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                rs = XJdbc.executeQuery(sql, args);
//                while (rs.next()) {
//                    NguoiHoc model = readFromResultSet(rs);
//                    list.add(model);
//                }
//            } finally {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
    
    public NguoiHoc selectById(String manh) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH =?";
        List<NguoiHoc> list = selectBySql(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.executeQuery(sql, args);
                while (rs.next()) {
                    NguoiHoc entity = new NguoiHoc();
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setDienThoai(rs.getString("DienThoai"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayDK(rs.getDate("NgayDK"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
        }
        return list;
    }
    
}

//    private NguoiHoc readFromResultSet(ResultSet rs) throws SQLException {
//        NguoiHoc model = new NguoiHoc();
//        model.setMaNH(rs.getString("MaNH"));
//        model.setHoTen(rs.getString("HoTen"));
//        model.setNgaySinh(rs.getDate("NgaySinh"));
//        model.setGioiTinh(rs.getBoolean("GioiTinh"));
//        model.setDienThoai(rs.getString("DienThoai"));
//        model.setEmail(rs.getString("Email"));
//        model.setGhiChu(rs.getString("GhiChu"));
//        model.setMaNV(rs.getString("MaNV"));
//        model.setNgayDK(rs.getDate("NgayDK"));
//        return model;
//    }
