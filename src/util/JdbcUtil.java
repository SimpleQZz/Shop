package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {

    public static DataSource ds = null;
    static {
        try {
            //1.���������ļ�
            Properties p = new Properties();
            FileInputStream in = new FileInputStream("resource/db.properties");
            p.load(in);
            //ds = BasicDataSourceFactory.createDataSource(p);
            ds = DruidDataSourceFactory.createDataSource(p);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn() {
        try {
            // 2.��������
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.��������
            // 2.�������ݿ�
            conn = JdbcUtil.getConn();
            // 3.�������
            ps = conn.prepareStatement(sql);
            // ��������
            for (int i = 0; i < params.length; i++) {
                // ps.setString(1, stu.getName());
                // ps.setInt(2, stu.getAge());
                ps.setObject(i + 1, params[i]);

            }
            // 4.ִ�����
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.�ͷ���Դ
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }
    /**
     * �ر���Դ
     */
    public static void close(Connection conn,Statement st,ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
