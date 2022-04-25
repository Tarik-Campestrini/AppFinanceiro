package com.app.financeiro.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "O campo Nome deve ser preenchido")
    @Size(min = 3, max = 12, message = "O campo Nome deve ter no minimo de 3 caracteres")
    private String nome;
    
    @NotBlank(message = "O campo Sobrenome deve ser preenchido")
    @Size(min = 3, max = 32, message = "O campo Sobrenome deve ter no minimo de 3 caracteres")
    private String sobrenome;
    
    @NotBlank(message = "O campo E-mail deve ser preenchido")
    @Size(min = 5, max = 32, message = "O campo E-mail deve ter no minimo de 5 caracteres")
    private String email;
    
    @NotBlank(message = "O campo Senha deve ser preenchido")
    @Size(min = 3, max = 500, message = "O campo Senha deve ter no minimo de 3 caracteres")
    private String senha;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Entrada> entrada;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Saida> saida;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobreNome) {
        this.sobrenome = sobreNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	


}
