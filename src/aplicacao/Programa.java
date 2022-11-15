package aplicacao;

import modelo.dao.DaoFactory;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        VendedorDao vendedorDao = DaoFactory.criarVendedorDao();

        System.out.println("FindByID: ");
        Vendedor vendedor = vendedorDao.findById(3);
        System.out.println(vendedor);

        System.out.println("\nFindByDepartment: ");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
        for (Vendedor obj : list) {
            System.out.println(obj);
        }

        System.out.println("\nfindAll: ");
        list = vendedorDao.findAll();
        for (Vendedor obj : list) {
            System.out.println(obj);
        }


        System.out.println("\n Insert Vendedor: ");
        Vendedor vend = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departamento);
        vendedorDao.insert(vend);
        System.out.println("Inserido! Novo id: " + vend.getId());



        System.out.println("\n Update Vendedor: ");
        vendedor = vendedorDao.findById(1);
        vendedor.setNome("Renato Farias");
        vendedorDao.update(vendedor);
        System.out.println("Update completado");


        System.out.println("\nDelete Vendedor: ");
        System.out.println("Digite o ID do vendedor que você deseja deletar: ");
        int id = sc.nextInt();
        vendedorDao.deleteById(id);
        System.out.println("Vendedor deletado com sucesso!");

        sc.close();
    }
}
