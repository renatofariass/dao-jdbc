package aplicacao;

import modelo.dao.DaoFactory;
import modelo.dao.DepartamentoDao;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.List;
import java.util.Scanner;

public class Programa2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartamentoDao departamentoDao = DaoFactory.criarDepartamentoDao();

        System.out.println("FindById: ");
        Departamento departamento = departamentoDao.findById(3);
        System.out.println(departamento);


        System.out.println("\nfindAll: ");
        List<Departamento> list = departamentoDao.findAll();
        for (Departamento obj : list) {
            System.out.println(obj);
        }


        System.out.println("\nInsert Departamento: ");
        Departamento dep = new Departamento(null, "Music");
        departamentoDao.insert(dep);
        System.out.println("Inserido! Novo id: " + dep.getId());



        System.out.println("\nUpdate Departamento: ");
        departamento = departamentoDao.findById(3);
        departamento.setNome("Clothes");
        departamentoDao.update(departamento);
        System.out.println("Update completado");


        System.out.println("\nDelete Departamento: ");
        System.out.println("Digite o ID do Departamento que você deseja deletar: ");
        int id = sc.nextInt();
        departamentoDao.deleteById(id);
        System.out.println("Departamento deletado com sucesso!");

        sc.close();
    }
}
