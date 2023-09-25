package org.example;

public abstract class Method {

    double a;
    double b;
    double epsilon;

    public Method(double a, double b, double epsilon){
        this.a=a;
        this.b=b;
        this.epsilon=epsilon;
    }

    public double function(double x) {
  return Math.pow(x,2) + Math.sin(x) - 12*x - 0.25;
        //return  3*x + Math.cos(x) +1;
        //return  Math.pow(x,2) - 4.0;

    }

    public double derivativeFunction(double x){
        return 2*x + Math.cos(x) - 12;
         //return  3- Math.sin(x) ;
        //return  2.0 * x;
    }

    public  double findMaxDerivative(double a, double b) {
        double epsilon = 1e-6;
        double maxDerivative = Double.NEGATIVE_INFINITY;
        for (double x = a; x <= b; x += epsilon) {
            double derivative = Math.abs(derivativeFunction(x));
            if (derivative > maxDerivative) {
                maxDerivative = derivative;
            }
        }
        return maxDerivative;
    }

    public  double findMinDerivative(double a, double b) {
        double epsilon = 1e-6;
        double minDerivative = Double.POSITIVE_INFINITY;
        for (double x = a; x <= b; x += epsilon) {
            double derivative = Math.abs(derivativeFunction(x));
            if (derivative < minDerivative) {
                minDerivative = derivative;
            }
        }
        return minDerivative;
    }

    public abstract double execute () ;
}
