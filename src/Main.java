import model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Banco banco = new Banco("Banco Java");

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n=== BANCO JAVA ===");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Acessar Conta");
            System.out.println("3 - Listar Contas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> acessarConta();
                case 3 -> listarContas();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void criarConta() {

        try {

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("ID: ");
            long id = scanner.nextLong();

            System.out.print("Ano nascimento: ");
            int ano = scanner.nextInt();

            System.out.print("Mes nascimento: ");
            int mes = scanner.nextInt();

            System.out.print("Dia nascimento: ");
            int dia = scanner.nextInt();
            scanner.nextLine();

            Cliente cliente = new Cliente(
                    nome,
                    cpf,
                    LocalDate.of(ano, mes, dia),
                    id
            );

            System.out.print("Numero da conta: ");
            String numero = scanner.nextLine();

            System.out.print("Crie uma senha: ");
            String senha = scanner.nextLine();

            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupanca");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            if (tipo == 1) {
                System.out.print("Limite: ");
                double limite = scanner.nextDouble();
                scanner.nextLine();
                banco.adicionarConta(new ContaCorrente(numero, cliente, senha, limite));
            } else if (tipo == 2) {
                banco.adicionarConta(new ContaPoupanca(numero, cliente, senha));
            } else {
                System.out.println("Tipo inválido.");
                return;
            }

            System.out.println("Conta criada com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void acessarConta() {

        System.out.print("Numero da conta: ");
        String numero = scanner.nextLine();

        Conta conta = banco.buscarConta(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (!conta.autenticar(senha)) {
            System.out.println("Senha incorreta.");
            return;
        }

        int opcao;

        do {

            System.out.println("\nConta: " + conta.getNumeroDaConta());
            System.out.println("Cliente: " + conta.getCliente().getNome());
            System.out.println("Saldo: " + conta.getSaldo());

            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Ver Extrato");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {

                    case 1 -> {
                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        conta.depositar(valor);
                    }

                    case 2 -> {
                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        conta.sacar(valor);
                    }

                    case 3 -> {
                        System.out.print("Conta destino: ");
                        String destino = scanner.nextLine();

                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        banco.transferir(conta.getNumeroDaConta(), destino, valor);
                    }

                    case 4 -> conta.mostrarExtrato();
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private static void listarContas() {

        for (Conta conta : banco.listarContas()) {
            System.out.println(
                    "Conta: " + conta.getNumeroDaConta() +
                            " | Cliente: " + conta.getCliente().getNome() +
                            " | Saldo: " + conta.getSaldo()
            );
        }
    }
}