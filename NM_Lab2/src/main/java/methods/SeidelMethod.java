package methods;


public class SeidelMethod extends Method {

    private   double epsilon;
    public SeidelMethod(double[][] matrix, double[] vector, double epsilon) {
        super(matrix, vector);
        this.epsilon=epsilon;

    }

    public  void execute(){
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
        int n = matrix.length;
        double[] X = new double[n];

        for (int k = 0; k < maxIterations; k++) {
            double maxDiff = 0.0;

            for (int i = 0; i < n; i++) {
                double sum1 = 0.0;
                double sum2 = 0.0;

                for (int j = 0; j < i; j++) {
                    sum1 += matrix[i][j] * X[j];
                }

                for (int j = i + 1; j < n; j++) {
                    sum2 += matrix[i][j] * X[j];
                }

                double newX = (vector[i] - sum1 - sum2) / matrix[i][i];


                double diff = Math.abs(newX - X[i]);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }

                X[i] = newX;
            }

            if (maxDiff > epsilon) {
                System.out.println((k+1) + " iterations were performed");
                return X;
            }
        }

        throw new IllegalArgumentException(maxIterations + " iterations are not enough to find a solution");
    }

}