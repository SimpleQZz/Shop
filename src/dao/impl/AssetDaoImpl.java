package dao.impl;

import dao.AssetDao;
import domain.AssetInfo;
import domain.AssetSelling;
import util.JdbcUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AssetDaoImpl implements AssetDao {

    @Override
    public List<AssetSelling> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "select asset_name,remain,uploadtime,state from asset_selling ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("uploadtime");
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling=new AssetSelling(rs.getString("asset_name"),rs.getString("remain"),dateStr,
                        rs.getString("state"));
                list.add(assetSelling);
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
    public List<AssetInfo> findAssetByCompany(String company) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT asset_name,quantity,company_name,updatetime FROM asset where company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetInfo> list =new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("updatetime");
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetInfo assetInfo= new AssetInfo(rs.getString("asset_name"),rs.getString("quantity"),rs.getString("company_name"),dateStr);
                list.add(assetInfo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    public AssetInfo findAssetByNameAndCompany(String assetName, String company) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT asset_name,quantity,company_name,updatetime FROM asset where asset_name=? and company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            ps.setString(2, company);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetInfo> list =new ArrayList<>();
            if (rs.next()) {
                Date date = rs.getDate("updatetime");
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                return new AssetInfo(rs.getString("asset_name"),rs.getString("quantity"),rs.getString("company_name"),dateStr);
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
    public void updateQuantity(String company, String assetName, String quantity) {
        String sql = "update asset set quantity=? where asset_name=? and company_name=?";
        JdbcUtil.executeUpdate(sql,quantity,assetName,company);
    }
}
