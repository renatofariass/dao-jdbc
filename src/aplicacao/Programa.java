package aplicacao;

import modelo.dao.DaoFactory;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.Date;

public class Programa {
    public static void main(String[] args) {

        VendedorDao vendedorDao = DaoFactory.criarVendedorDao();
        Vendedor vendedor = vendedorDao.findById(3);
        System.out.println(vendedor);

    }


}
