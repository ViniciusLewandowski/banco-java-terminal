package model;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private final String nome;
    private final List<Conta> contas = new ArrayList<>();

    public Banco(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do banco não pode ser vazio.");
        }

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarConta(Conta conta) {

        if (conta == null) {
            throw new IllegalArgumentException("Conta inválida.");
        }

        if (buscarConta(conta.getNumeroDaConta()) != null) {
            throw new IllegalArgumentException("Já existe conta com esse número.");
        }

        contas.add(conta);
    }

    public Conta buscarConta(String numeroConta) {

        if (numeroConta == null || numeroConta.isBlank()) {
            throw new IllegalArgumentException("Número da conta inválido.");
        }

        for (Conta conta : contas) {
            if (conta.getNumeroDaConta().equals(numeroConta)) {
                return conta;
            }
        }

        return null;
    }

    public List<Conta> listarContas() {
        return new ArrayList<>(contas);
    }

    public void transferir(String numeroOrigem, String numeroDestino, double valor) {

        Conta origem = buscarConta(numeroOrigem);
        Conta destino = buscarConta(numeroDestino);

        if (origem == null) {
            throw new IllegalArgumentException("Conta de origem não encontrada.");
        }

        if (destino == null) {
            throw new IllegalArgumentException("Conta de destino não encontrada.");
        }

        origem.transferir(destino, valor);
    }
}