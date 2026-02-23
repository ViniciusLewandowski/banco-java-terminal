package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

    private double saldo = 0.0;
    private final String numeroDaConta;
    private final Cliente cliente;
    private final String senha;
    private final List<String> extrato = new ArrayList<>();

    public Conta(String numeroDaConta, Cliente cliente, String senha) {

        if (numeroDaConta == null || numeroDaConta.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser vazio.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("Conta deve possuir um cliente.");
        }

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }

        this.numeroDaConta = numeroDaConta;
        this.cliente = cliente;
        this.senha = senha;

        extrato.add("Conta criada.");
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean autenticar(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    public void depositar(double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito inválido.");
        }

        saldo += valor;
        extrato.add("Depósito: +" + valor);
    }

    public void sacar(double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido.");
        }

        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        saldo -= valor;
        extrato.add("Saque: -" + valor);
    }

    public void transferir(Conta contaDestino, double valor) {

        if (contaDestino == null) {
            throw new IllegalArgumentException("Conta destino inválida.");
        }

        if (this == contaDestino) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta.");
        }

        this.sacar(valor);
        contaDestino.depositar(valor);

        extrato.add("Transferência enviada: -" + valor + " para conta " + contaDestino.getNumeroDaConta());
        contaDestino.registrarTransferenciaRecebida(valor, this.numeroDaConta);
    }

    private void registrarTransferenciaRecebida(double valor, String contaOrigem) {
        extrato.add("Transferência recebida: +" + valor + " da conta " + contaOrigem);
    }

    public void mostrarExtrato() {
        System.out.println("=== EXTRATO ===");
        for (String item : extrato) {
            System.out.println(item);
        }
    }
}