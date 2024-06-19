package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
    public static void main(String[] args) {

        ContaController contas = new ContaController();

        Scanner sc = new Scanner(System.in);

        int opcao, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

        ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 1, "Joao", 1000f, 123, 100f);
        contas.cadastrar(cc1);

        ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 1, "Maria", 2000f, 124, 100f);
        contas.cadastrar(cc1);

        ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 2, "Mariana", 4000f, 125, 12);
        contas.cadastrar(cp1);

        ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 2, "Juliana", 8000f, 126, 15);
        contas.cadastrar(cp2);

        while (true) {

            System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
                    + "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                BANCO DO BRAZIL COM Z                ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Listar todas as Contas               ");
            System.out.println("            3 - Buscar Conta por Numero              ");
            System.out.println("            4 - Atualizar Dados da Conta             ");
            System.out.println("            5 - Apagar Conta                         ");
            System.out.println("            6 - Sacar                                ");
            System.out.println("            7 - Depositar                            ");
            System.out.println("            8 - Transferir valores entre Contas      ");
            System.out.println("            9 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     " + Cores.TEXT_RESET);

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite valores inteiros!");
                sc.nextLine();
                opcao = 0;
            }

            if (opcao == 9) {
                System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
                sobre();
                sc.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");

                    System.out.println("Digite o numero da agencia: ");
                    agencia = sc.nextInt();
                    System.out.println("Digite o nome do titular: ");
                    sc.skip("\\R?");
                    titular = sc.nextLine();
                    do {
                        System.out.println("Digite o tipo de conta (1 - CC ou 2-CP): ");
                        tipo = sc.nextInt();
                    } while (tipo < 1 && tipo > 2);
                    System.out.println("Digite o saldo da conta (R$): ");
                    saldo = sc.nextFloat();
                    switch (tipo) {
                        case 1:
                            System.out.println("Digite o limite de crédito (R$): ");
                            limite = sc.nextFloat();
                            contas.cadastrar(new ContaCorrente(contas.gerarNumero(), tipo, titular, saldo, agencia, limite));
                            break;
                        case 2:
                            System.out.println("Digite o dia do aniversario: ");
                            aniversario = sc.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), tipo, titular, saldo, agencia, aniversario));
                            break;
                    }
                    keyPress();
                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3:
                    System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = sc.nextInt();
                    contas.procurarPorNumero(numero);
                    keyPress();
                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");

                    System.out.println("Digite o número da conta: ");
                    numero = sc.nextInt();
                    var buscarConta = contas.buscarNaCollection(numero);
                    if(buscarConta != null) {
                        tipo = buscarConta.getTipo();
                        System.out.println("Digite o número da Agencia: ");
                        agencia = sc.nextInt();
                        System.out.println("Digite o nome do titular: ");
                        sc.skip("\\R?");
                        titular = sc.nextLine();

                        System.out.println("Digite o saldo da conta (R$): ");
                        saldo = sc.nextFloat();

                        switch (tipo) {
                            case 1:
                                System.out.println("Digite o limite de crédito (R$): ");
                                limite = sc.nextFloat();
                                contas.atualizar(new ContaCorrente(numero, tipo, titular, saldo, agencia, limite));
                                break;

                            case 2:
                                System.out.println("Digite o dia do aniversario: ");
                                aniversario = sc.nextInt();
                                contas.atualizar(new ContaPoupanca(numero, tipo, titular, saldo, agencia, aniversario));
                                break;

                            default:
                                System.out.println("Tipo inválido!");
                        }
                    }
                    else {
                        System.out.println("Tipo de conta inválido!");
                    }
                    keyPress();
                    break;
                case 5:
                    System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = sc.nextInt();
                    contas.deletar(numero);
                    keyPress();
                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
                    System.out.println("Digite o numero da conta: ");
                    numero = sc.nextInt();
                    do {
                        System.out.println("Digite o valor do Saque (R$): ");
                        valor = sc.nextFloat();
                    } while (valor <= 0);
                    contas.sacar(numero,valor);
                    keyPress();
                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
                    System.out.println("Digite o numero da conta: ");
                    numero = sc.nextInt();
                    do {
                        System.out.println("Digite o valor do deposito (R$): ");
                        valor = sc.nextFloat();
                    } while (valor <= 0);
                    keyPress();
                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
                    System.out.println("Digite o numero da conta de origem: ");
                    numero = sc.nextInt();
                    System.out.println("Digite o numero da conta de destino: ");
                    numeroDestino = sc.nextInt();
                    do{
                        System.out.println("Digite o valor da trassnferência (R$): ");
                        valor = sc.nextFloat();
                    } while (valor <= 0 );
                    contas.trasnferir(numero, numeroDestino, valor);
                    keyPress();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
                    keyPress();
                    break;
            }
        }
    }

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: ");
        System.out.println("Generation Brasil - generation@generation.org");
        System.out.println("github.com/conteudoGeneration");
        System.out.println("*********************************************************");
    }

    public static void keyPress() {
        try {
            System.out.println(Cores.TEXT_RESET + "\nPressione Enter para Continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de enter!");
        }
    }
}

