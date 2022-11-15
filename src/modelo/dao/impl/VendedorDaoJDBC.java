package modelo.dao.impl;

import db.DB;
import db.DbException;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJDBC implements VendedorDao {
    //implementação da interface VendedorDao
    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "( ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
            st.setDouble(4, obj.getSalarioBase());
            st.setInt(5, obj.getDepartamento().getId());

            int linhasAfetadas = st.executeUpdate();

            if(linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
                else{
                    throw new DbException("Erro inesperado! Nenhuma linha foi afetada.");
                }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Vendedor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                    + "WHERE Id = ?");
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
            st.setDouble(4, obj.getSalarioBase());
            st.setInt(5, obj.getDepartamento().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
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
               Departamento dep = instanciarDepartamento(rs);
               Vendedor vendedor = instanciarVendedor(rs, dep);
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
    public List<Vendedor> findByDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //consulta no banco de dados por id do vendedor
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");
            //substituindo a "?" do where por um id
            st.setInt(1, departamento.getId());
            //guardando o resultado dentro do result set
            rs = st.executeQuery();
            //criando uma lista para armazenar os vendedores
            List<Vendedor> list = new ArrayList<>();
            //criando um map para controle de não repetição departamento
            Map<Integer, Departamento> map = new HashMap<>();

            while(rs.next()) {
                //verificando se o departamento já existe no map, caso exista iremos reaproveitá-lo
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                //caso o departamento não esteja contido no map, irá retornar nulo e teremos que instanciar ele
                if(dep == null) {
                    //instanciando o departamento
                    dep = instanciarDepartamento(rs);
                    //salvando o departamento no map
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                //instanciando vendedor
                Vendedor vendedor = instanciarVendedor(rs, dep);
                //adicionando vendedor a lista
                list.add(vendedor);
            }
            //retornando nulo caso o if seja falso
            return list;
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //consulta no banco de dados por id do vendedor
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name");

            //guardando o resultado dentro do result set
            rs = st.executeQuery();
            //criando uma lista para armazenar os vendedores
            List<Vendedor> list = new ArrayList<>();
            //criando um map para controle de não repetição departamento
            Map<Integer, Departamento> map = new HashMap<>();

            while(rs.next()) {
                //verificando se o departamento já existe no map, caso exista iremos reaproveitá-lo
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                //caso o departamento não esteja contido no map, irá retornar nulo e teremos que instanciar ele
                if(dep == null) {
                    //instanciando o departamento
                    dep = instanciarDepartamento(rs);
                    //salvando o departamento no map
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                //instanciando vendedor
                Vendedor vendedor = instanciarVendedor(rs, dep);
                //adicionando vendedor a lista
                list.add(vendedor);
            }
            //retornando nulo caso o if seja falso
            return list;
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

    //criando função para reutilizar a instanciação de departamento
    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }

    //criando função para reutilizar a instanciação de departamento
    private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(rs.getInt("Id"));
        vendedor.setNome(rs.getString("Name"));
        vendedor.setEmail(rs.getString("Email"));
        vendedor.setSalarioBase(rs.getDouble("BaseSalary"));
        vendedor.setAniversario(rs.getDate("BirthDate"));
        vendedor.setDepartamento(dep);
        return vendedor;
    }
}
