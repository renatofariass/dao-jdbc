package modelo.dao;

import modelo.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
    //instanciação da implementação da interface VendedorDao
    public static VendedorDao criarVendedorDao() {
        return new VendedorDaoJDBC();
    }
}
