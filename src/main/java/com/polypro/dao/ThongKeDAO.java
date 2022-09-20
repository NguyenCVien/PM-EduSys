package com.polypro.dao;

import com.polypro.utils.XJdbc;
import com.polypro.utils.XJdbc1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getBangDiem(Integer makh) {
        String sql = "{CALL sp_BangDiem(?)}";
        String[] cols = {"MaNH", "HoTen", "Diem"};
        return this.getListOfArray(sql, cols, makh);
    }
    

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALL sp_ThongKeNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL sp_ThongKeDiem}";
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL sp_ThongKeDoanhThu (?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "CaoNhat",
        "ThapNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }

    public static void main(String[] args) {
        System.out.println(new ThongKeDAO().getBangDiem(16).size());
    }
//    public List<Object[]> getNguoiHoc() {
//
//        List<Object[]> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                String sql = "{call sp_ThongKeNguoiHoc}";
//                rs = XJdbc.executeQuery(sql);
//                while (rs.next()) {
//                    Object[] model = {
//                        rs.getInt("Nam"),
//                        rs.getInt("SoLuong"),
//                        rs.getDate("DauTien"),
//                        rs.getDate("CuoiCung")};
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

    public List<Object[]> getXepLoai(Integer makh) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_BangDiem(?)}";
                rs = XJdbc.executeQuery(sql, makh);
                while (rs.next()) {
                    double diem = rs.getDouble("Diem");
                    String xepLoai = "Xuất sắc";
                    if (diem < 0) {
                        xepLoai = "Chưa nhập";
                    } else if (diem < 3) {
                        xepLoai = "Kém";
                    } else if (diem < 5) {
                        xepLoai = "Trung bình";
                    } else if (diem < 7.5) {
                        xepLoai = "Khá";
                    } else if (diem < 9) {
                        xepLoai = "Giỏi";
                    }
                    Object[] model = {
                        rs.getString("MaNH"),
                        rs.getString("HoTen"),
                        diem,
                        xepLoai
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }
//    public List<Object[]> getDiemTheoChuyenDe() {
//
//        List<Object[]> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                String sql = "{call sp_ThongKeDiem}";
//                rs = XJdbc.executeQuery(sql);
//                while (rs.next()) {
//                    Object[] model = {
//                        rs.getString("ChuyenDe"),
//                        rs.getInt("SoHV"),
//                        rs.getDouble("ThapNhat"),
//                        rs.getDouble("CaoNhat"),
//                        rs.getDouble("TrungBinh")};
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
//
//    public List<Object[]> getDoanhThu(int nam) {
//
//        List<Object[]> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                String sql = "{call sp_ThongKeDoanhThu(?)}";
//                rs = XJdbc.executeQuery(sql, nam);
//                while (rs.next()) {
//                    Object[] model = {
//                        rs.getString("ChuyenDe"),
//                        rs.getInt("SoKH"),
//                        rs.getInt("SoHV"),
//                        rs.getDouble("DoanhThu"),
//                        rs.getDouble("ThapNhat"),
//                        rs.getDouble("CaoNhat"),
//                        rs.getDouble("TrungBinh")};
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
}
