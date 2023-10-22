package methods;

public class GaussMethod extends Method {


    public GaussMethod(double[][] matrix, double[] vector) {
        super(matrix, vector);
    }

    public  void execute(){
        System.out.println("Your matrix:");
        printMatrix(matrix);
        printVector(calculate());
    };

    public double[] calculate() {


        double[][] L = new double[matrix.length][matrix.length];
        double[][] U = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            L[i][i] = 1;
            for (int j = i; j < matrix.length; j++) {
                U[i][j] = matrix[i][j];
            }
            for (int j = i + 1; j < matrix.length; j++) {
                L[j][i] = matrix[j][i] / U[i][i];
                for (int k = i; k < matrix.length; k++) {
                    matrix[j][k] -= L[j][i] * U[i][k];
                }
            }
        }

        double[] y;
        y = forwardMove(L, vector);

        double[] x;
        x = backwardMove(U, y);

        return x;
    }

    public static double[] forwardMove(double[][] matrix, double[] vector) {
        int n = matrix.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            int maxRowIndex = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRowIndex][i])) {
                    maxRowIndex = k;
                }
            }

            if (maxRowIndex != i) {
                double[] tempRow = matrix[i];
                matrix[i] = matrix[maxRowIndex];
                matrix[maxRowIndex] = tempRow;

                double tempB = vector[i];
                vector[i] = vector[maxRowIndex];
                vector[maxRowIndex] = tempB;
            }

            double pivot = matrix[i][i];
            for (int j = i; j < n; j++) {
                matrix[i][j] /= pivot;
            }
            vector[i] /= pivot;

            for (int k = i + 1; k < n; k++) {
                double factor = matrix[k][i];
                for (int j = i; j < n; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
                vector[k] -= factor * vector[i];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            result[i] = vector[i];
            for (int j = i + 1; j < n; j++) {
                result[i] -= matrix[i][j] * result[j];
            }
        }

        return result;
    }

    public static double[] backwardMove(double[][] matrix, double[] vector) {
        int n = matrix.length;
        double[] x = new double[n];

        for (int i = n - 1; i >= 0; i--) {
            x[i] = vector[i];
            for (int j = i + 1; j < n; j++) {
                x[i] -= matrix[i][j] * x[j];
            }
            x[i] /= matrix[i][i];
        }

        return x;
    }
}


