package com.example.controlecurso;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int alunoId;
    private String nomeAluno;
    private String cpf;
    private String email;
    private String telefone;

    public Aluno(){

    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    @Override
    public String toString(){
        return Integer.toString(alunoId)+" - Nome:"+ nomeAluno.toString();
    }
}
