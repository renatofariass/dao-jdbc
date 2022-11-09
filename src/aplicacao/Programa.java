package aplicacao;

import modelo.dao.DaoFactory;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.Date;

public class Programa {
    public static void main(String[] args) {
        Departamento obj = new Departamento(1, "Livros");
        Vendedor vend = new Vendedor(4, "Carlos", "Carlos@gmail.com", new Date(), 3000.0, obj);
        VendedorDao vendedorDao = DaoFactory.criarVendedorDao();
        System.out.println(obj);
    }


}
