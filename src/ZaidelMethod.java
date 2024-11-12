import java.util.Arrays;

public class ZaidelMethod {

    // Método para realizar o método de Gauss-Seidel (Zaidel)
    public static double[] gaussSeidel(double[][] A, double[] b, double tolerance, int maxIterations) {
        int n = A.length;
        double[] x = new double[n]; // Aproximação inicial (vetor de zeros)
        Arrays.fill(x, 0);

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            double error = 0;

            for (int i = 0; i < n; i++) {
                double oldX = x[i];
                double sum = b[i];

                // Calcula a soma dos elementos abaixo da diagonal
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= A[i][j] * x[j];
                    }
                }

                // Atualiza o valor de x[i] utilizando o valor mais recente de outras variáveis
                x[i] = sum / A[i][i];

                // Calcula o erro absoluto para o critério de parada
                error += Math.abs(x[i] - oldX);
            }

            // Verifica se o erro está dentro da tolerância para convergência
            if (error < tolerance) {
                System.out.println("Convergiu após " + (iteration + 1) + " iterações.");
                return x;
            }
        }

        System.out.println("Máximo de iterações atingido.");
        return x; // Retorna a última aproximação encontrada
    }

    public static void main(String[] args) {
        // Matriz dos coeficientes
        double[][] A = {
                {4.003, 0.207, 0.519, 0.281},
                {0.416, 3.273, 0.326, 0.375},
                {0.297, 0.351, 2.997, 0.429},
                {0.412, 0.194, 0.215, 3.628}
        };

        // Vetor dos termos independentes
        double[] b = {0.425, 0.021, 0.213, 0.946};

        double tolerance = 1e-6;
        int maxIterations = 1000;

        // Resolve o sistema Ax = b
        double[] solution = gaussSeidel(A, b, tolerance, maxIterations);

        // Exibe as soluções
        System.out.println("Soluções:");
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("x%d = %.6f\n", i + 1, solution[i]);
        }
    }
}
