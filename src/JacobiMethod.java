import java.util.Arrays;

public class JacobiMethod {

    public static double[] jacobi(double[][] A, double[] b, double tolerance, int maxIterations) {
        int n = A.length;
        double[] xOld = new double[n];
        double[] xNew = new double[n];
        Arrays.fill(xOld, 0); // Aproximação inicial é um vetor de zeros

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (int i = 0; i < n; i++) {
                double sum = b[i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= A[i][j] * xOld[j];
                    }
                }
                xNew[i] = sum / A[i][i];
            }

            // Verifica a convergência
            double error = 0;
            for (int i = 0; i < n; i++) {
                error += Math.abs(xNew[i] - xOld[i]);
            }
            if (error < tolerance) {
                System.out.println("Convergiu após " + (iteration + 1) + " iterações.");
                return xNew;
            }

            // Atualiza xOld para a próxima iteração
            System.arraycopy(xNew, 0, xOld, 0, n);
        }

        System.out.println("Máximo de iterações atingido.");
        return xNew; // Retorna a última aproximação
    }

    public static void main(String[] args) {
        double[][] A = {
                {4.003, 0.207, 0.519, 0.281},
                {0.416, 3.273, 0.326, 0.375},
                {0.297, 0.351, 2.997, 0.429},
                {0.412, 0.194, 0.215, 3.628}
        };
        double[] b = {0.425, 0.021, 0.213, 0.946};
        double tolerance = 1e-6;
        int maxIterations = 1000;

        double[] solution = jacobi(A, b, tolerance, maxIterations);

        System.out.println("Soluções:");
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("x%d = %.6f\n", i + 1, solution[i]);
        }
    }
}
