import java.util.ArrayList;
import java.util.Scanner;

public class Funcionarios {
    private String nome;
    private String cargo;
    private double salario;
    private int mesesEmpresa;
    
    public Funcionarios(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.mesesEmpresa = 0;
    }

    // GETTERS QUE FALTAVAM
    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }
    public int getMesesEmpresa() { return mesesEmpresa; }

    public void adicionarMeses(int meses) {
        this.mesesEmpresa += meses;
    }

    public int getQtdAumentos() {
        return mesesEmpresa / 6;
    }

    public double getSalarioComAumento() {
        double salarioAtual = salario;
        for (int i = 0; i < getQtdAumentos(); i++) {
            salarioAtual *= 1.05;
        }
        return salarioAtual;
    }

    public double getDiferenca() {
        return getSalarioComAumento() - salario;
    }
}

 class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Funcionarios> lista = new ArrayList<>();
        lista.add(new Funcionarios("João", "Gerente", 5000.0));
        lista.add(new Funcionarios("Maria", "Sub-gerente", 3500.0));
        lista.add(new Funcionarios("Pedro", "Desenvolvedor", 3200.0));
        lista.add(new Funcionarios("Ana", "Analista", 3000.0));
        lista.add(new Funcionarios("Carlos", "Designer", 2500.0));
        lista.add(new Funcionarios("Fernanda", "Estagiária", 1500.0));

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== MENU ===");
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i+1) + " - " + lista.get(i).getNome() + " (" + lista.get(i).getMesesEmpresa() + " meses)");
            }
            System.out.println("0 - Gerar Relatório Final");
            System.out.print("Selecione o funcionário: ");
            opcao = scanner.nextInt();

            if (opcao > 0 && opcao <= lista.size()) {
                System.out.print("Digite quantos meses o " + lista.get(opcao-1).getNome() + " tem: ");
                int meses = scanner.nextInt();
                lista.get(opcao-1).adicionarMeses(meses - lista.get(opcao-1).getMesesEmpresa());
                System.out.println("Meses atualizado!");
            }
        }

        System.out.println("\n=== RELATÓRIO FINAL COM DIFERENÇA ===\n");
        double totalFolha = 0;
        double totalDiferenca = 0;

        for (Funcionarios f : lista) {
            double novoSalario = f.getSalarioComAumento();
            double diferenca = f.getDiferenca();

            System.out.println("Funcionario: " + f.getNome());
            System.out.println("  Meses: " + f.getMesesEmpresa() + " -> " + f.getQtdAumentos() + " aumento(s) de 5%");
            System.out.println("  Salario Base: R$ " + String.format("%.2f", f.getSalario()));
            System.out.println("  Novo Salario: R$ " + String.format("%.2f", novoSalario));
            
            if (diferenca > 0) {
                System.out.println("  DIFERENÇA A RECEBER: R$ " + String.format("%.2f", diferenca));
            } else {
                System.out.println("  Sem diferença (menos de 6 meses)");
            }
            System.out.println("-----------------------------------");
            
            totalFolha += novoSalario;
            totalDiferenca += diferenca;
        }

        System.out.println("\nTOTAL DA FOLHA ATUALIZADA: R$ " + String.format("%.2f", totalFolha));
        System.out.println("TOTAL DE AUMENTOS PAGOS: R$ " + String.format("%.2f", totalDiferenca));
        scanner.close();
    }
}
  
 