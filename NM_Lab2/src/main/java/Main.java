import methods.GaussMethod;
import methods.JacobiMethod;
import methods.Method;
import methods.SeidelMethod;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static double[][] matrix;
    public static double[] vector;
    public static void main(String[] args) {

        double epsilon = 0.5;

         //matrix = new double[][]{{3, -1, 1}, {-1, 2, 0.5}, {1, 0.5, 3}};
        // vector = new double[]{1, 1.75, 2.5};

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the matrix size");
            int size = scanner.nextInt();

            System.out.println("Choose the matrix you want to work with");
            System.out.println("[r] Random matrix ");
            System.out.println("[h] Hilbert matrix");
            System.out.println("[e] Exit");
            char expression = scanner.next().charAt(0);
            System.out.println();




            switch (expression) {
                case 'r':
                    matrix = buildRandomMatrix(size);
                    break;

                case 'h':
                    matrix = buildRandomHilbertMatrix(size);
                    break;

                case 'e':
                    System.out.println("Exiting the program.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }
            System.out.println("Choose the method you want to use");

            System.out.println("[g] Gauss method ");
            System.out.println("[j] Jacobi method");
            System.out.println("[s] Seidel method");
            System.out.println("[e] Exit");

            expression = scanner.next().charAt(0);
            System.out.println();

            vector=buildVector(matrix);
            Method method = new GaussMethod(matrix, vector);

            switch (expression) {
                case 'g':
                    System.out.println("Gauss method");
                    method = new GaussMethod(matrix, vector);
                    break;

                case 'j':
                    System.out.println("Jacobi method");
                    method = new JacobiMethod(matrix, vector, epsilon);
                    break;

                case 's':
                    System.out.println("Seidel method");
                    method = new SeidelMethod(matrix, vector, epsilon);
                    break;

                case 'e':
                    System.out.println("Exiting the program.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }
            method.execute();
        }
    }

    public static double[][] buildRandomMatrix(int size) {
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new Random().nextDouble(10.0);
            }
        }
        return matrix;
    }

    public static double[][] buildRandomHilbertMatrix(int size) {
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 1.0 / ((i + 1) + (j + 1) - 1.0);
            }
        }
        return matrix;
    }

    public static double[] buildVector(double[][] matrix) {
        double[] vector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            vector[i] = 0;
            for (int j = 0; j < matrix.length; j++) {
                vector[i] += matrix[i][j];
            }
        }
        return vector;
    }
}
