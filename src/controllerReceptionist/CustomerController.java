package controllerReceptionist;

import db.DbConnection;
import model.CRO;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController implements CustomerDetails {

    // Load Customer ID According To Last One
    public String getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT custID FROM Customer ORDER BY custID DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "C-00"+tempId;
            }else if(tempId<99){
                return "C-0"+tempId;
            }else{
                return "C-"+tempId;
            }

        }else{
            return "C-001";
        }
    }


    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getTitle());
        stm.setObject(3,c.getName());
        stm.setObject(4,c.getAddress());
        stm.setObject(5,c.getNic());
        stm.setObject(6,c.getMobile());
        stm.setObject(7,c.getEmail());
        return stm.executeUpdate()>0;
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Customer WHERE custID=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );

        } else {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET custTitle=?, custName=?, custAddress=?,NIC=?, mobileNum=?, email=?  WHERE custID=?");
        stm.setObject(1,c.getTitle());
        stm.setObject(2,c.getName());
        stm.setObject(3,c.getAddress());
        stm.setObject(4,c.getNic());
        stm.setObject(5,c.getMobile());
        stm.setObject(6,c.getEmail());
        stm.setObject(7,c.getId());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE custID='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customer = new ArrayList<>();
        while (rst.next()) {
            customer.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return customer;
    }
}
