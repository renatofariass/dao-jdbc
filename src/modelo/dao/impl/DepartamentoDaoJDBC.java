package modelo.dao.impl;

import db.DB;
import db.DbException;
import modelo.dao.DepartamentoDao;
import modelo.entidades.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartamentoDaoJDBC implements DepartamentoDao {
    //implementação da interface DepartamentoDao
    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Departamento obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO department "
                    + "(Name) "
                    + "VALUES "
                    + "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Departamento obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE department "
                    + "SET Name = ? "
                    + "WHERE Id = ?");
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);
            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new DbException("Esse Id não existe!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Departamento findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT Id, Name "
                    + "FROM department "
                    + "WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Departamento dep = instanciarDepartamento(rs);
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Departamento> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT Id, Name "
                    + "FROM department "
                    + "ORDER BY Name");

            rs = st.executeQuery();
            List<Departamento> list = new ArrayList<>();

            while (rs.next()) {
                Departamento dep = instanciarDepartamento(rs);
                list.add(dep);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    //criando função para reutilizar a instanciação de departamento
    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("Id"));
        dep.setNome(rs.getString("Name"));
        return dep;
    }
}
