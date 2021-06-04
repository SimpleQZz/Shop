package dao.impl;

import dao.UserDao;
import domain.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<User> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "select * from user ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User user=new User(rs.getInt("uid"),rs.getString("username"),rs.getString("password"),rs.getString("company_name"));
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }
        return null;
    }

    @Override
    public void save(User user) {
        String sql = "insert into user(username,password,company_name) values (?,?,?)";
        JdbcUtil.executeUpdate(sql, user.getUsername(),user.getPassword(),user.getCompanyName());
    }


}
