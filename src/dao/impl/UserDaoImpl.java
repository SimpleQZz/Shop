package dao.impl;

import dao.UserDao;
import domain.User;
import javafx.scene.control.TextField;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUser(String username,String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM user where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("uid"),rs.getString("username"),rs.getString("password"),rs.getString("company_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public User findByUserName(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("uid"),rs.getString("username"),rs.getString("password"),rs.getString("company_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public void updateUser(String username, String  password) {
        String sql="update user set password=? where username=?";
        JdbcUtil.executeUpdate(sql,password,username);
    }


}
