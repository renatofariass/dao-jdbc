package modelo.entidades;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    //construtor padrão
    public Departamento(){}

    //construtor com argumentos
    public Departamento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    //getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //hashcode e equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento dep = (Departamento) o;
        return getId().equals(dep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
