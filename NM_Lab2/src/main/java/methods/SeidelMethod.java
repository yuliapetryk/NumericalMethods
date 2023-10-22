package methods;


public class SeidelMethod extends Method {

    private   double epsilon;
    public SeidelMethod(double[][] matrix, double[] vector, double epsilon) {
        super(matrix, vector);
        this.epsilon=epsilon;

    }

    public  void execute(){
        System.out.println("Your matrix:");
        printMatrix(matrix);
        if (!isDiagonallyDominant(matrix)) {
            System.out.println("The matrix is not diagonally dominant. You cannot use the Seidel method.");
            matrix=makeDiagonallyDominant(matrix);
            System.out.println("Your matrix now looks like this");
            printMatrix(matrix);
        }
        printVector(calculate());

    };
    public double[] calculate() {
        int maxIterations = 1000;

        double[] X = new double[vector.length];
        double[] X_new = new double[vector.length];

        for (int k = 0; k < maxIterations; k++) {
            for (int i = 0; i < matrix.length; i++) {
                double sum1 = 0.0;
                double sum2 = 0.0;

                for (int j = 0; j < i; j++) {
                    sum1 += matrix[i][j] * X_new[j];
                }

                for (int j = i + 1; j < matrix[i].length; j++) {
                    sum2 += matrix[i][j] * X[j];
                }

                X_new[i] = (vector[i] - sum1 - sum2) / matrix[i][i];
            }

            double normDiff = calculateNormDifference(X, X_new);

            if (normDiff < epsilon) {
                System.out.println((k + 1) + " iterations were performed");
                System.out.println();
                return X;
            }

            System.arraycopy(X_new, 0, X, 0, X.length);
        }

        throw new IllegalArgumentException(maxIterations + " iterations are not enough to find a solution");
    }

}