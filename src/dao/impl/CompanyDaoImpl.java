package dao.impl;

import dao.CompanyDao;
import domain.Company;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            ps = conn.prepareStatement(sql);
            // 遍历参数
            for (int i = 0; i < params.length; i++) {
                // ps.setString(1, stu.getName());
                // ps.setInt(2, stu.getAge());
                ps.setObject(i + 1, params[i]);

            }
            // 4.执行语句
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    @Override
    public void save(Company company) {
        String sql = "insert into company(company_name) values (?)";
        executeUpdate(sql, company.getCompanyName());
    }

    @Override
    public List<Company> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "select * from company ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<Company> list = new ArrayList<>();
            while (rs.next()) {
                Company company = new Company(
                        rs.getInt("company_id"), rs.getString("company_name"), rs.getInt("credit")
                );
                list.add(company);
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
    public Company findByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM `company` where company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Company(rs.getInt("company_id"),rs.getString("company_name"),rs.getInt("credit"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    public void updateCredit(String company, String credit){
        String sql = "update company set credit=? where company_name=?";
        executeUpdate(sql,credit,company);
    }
}

