package methods;

public abstract class Method {

    protected double[][] matrix;
    protected double[] vector;

    public Method(double[][] matrix, double[] vector) {
        this.matrix = matrix;
        this.vector = vector;
    }

    public abstract void execute();

    protected void printVector(double[] values) {
        System.out.println("Result: ");
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]+ " ");
        }
        System.out.println("\n");
    }

    protected void printMatrix(double[][] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                System.out.print(values[i][j] + "  ");
            }
            System.out.println("\n");
        }
    }

    public static boolean isDiagonallyDominant(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            double diag = Math.abs(matrix[i][i]);
            double sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != i) {
                    sum += Math.abs(matrix[i][j]);
                }
            }
            if (diag <= sum) {
                return false;
            }
        }
        return true;
    }

    public static double[][] makeDiagonallyDominant(double[][] matrix) {
        int n =matrix.length;

        for (int i = 0; i < n; i++) {
            double diag = Math.abs(matrix[i][i]);
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    sum += Math.abs(matrix[i][j]);
                }
            }

            if (diag <= sum) {
                matrix[i][i] = sum + 1;
            }
        }

        return matrix;
    }

    public double calculateNormDifference(double[] X, double[] X_new) {
        double sumSquares = 0.0;

        for (int i = 0; i < X.length; i++) {
            double diff = X_new[i] - X[i];
            sumSquares += diff * diff;
        }

        return Math.sqrt(sumSquares);
    }
}