package model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String numeroDaConta, Cliente cliente, String senha) {
        super(numeroDaConta, cliente, senha);

        if (cliente.getIdade() < 12) {
            throw new IllegalArgumentException("Cliente deve ter pelo menos 12 anos para abrir Conta Poupança.");
        }
    }

    @Override
    public void sacar(double valor) {

        if (getCliente().getIdade() < 16) {
            throw new IllegalArgumentException("Somente clientes com 16 anos ou mais podem sacar.");
        }

        super.sacar(valor);
    }

    @Override
    public void transferir(Conta contaDestino, double valor) {
        throw new UnsupportedOperationException("Conta Poupança não permite transferências.");
    }
}