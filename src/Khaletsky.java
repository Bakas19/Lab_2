public class Khaletsky {



        // Método para realizar a decomposição LU
        public static void luDecomposition(double[][] A, double[][] L, double[][] U) {
            int n = A.length;

            for (int i = 0; i < n; i++) {
                // Preenche matriz U
                for (int j = i; j < n; j++) {
                    double sum = 0;
                    for (int k = 0; k < i; k++) {
                        sum += L[i][k] * U[k][j];
                    }
                    U[i][j] = A[i][j] - sum;
                }

                // Preenche matriz L
                for (int j = i; j < n; j++) {
                    if (i == j) {
                        L[i][i] = 1; // Diagonal principal de L é 1
                    } else {
                        double sum = 0;
                        for (int k = 0; k < i; k++) {
                            sum += L[j][k] * U[k][i];
                        }
                        L[j][i] = (A[j][i] - sum) / U[i][i];
                    }
                }
            }
        }

        // Método para resolver Ly = b usando substituição direta
        public static double[] forwardSubstitution(double[][] L, double[] b) {
            int n = L.length;
            double[] y = new double[n];

            for (int i = 0; i < n; i++) {
                y[i] = b[i];
                for (int j = 0; j < i; j++) {
                    y[i] -= L[i][j] * y[j];
                }
            }

            return y;
        }

        // Método para resolver Ux = y usando substituição reversa
        public static double[] backwardSubstitution(double[][] U, double[] y) {
            int n = U.length;
            double[] x = new double[n];

            for (int i = n - 1; i >= 0; i--) {
                x[i] = y[i];
                for (int j = i + 1; j < n; j++) {
                    x[i] -= U[i][j] * x[j];
                }
                x[i] /= U[i][i];
            }

            return x;
        }

        // Método principal para resolver o sistema Ax = b
        public static double[] solve(double[][] A, double[] b) {
            int n = A.length;
            double[][] L = new double[n][n];
            double[][] U = new double[n][n];

            // Passo 1: Decomposição LU
            luDecomposition(A, L, U);

            // Passo 2: Resolve Ly = b
            double[] y = forwardSubstitution(L, b);

            // Passo 3: Resolve Ux = y
            return backwardSubstitution(U, y);
        }

        public static void main(String[] args) {
            // Matriz de coeficientes
            double[][] A = {
                    {4.003, 0.207, 0.519, 0.281},
                    {0.416, 3.273, 0.326, 0.375},
                    {0.297, 0.351, 2.997, 0.429},
                    {0.412, 0.194, 0.215, 3.628}
            };

            // Vetor dos termos independentes
            double[] b = {0.425, 0.021, 0.213, 0.946};

            // Resolve o sistema Ax = b
            double[] solution = solve(A, b);

            // Exibe a solução
            System.out.println("Soluções:");
            for (int i = 0; i < solution.length; i++) {
                System.out.printf("x%d = %.4f\n", i + 1, solution[i]);
            }
        }


}
