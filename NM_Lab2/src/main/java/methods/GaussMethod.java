package methods;

public class GaussMethod extends Method {


    public GaussMethod(double[][] matrix, double[] vector) {
        super(matrix, vector);

    }

    public  void execute(){
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

        double[] y = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            y[i] = vector[i];
            for (int j = 0; j < i; j++) {
                y[i] -= L[i][j] * y[j];
            }
        }

        double[] X = new double[matrix.length];
        for (int i = matrix.length - 1; i >= 0; i--) {
            X[i] = y[i];
            for (int j = i + 1; j < matrix.length; j++) {
                X[i] -= U[i][j] * X[j];
            }
            X[i] /= U[i][i];
        }

        return X;
    }
    }



