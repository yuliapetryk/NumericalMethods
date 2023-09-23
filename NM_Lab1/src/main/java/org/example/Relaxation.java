package org.example;
public class Relaxation extends Method {

    public Relaxation(double a, double b, double epsilon){
        super(a,b,epsilon);
    }

    public  double execute () {
        double max, min;
        max=findMaxDerivative(a,b);
        min=findMinDerivative(a,b);
        double tau=-2.0/(min+max);
        double x = b;
        double nextX=a;
        int i=0;
        while (Math.abs(x - nextX) >= epsilon) {
            x = nextX;
            nextX= x + tau * function(x);
            i++;
        }
        System.out.println("Number of iterations = "+ i);
        return nextX;
    };
}
