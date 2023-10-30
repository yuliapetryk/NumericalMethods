import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        String filePath = "\\Users\\User\\Desktop\\University\\PI-33\\Sem1\\NM\\NM_Lab3\\src\\main\\java\\graph.txt";
        double[][] matrix = readMatrixFromFile(filePath);

        //printMatrix(matrix);

        System.out.println();

        if (!checkColumnSum(matrix)) {
            System.out.println("\n" +"The sum of elements in each column must be equal to 1!");
        } else {
            double eps = 0.00001;
            double[] vector= new double[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                vector[i] = 1.0;
            }

            double[] eigenVector =  findEigenVector(matrix , eps);
            double maxEigenValue = findMaxEigenValue(matrix , eps);

            System.out.println("Max eigen value: " + maxEigenValue); //0,99
            System.out.println();
            System.out.println("Result(vector of vertex ranks): ");
            printVector(eigenVector);
            /*
            1.6
            1,2
            0,3
            0,9
            * */
        }
    }

    public static double[] findEigenVector(double[][] matrix,  double eps) throws Exception {
        double[] vector= new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            vector[i] = 1.0;
        }
        int i=1;
        while (true) {
            double[] nextVector = matrixMultiplication(matrix, vector);
            if (normDifference(nextVector, vector) < eps) {
                System.out.println("Accuracy is achieved in " + i + "  iterations");
                System.out.println();
                return nextVector;
            }
            vector = nextVector;
            i++;
        }
    }

    public static double findMaxEigenValue(double[][] matrix, double eps) {
        double[] vector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            vector[i] = 1;
        }

        double maxEigenValue = 0;

        while (true) {
            double[] nextVector = matrixMultiplication(matrix, vector);
            double maxEigenValuePrev = maxEigenValue;
            maxEigenValue = nextVector[0] / vector[0];
            if (Math.abs(maxEigenValue - maxEigenValuePrev)< eps) {
                return maxEigenValue;
            }
            vector = nextVector.clone();
        }
    }

    public static double[] matrixMultiplication(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    public static double normDifference(double[] nextVector, double[] vector) {
        double sum = 0.0;
        for (int i = 0; i < nextVector.length; i++) {
            sum += Math.pow(nextVector[i] - vector[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static boolean checkColumnSum(double[][] matrix) {
        for (int j = 0; j < matrix[0].length; j++) {
            double sum = 0.0;
            for (double[] doubles : matrix) {
                sum += doubles[j];
            }
            if (Math.abs(sum - 1.0) > 0.001) {
                return false;
            }
        }
        return true;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public static void printVector(double[] vector) {
        for (double v : vector) {
            System.out.println(v + " ");
        }
    }

    public static double[][] readMatrixFromFile(String filePath) {
        double[][] matrix = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                if (matrix == null) {
                    matrix = new double[values.length][values.length];
                }
                for (int i = 0; i < values.length; i++) {
                    matrix[rowCount][i] = Double.parseDouble(values[i]);
                }
                rowCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    public static double[] normalizeVector( double[] vector) {
        double norm = 0.0;
        for (double value : vector) {
            norm += Math.pow(value, 2);
        }
        norm = Math.sqrt(norm);

        for (int i = 0; i < vector.length; i++) {
            vector[i] /= norm;
        }
        return vector;
    }
}
