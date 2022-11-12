package modelo.dao;

import db.DB;
import modelo.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
    //instanciação da implementação da interface VendedorDao
    public static VendedorDao criarVendedorDao() {
        return new VendedorDaoJDBC(DB.getConnection());
    }
}
