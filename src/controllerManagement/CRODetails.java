package controllerManagement;

import model.CRO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRODetails {
    public boolean saveCRO(CRO c) throws SQLException, ClassNotFoundException;
    public CRO searchCRO(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateCRO(CRO c) throws SQLException, ClassNotFoundException ;
    public boolean deleteCRO(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<CRO> getAllCRO() throws SQLException, ClassNotFoundException;
}
