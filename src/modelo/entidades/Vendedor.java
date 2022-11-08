package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {

    private static final long serialVersionUHD = 1L;
    private Integer id;
    private String nome;
    private String email;
    private Date aniversario;
    private Double salarioBase;
    private Departamento departamento;

    //construtor vazio
    public Vendedor() {}

    public Vendedor(Integer id, String nome, String email, Date aniversario, Double salarioBase, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.aniversario = aniversario;
        this.salarioBase = salarioBase;
        this.departamento = departamento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAniversario() {
        return aniversario;
    }

    public void setAniversario(Date aniversario) {
        this.aniversario = aniversario;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    //hashcode e equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendedor)) return false;
        Vendedor vendedor = (Vendedor) o;
        return getId().equals(vendedor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", aniversario=" + aniversario +
                ", salarioBase=" + salarioBase +
                ", departamento=" + departamento +
                '}';
    }
}
