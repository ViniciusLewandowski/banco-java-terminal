package model;

public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(String numeroDaConta, Cliente cliente, String senha, double limite) {
        super(numeroDaConta, cliente, senha);

        if (cliente.getIdade() < 18) {
            throw new IllegalArgumentException("Cliente deve ser maior de 18 anos para abrir Conta Corrente.");
        }

        if (limite < 0) {
            throw new IllegalArgumentException("Limite não pode ser negativo.");
        }

        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    @Override
    public void sacar(double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido.");
        }

        if (valor > getSaldo() + limite) {
            throw new IllegalArgumentException("Saldo + limite insuficiente.");
        }

        double saldoAtual = getSaldo();

        if (valor <= saldoAtual) {
            super.sacar(valor);
        } else {

            if (saldoAtual > 0) {
                super.sacar(saldoAtual);
            }

            double restante = valor - saldoAtual;
            limite -= restante;
        }
    }
}