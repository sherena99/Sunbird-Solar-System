package controllerReceptionist;

import db.DbConnection;
import model.Complain;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplainController {

    public String getComplainNumber() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT complainNum FROM CustomerComplain ORDER BY complainNum DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "Com-00"+tempId;
            }else if(tempId<99){
                return "Com-0"+tempId;
            }else{
                return "Com-"+tempId;
            }

        }else{
            return "Com-001";
        }
    }


    public boolean saveComplain(Complain com) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO CustomerComplain VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,com.getComplainNum());
        stm.setObject(2,com.getTime());
        stm.setObject(3,com.getDate());
        stm.setObject(4,com.getInquiryCode());
        stm.setObject(5,com.getCustName());
        stm.setObject(6,com.getCroCode());
        stm.setObject(7,com.getComAbout());
        stm.setObject(8,com.getComDetails());
        stm.setObject(9,com.getOfficer());
        return stm.executeUpdate()>0;
    }

    public ArrayList<Complain> loadAllComplains() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM CustomerComplain");
        ResultSet rst = stm.executeQuery();
        ArrayList<Complain> complain = new ArrayList<>();
        while (rst.next()) {
            complain.add(new Complain(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return complain;
    }
}
