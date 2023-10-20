package methods;


public class JacobiMethod extends Method {

    private final double epsilon;

    public JacobiMethod(double[][] matrix, double[] vector, double epsilon) {
        super(matrix, vector);
        this.epsilon=epsilon;
    }

    public  void execute(){
        if (!isDiagonallyDominant(matrix)) {
            System.out.println("The matrix is not diagonally dominant. You cannot use the Jacobi method.");
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
                double sum = 0.0;
                for (int j = 0; j <matrix [i].length; j++) {
                    if (j != i) {
                        sum += matrix[i][j] * X[j];
                    }
                }
                X_new[i] = (vector[i] - sum) / matrix[i][i];
            }

            double maxDiff = 0.0;
            for (int i = 0; i < X.length; i++) {
                double diff = Math.abs(X_new[i] - X[i]);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }

            if (maxDiff <epsilon) {
                System.out.println((k+1) + " iterations were performed");
                return X;
            }

            System.arraycopy(X_new, 0, X, 0, X.length);
        }
        throw new IllegalArgumentException(maxIterations + " iterations are not enough to find a solution");


    }


}