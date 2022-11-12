package modelo.dao.impl;

import db.DB;
import db.DbException;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendedorDaoJDBC implements VendedorDao {
    //implementação da interface VendedorDao
    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {

    }

    @Override
    public void update(Vendedor obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //consulta no banco de dados por id do vendedor
           st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                   + "FROM seller INNER JOIN department "
                   + "ON seller.DepartmentId = department.Id "
                   + "WHERE seller.Id = ?");
           //substituindo a "?" do where por um id
           st.setInt(1, id);
           //guardando o resultado dentro do result set
           rs = st.executeQuery();
            //verificando se o vendedor existe no banco de dados
            //se verdadeiro, retorna os dados do vendedor. Se falso, retorna nulo
           if(rs.next()) {
               Departamento dep = new Departamento();
               dep.setId(rs.getInt("DepartmentId"));
               dep.setNome(rs.getString("DepName"));
               Vendedor vendedor = new Vendedor();
               vendedor.setId(rs.getInt("Id"));
               vendedor.setNome(rs.getString("Name"));
               vendedor.setEmail(rs.getString("Email"));
               vendedor.setSalarioBase(rs.getDouble("BaseSalary"));
               vendedor.setAniversario(rs.getDate("BirthDate"));
               vendedor.setDepartamento(dep);
               return vendedor;
           }
           //retornando nulo caso o if seja falso
           return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            //fechando o statement e o result set
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> findAll() {
        return null;
    }
}
