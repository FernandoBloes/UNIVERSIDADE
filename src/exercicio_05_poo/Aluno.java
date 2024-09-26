package exercicio_05_poo;

import java.util.Scanner;

public class Aluno {
    private String nome;
    private String ra;
    private double[] notas;
    private boolean ead;
    private double frequencia;
    private double notaFinal;

    // Construtor para disciplinas presenciais
    public Aluno(String nome, String ra, double[] notas, double frequencia) {
        this.nome = nome;
        this.ra = ra;
        this.notas = notas;
        this.frequencia = frequencia;
        this.ead = false;
        this.notaFinal = calcularNotaFinal();
    }

    // Construtor para disciplinas EAD (sem necessidade de frequência)
    public Aluno(String nome, String ra, double[] notas) {
        this.nome = nome;
        this.ra = ra;
        this.notas = notas;
        this.ead = true;
        this.notaFinal = calcularNotaFinal();
    }

    // Método para calcular a nota final
    private double calcularNotaFinal() {
        int quantidadeNotas = notas.length;
        double resultado = 0.0;

        switch (quantidadeNotas) {
            case 2:
                // Média aritmética
                resultado = (notas[0] + notas[1]) / 2;
                break;
            case 3:
                // Média ponderada: n1, n2 com peso dobrado, n3 com o dobro de n2
                resultado = (notas[0] + 2 * notas[1] + 4 * notas[2]) / 7;
                break;
            case 4:
                // Fórmula específica para ac1, ac2, ag e af
                resultado = (notas[0] * 0.15) + (notas[1] * 0.30) + (notas[2] * 0.10) + (notas[3] * 0.45);
                break;
            default:
                throw new IllegalArgumentException("Número inválido de notas.");
        }
        return resultado;
    }

    // Método para verificar se o aluno foi aprovado ou reprovado
    private String verificarSituacao() {
        if (notaFinal >= 5) {
            if (ead) {
                return "Aprovado";
            } else if (frequencia >= 75) {
                return "Aprovado";
            } else {
                return "Reprovado por falta";
            }
        } else {
            return "Reprovado por nota";
        }
    }

    // Método para exibir as informações do aluno
    public void exibirDadosAluno() {
        System.out.println("Nome: " + nome);
        System.out.println("RA: " + ra);
        System.out.println("Nota Final: " + String.format("%.2f", notaFinal));
        System.out.println("Situação: " + verificarSituacao());
    }

    // Método main para capturar as informações do usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Capturando informações do aluno
        System.out.println("Insira o nome do aluno:");
        String nome = scanner.nextLine();

        System.out.println("Insira o RA do aluno:");
        String ra = scanner.nextLine();

        System.out.println("O curso é EAD? (sim/nao):");
        String eadStr = scanner.nextLine();
        boolean ead = eadStr.equalsIgnoreCase("sim");

        // Capturando as notas
        System.out.println("Quantas notas o aluno possui? (2, 3 ou 4):");
        int quantidadeNotas = scanner.nextInt();
        double[] notas = new double[quantidadeNotas];

        for (int i = 0; i < quantidadeNotas; i++) {
            System.out.println("Insira a nota " + (i + 1) + ":");
            notas[i] = scanner.nextDouble();
        }

        Aluno aluno;

        if (ead) {
            // Criando o objeto para aluno EAD
            aluno = new Aluno(nome, ra, notas);
        } else {
            // Capturando a frequência do aluno
            System.out.println("Insira a frequência do aluno (%):");
            double frequencia = scanner.nextDouble();

            // Criando o objeto para aluno presencial
            aluno = new Aluno(nome, ra, notas, frequencia);
        }

        // Exibindo as informações do aluno
        aluno.exibirDadosAluno();

        scanner.close();
    }
}
