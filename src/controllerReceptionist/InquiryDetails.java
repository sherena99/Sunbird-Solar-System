package controllerReceptionist;

import model.Customer;
import model.Inquiry;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InquiryDetails {
    public boolean deleteInquiry(Inquiry inquiryId) throws SQLException, ClassNotFoundException;
    public Inquiry searchInquiry(String inquiryId) throws SQLException, ClassNotFoundException;
    public boolean updateInquiry(InquiryDetails o1) throws SQLException, ClassNotFoundException ;
    public ArrayList<Inquiry> getAllInquiries() throws SQLException, ClassNotFoundException;
}
