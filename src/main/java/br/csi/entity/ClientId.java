package br.csi.entity;

import java.io.Serializable;

public class ClientId implements Serializable {
    private String cpf;
    private String email;

    public ClientId() {}

    public ClientId(String email, String cpf) {
        this.email = email;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
