public class Gauss {

        // Método para realizar a eliminação de Gauss
        public static double[] gaussElimination(double[][] augmentedMatrix) {
            int n = augmentedMatrix.length;

            // Eliminação para transformar a matriz em forma triangular superior
            for (int i = 0; i < n; i++) {
                // Normaliza o pivô
                double pivot = augmentedMatrix[i][i];
                for (int j = i; j <= n; j++) { // n+1 é a coluna dos termos independentes
                    augmentedMatrix[i][j] /= pivot;
                }

                // Subtrai múltiplos da linha pivô das linhas abaixo
                for (int k = i + 1; k < n; k++) {
                    double factor = augmentedMatrix[k][i];
                    for (int j = i; j <= n; j++) {
                        augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                    }
                }
            }

            // Vetor para armazenar as soluções
            double[] solution = new double[n];

            // Substituição reversa para resolver as variáveis
            for (int i = n - 1; i >= 0; i--) {
                solution[i] = augmentedMatrix[i][n]; // Última coluna
                for (int j = i + 1; j < n; j++) {
                    solution[i] -= augmentedMatrix[i][j] * solution[j];
                }
            }

            return solution;
        }

        public static void main(String[] args) {
            // Exemplo de matriz aumentada
            double[][] augmentedMatrix = {
                    {4.003, 0.207, 0.519, 0.281, 0.425},
                    {0.416, 3.273, 0.326, 0.375, 0.021},
                    {0.297, 0.351, 2.997, 0.429, 0.213},
                    {0.412, 0.194, 0.215, 3.628, 0.946}
            };

            double[] solution = gaussElimination(augmentedMatrix);

            // Exibe a solução
            System.out.println("Soluções:");
            for (int i = 0; i < solution.length; i++) {
                System.out.printf("x%d = %.4f\n", i + 1, solution[i]);
            }
        }


}
