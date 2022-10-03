package controllerManagement;

import db.DbConnection;
import model.CRO;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController implements ProductDetails{

    // Load Product Code According To Last One
    public String getProductCode() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT productCode FROM Product ORDER BY productCode DESC LIMIT 1"
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

    @Override
    public boolean saveProduct(Product p) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Product VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,p.getCode());
        stm.setObject(2,p.getName());
        stm.setObject(3,p.getDescription());
        stm.setObject(4,p.getDiscount());
        stm.setObject(5,p.getPrice());
        return stm.executeUpdate()>0;
    }

    @Override
    public Product searchProduct(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Product WHERE productCode=?");
        stm.setObject(1, code);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Product(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)

            );

        } else {
            return null;
        }
    }

    @Override
    public boolean updateProduct(Product p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Product SET productName=?, description=?, discount=?, listPrice=? WHERE productCode=?");
        stm.setObject(1,p.getName());
        stm.setObject(2,p.getDescription());
        stm.setObject(3,p.getDiscount());
        stm.setObject(4,p.getPrice());
        stm.setObject(5,p.getCode());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteProduct(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Product WHERE productCode='" + code + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Product");
        ResultSet rst = stm.executeQuery();
        ArrayList<Product> product = new ArrayList<>();
        while (rst.next()) {
            product.add(new Product(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)
            ));
        }
        return product;
    }
}
