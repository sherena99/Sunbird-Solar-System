package controllerReceptionist;

import model.CRO;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDetails {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException ;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;
}
