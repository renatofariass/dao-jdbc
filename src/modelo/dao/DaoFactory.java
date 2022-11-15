package modelo.dao;

import db.DB;
import modelo.dao.impl.DepartamentoDaoJDBC;
import modelo.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
    //instanciação da implementação da interface VendedorDao
    public static VendedorDao criarVendedorDao() {
        return new VendedorDaoJDBC(DB.getConnection());
    }

    public static DepartamentoDao criarDepartamentoDao() {
        return new DepartamentoDaoJDBC(DB.getConnection());
    }
}
