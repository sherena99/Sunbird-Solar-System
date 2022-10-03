package controllerManagement;

import db.DbConnection;
import model.CRO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CROController implements CRODetails {

    // Load CRO ID According To Last One
    public String getCROId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT croId FROM CRO ORDER BY croId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "CRO-00"+tempId;
            }else if(tempId<99){
                return "CRO-0"+tempId;
            }else{
                return "CRO-"+tempId;
            }

        }else{
            return "CRO-001";
        }
    }


    @Override
    public boolean saveCRO(CRO c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO CRO VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getName());
        stm.setObject(3,c.getAddress());
        stm.setObject(4,c.getCode());
        stm.setObject(5,c.getMobile());
        stm.setObject(6,c.getSalary());
        return stm.executeUpdate()>0;
    }


    @Override
    public CRO searchCRO(String id) throws SQLException, ClassNotFoundException {
            PreparedStatement stm = DbConnection.getInstance()
                    .getConnection().prepareStatement("SELECT * FROM CRO WHERE croID=?");
            stm.setObject(1, id);

            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                return new CRO(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getDouble(6)

                );

            } else {
                return null;
            }
        }

    @Override
    public boolean updateCRO(CRO c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE CRO SET croName=?, croAddress=?, croCode=?,croMobile=?, croSalary=? WHERE croId=?");
        stm.setObject(1,c.getName());
        stm.setObject(2,c.getAddress());
        stm.setObject(3,c.getCode());
        stm.setObject(4,c.getMobile());
        stm.setObject(5,c.getSalary());
        stm.setObject(6,c.getId());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCRO(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM CRO WHERE croId='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<CRO> getAllCRO() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM CRO");
        ResultSet rst = stm.executeQuery();
        ArrayList<CRO> cro = new ArrayList<>();
        while (rst.next()) {
            cro.add(new CRO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)
            ));
        }
        return cro;
    }
}
