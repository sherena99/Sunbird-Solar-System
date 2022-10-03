package controllerReceptionist;

import db.DbConnection;
import model.Payment;
import model.Product;
import model.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiteController {
    public String getSiteId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT siteId FROM Site ORDER BY siteId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "S-00" + tempId;
            } else if (tempId < 99) {
                return "S-0" + tempId;
            } else {
                return "S-" + tempId;
            }

        } else {
            return "S-001";
        }
    }

    public static List<String> getAllInquiryCodes() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT inquiryCode FROM Inquiry");
        ResultSet rs = stm.executeQuery();
        List<String> inquiryId = new ArrayList<>();
        while (rs.next()) {
            inquiryId.add(rs.getString("inquiryCode"));
        }
        return inquiryId;
    }
    public static String getSiteAddressByInquiryCode(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT siteAddress FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("siteAddress");
        }
        return null;
    }
    public static String getStatusByInquiryCode(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT status FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("status");
        }
        return null;
    }

    public boolean saveSite(Site c,String status) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            String query="INSERT INTO Site VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setObject(1,c.getDate());
            stm.setObject(2,c.getTime());
            stm.setObject(3,c.getInquiryCode());
            stm.setObject(4,c.getAddress());
            stm.setObject(5,c.getSiteType());
            stm.setObject(6,c.getRoof());
            stm.setObject(7,c.getDirection());
            stm.setObject(8,c.getExtraWork());
            stm.setObject(9,c.getCharges());
            stm.setObject(10,c.getNote());
            if (stm.executeUpdate()>0){
                if (updateStatus(status,c.getInquiryCode())){
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }
            }else {
                con.rollback();
                return false;
            }
        }catch (Exception e){
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }

    }
    public boolean updateStatus(String status, String inquiryId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Inquiry SET status=? WHERE inquiryCode=?");
        stm.setString(1,status);
        stm.setString(2,inquiryId);
        return stm.executeUpdate()>0;

    }

    public ArrayList<Site> loadSiteDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Site");
        ResultSet rst = stm.executeQuery();
        ArrayList<Site> sites = new ArrayList<>();
        while (rst.next()) {
            sites.add(new Site(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getDouble(9),
                    rst.getString(10)
            ));
        }
        return sites;
    }

    public boolean updateSiteDetails(Site s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Site SET date=?, time=?, address=?, type=?, roof=?, direction=?, ExtraWork=?, charges=?, note=?  WHERE inquiryId=?");
        stm.setObject(1,s.getDate());
        stm.setObject(2,s.getTime());
        stm.setObject(3,s.getAddress());
        stm.setObject(4,s.getSiteType());
        stm.setObject(5,s.getRoof());
        stm.setObject(6,s.getDirection());
        stm.setObject(7,s.getExtraWork());
        stm.setObject(8,s.getCharges());
        stm.setObject(9,s.getNote());
        stm.setObject(10,s.getInquiryCode());
        return stm.executeUpdate()>0;
    }

    public Site searchSiteDetails(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Site WHERE inquiryId=?");
        stm.setObject(1, code);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Site(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getDouble(9),
                    rst.getString(10)

            );

        } else {
            return null;
        }
    }



}
