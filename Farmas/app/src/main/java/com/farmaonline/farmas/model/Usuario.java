package com.farmaonline.farmas.model;

/**
 * Created by Romano on 30/11/2017.
 */

public class Usuario {

    private long chavePrimaria;

    private String login;

    private String senha;

    private String email;

    public long getChavePrimaria() {
        return chavePrimaria;
    }

    public void setChavePrimaria(long chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
        .append("Chave Primaria: ").append(getChavePrimaria())
        .append("Login: ").append(getLogin())
        .append("Senha: ").append(getSenha())
        .append("Email: ").append(getSenha());
        return builder.toString();
    }
}
