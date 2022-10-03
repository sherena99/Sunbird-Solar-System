package controllerReceptionist;

import db.DbConnection;
import model.*;
import model.InquiryDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {

    public String getPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "P-00"+tempId;
            }else if(tempId<99){
                return "P-0"+tempId;
            }else{
                return "P-"+tempId;
            }

        }else{
            return "P-001";
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
    public static String getCustomerNameById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT custName FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("custName");
        }
        return null;
    }
    public static String getCROCodeById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT croCode FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("croCode");
        }
        return null;
    }
    public static String getTotalById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT total FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("total");
        }
        return null;
    }
    public static String getTotalDiscountById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT discount FROM Inquiry WHERE inquiryCode=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("discount");
        }
        return null;
    }


    public static List<String> getAllInquiryId() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT inquiryId FROM Site");
        ResultSet rs = stm.executeQuery();
        List<String> inquiryId = new ArrayList<>();
        while (rs.next()) {
            inquiryId.add(rs.getString("inquiryId"));
        }
        return inquiryId;
    }
    public static String getSiteAddressById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT address FROM Site WHERE inquiryId=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("address");
        }
        return null;
    }
    public static String getExtraWorkById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT ExtraWork FROM Site WHERE inquiryId=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("ExtraWork");
        }
        return null;
    }
    public static String getExtraChargesById(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT charges FROM Site WHERE inquiryId=?");
        stm.setObject(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("charges");
        }
        return null;
    }

    public ArrayList<InquiryDetails> getAllInquiryDetails(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM InquiryDetails WHERE inquiryId=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        ArrayList<InquiryDetails> inquiryDetails = new ArrayList<>();
        while (rst.next()) {
            inquiryDetails.add(new InquiryDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
            ));
        }
        return inquiryDetails;
    }

    public boolean savePayment(Payment p) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,p.getPaymentId());
        stm.setObject(2,p.getDate());
        stm.setObject(3,p.getTime());
        stm.setObject(4,p.getInquiryId());
        stm.setObject(5,p.getCustomerName());
        stm.setObject(6,p.getCroCode());
        stm.setObject(7,p.getPaymentMethod());
        stm.setObject(8,p.getTotal());
        stm.setObject(9,p.getDiscount());
        return stm.executeUpdate()>0;
    }

    public ArrayList<Payment> loadAllPayment() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Payment");
        ResultSet rst = stm.executeQuery();
        ArrayList<Payment> payments = new ArrayList<>();
        while (rst.next()) {
            payments.add(new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getDouble(9)
            ));
        }
        return payments;
    }

}
