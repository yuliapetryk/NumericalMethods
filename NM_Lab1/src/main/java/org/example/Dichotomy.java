package org.example;
public class Dichotomy extends Method {

    public Dichotomy(double a, double b, double epsilon){
        super(a,b,epsilon);
    }

    public  double execute () {
        double c=0;
        int i=0;
            if (!(function(a) * function(b) < 0)) {
                System.out.println("You can not use Dichotomy method!");}
              else {
                do {
                    c = (a + b) / 2;
                    i++;
                    if (function(a) * function(c) < 0) {
                        b = c;
                    } else a = c;
                } while (Math.abs(b - a) > epsilon);
                System.out.println("Number of iterations = "+ i);
            }
        return c;
    };
}
