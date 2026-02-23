package model;

import java.time.LocalDate;
import java.time.Period;

public class Cliente {

    private final String nome;
    private final String cpf;
    private final LocalDate dataNascimento;
    private final long id;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, long id) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
        }

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser vazio ou nulo.");
        }

        String cpfVerificar = cpf.replaceAll("[^0-9]", "");

        if (cpfVerificar.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }

        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }

        if (id < 0) {
            throw new IllegalArgumentException("ID não pode ser negativo.");
        }

        this.nome = nome;
        this.cpf = cpfVerificar;
        this.dataNascimento = dataNascimento;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public long getId() {
        return id;
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", idade=" + getIdade() +
                ", id=" + id +
                '}';
    }
}