package controllerReceptionist;

import db.DbConnection;
import model.Inquiry;
import model.InquiryDetails;
import model.Product;
import model.ProductDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InquiryController{

    public String getInquiryCode() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT inquiryCode FROM Inquiry ORDER BY inquiryCode DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "I-00" + tempId;
            } else if (tempId < 99) {
                return "I-0" + tempId;
            } else {
                return "I-" + tempId;
            }

        } else {
            return "I-001";
        }
    }
    public static List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT custID FROM Customer");
        ResultSet rs = stm.executeQuery();
        List<String> customersId = new ArrayList<>();
        while (rs.next()) {
            customersId.add(rs.getString("custID"));
        }
        return customersId;
    }
    public static String getCustomerNameById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT custName FROM Customer WHERE custID=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("custName");
        }
        return null;
    }
    public static String getCustomerAddressById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT custAddress FROM Customer WHERE custID=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("custAddress");
        }
        return null;
    }

    public static List<String> getAllCROIds() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT croId FROM CRO");
        ResultSet rs = stm.executeQuery();
        List<String> croId = new ArrayList<>();
        while (rs.next()) {
            croId.add(rs.getString("croId"));
        }
        return croId;
    }
    public static String getCROCodeById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT croCode FROM CRO WHERE croId=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("croCode");
        }
        return null;
    }
    public static List<String> getAllProductCode() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT productCode FROM Product");
        ResultSet rs = stm.executeQuery();
        List<String> pCode = new ArrayList<>();
        while (rs.next()) {
            pCode.add(rs.getString("productCode"));
        }
        return pCode;
    }
    public static Product getProductDetails(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Product WHERE productCode=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return new Product(
                    rs.getString("productCode"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getInt("discount"),
                    rs.getDouble("listPrice")
            );

        }
        return null;
    }

    public boolean saveInquiry(Inquiry inquiry)  {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO Inquiry VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

            stm.setObject(1, inquiry.getInquiryCode());
            stm.setObject(2, inquiry.getTime());
            stm.setObject(3, inquiry.getDate());
            stm.setObject(4, inquiry.getCustId());
            stm.setObject(5, inquiry.getCustName());
            stm.setObject(6, inquiry.getSiteAddress());
            stm.setObject(7, inquiry.getCroId());
            stm.setObject(8, inquiry.getCroCode());
            stm.setObject(9, inquiry.getInquiryFrom());
            stm.setObject(10, inquiry.getRemarks());
            stm.setObject(11, inquiry.getTotal());
            stm.setObject(12,inquiry.getDiscount());
            stm.setObject(13,inquiry.getStatus());

            if (stm.executeUpdate() > 0) {

                if (saveInquiryData(inquiry.getInquiryCode(), inquiry.getProduct())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }


        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        } finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

//        return false;
    }

    private boolean saveInquiryData(String inquiryCode, ArrayList<ProductDetails> product) throws SQLException, ClassNotFoundException {
        for (ProductDetails temp : product
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO InquiryDetails VALUES(?,?,?,?,?)");
            stm.setObject(1, inquiryCode);
            stm.setObject(2, temp.getProductCode());
            stm.setObject(3, temp.getSoldQty());
            stm.setObject(4, temp.getTotalDiscount());
            stm.setObject(5, temp.getPrice());
            if (stm.executeUpdate() > 0) {
            } else {
                return false;
            }
        }
        return true;
    }

    public Inquiry searchInquiry(String inquiryId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, inquiryId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Inquiry(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getString(13)
            );

        } else {
            return null;
        }
    }

    public ArrayList<Inquiry> getAllInquiries() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Inquiry");
        ResultSet rst = stm.executeQuery();
        ArrayList<Inquiry> inquiries = new ArrayList<>();
        while (rst.next()) {
            inquiries.add(new Inquiry(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getString(13)

            ));
        }
        return inquiries;
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
    public static String getCustomerNameByCode(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT custName FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, code);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("custName");
        }
        return null;
    }
    public static String getCroCodeByCode(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT croCode FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, code);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("croCode");
        }
        return null;
    }

    public ArrayList<InquiryDetails> getInquiryDetails(String inquiryCode) throws SQLException, ClassNotFoundException {
        ArrayList<InquiryDetails> details = new ArrayList<>();
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM InquiryDetails WHERE inquiryId='"+inquiryCode+"'").executeQuery();
        while (rst.next()) {
            details.add(new InquiryDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
            ));
        }
        return details;
    }


}





