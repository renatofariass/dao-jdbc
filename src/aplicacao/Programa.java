package aplicacao;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.Date;

public class Programa {
    public static void main(String[] args) {
        Departamento obj = new Departamento(1, "Livros");
        Vendedor vend = new Vendedor(4, "Carlos", "Carlos@gmail.com", new Date(), 3000.0, obj);

        System.out.println(obj);
        System.out.println(vend);
    }


}
