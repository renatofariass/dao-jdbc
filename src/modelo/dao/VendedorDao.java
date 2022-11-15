package modelo.dao;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.List;

public interface VendedorDao {
    void insert(Vendedor obj);
    void update(Vendedor obj);
    void deleteById(Integer id);
    Vendedor findById(Integer id);
    List<Vendedor> findByDepartamento(Departamento departamento);
    List<Vendedor> findAll();
}
