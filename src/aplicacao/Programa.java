package aplicacao;

import modelo.dao.DaoFactory;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Programa {
    public static void main(String[] args) {

        VendedorDao vendedorDao = DaoFactory.criarVendedorDao();
        Vendedor vendedor = vendedorDao.findById(3);
        System.out.println(vendedor);

        System.out.println("\n FindByDepartment: ");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
        for (Vendedor obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n findAll: ");
        list = vendedorDao.findAll();
        for (Vendedor obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n insert Vendedor: ");
        Vendedor vend = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departamento);
        vendedorDao.insert(vend);
        System.out.println("Inserido! Novo id: " + vend.getId());
    }


}
