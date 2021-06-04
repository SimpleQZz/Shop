package dao.impl;

import dao.AdministratorDao;
import domain.Administrator;
import domain.User;
import util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDaoImpl implements AdministratorDao {


    @Override
    public void save(Administrator administrator) {
        String sql = "insert into administrator(adname,password) values (?,?)";
        JdbcUtil.executeUpdate(sql, administrator.getAdname(),administrator.getPassword());
    }

    @Override
    public List<Administrator> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "select * from administrator ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<Administrator> list = new ArrayList<>();
            while (rs.next()) {
                Administrator administrator = new Administrator(rs.getInt("aid"),rs.getString("adname"),rs.getString("password")
                );
                list.add(administrator);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }
        return null;
    }

    public Administrator findOne(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "select * from administrator where adname=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Administrator(rs.getInt("aid"),rs.getString("adname"),rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}
