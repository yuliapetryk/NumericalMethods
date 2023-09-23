package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input a,b ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        System.out.println("Input epsilon ");
        double epsilon = scanner.nextDouble();

        while(true) {

            System.out.println("Choose the method you want to test");
            System.out.println("[d] Dichotomy method");
            System.out.println("[n] Newton method");
            System.out.println("[r] Relaxation method");
            System.out.println("[e] Exit");

            char expression=scanner.next().charAt(0);
            System.out.println();

            switch (expression) {
                case 'd':
                    System.out.println("Dichotomy method");
                    Method dichotomy = new Dichotomy(a, b, epsilon);
                    System.out.println("Result = " + dichotomy.execute());
                    System.out.println();
                    break;

                case 'n':
                    System.out.println("Newton method");
                    Method newton = new Newton(a, b, epsilon);
                    System.out.println("Result = " + newton.execute());
                    break;

                case 'r':
                    System.out.println("Relaxation method");
                    Method relaxation = new Relaxation(a, b, epsilon);
                    System.out.println("Result = " + relaxation.execute());
                    break;

                case 'e':
                    System.out.println("Exiting the program.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }

            System.out.println();
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            scanner.nextLine();
        }

    }
}