package controllerManagement;

import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDetails {
    public boolean saveProduct(Product p) throws SQLException, ClassNotFoundException;
    public Product searchProduct(String code) throws SQLException, ClassNotFoundException ;
    public boolean updateProduct(Product p) throws SQLException, ClassNotFoundException ;
    public boolean deleteProduct(String code) throws SQLException, ClassNotFoundException ;
    public ArrayList<Product> getAllProduct() throws SQLException, ClassNotFoundException;

}
