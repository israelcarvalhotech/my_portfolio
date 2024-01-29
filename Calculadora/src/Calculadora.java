import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {

        // Importa a classe Scanner para leitura de entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Loop que mantém o programa em execução até que o usuário decida sair
        while (true) {
            // Exibe o menu de opções para o usuário
            System.out.println("Calculadora - Escolha uma operação:");
            System.out.println("1. Adição");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("5. Sair");

            // Solicita ao usuário que selecione uma opção do menu
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            // Verifica se o usuário escolheu sair
            if (opcao == 5) {
                System.out.println("Obrigado por usar a calculadora. Até logo!");
                break; // Sai do loop e encerra o programa
            }

            // Solicita ao usuário que insira os números para realizar a operação
            System.out.print("Digite o primeiro número: ");
            double num1 = scanner.nextDouble();

            System.out.print("Digite o segundo número: ");
            double num2 = scanner.nextDouble();

            // Variável para armazenar o resultado da operação
            double resultado = 0;

            // Realiza a operação de acordo com a opção escolhida pelo usuário
            switch (opcao) {
                case 1:
                    resultado = num1 + num2; // Adição
                    break;
                case 2:
                    resultado = num1 - num2; // Subtração
                    break;
                case 3:
                    resultado = num1 * num2; // Multiplicação
                    break;
                case 4:
                    // Verifica se o segundo número é diferente de zero antes de realizar a divisão
                    if (num2 != 0) {
                        resultado = num1 / num2; // Divisão
                    } else {
                        System.out.println("Erro: divisão por zero!");
                        continue; // Retorna ao início do loop
                    }
                    break;
                default:
                    System.out.println("Opção inválida!"); // Opção inválida
                    continue; // Retorna ao início do loop
            }

            // Exibe o resultado da operação
            System.out.println("Resultado: " + resultado);
            System.out.println();
        }

        // Fecha o scanner para liberar recursos
        scanner.close();
    }
}