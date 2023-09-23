package org.example;

public class Newton extends Method {

    public Newton(double a, double b, double epsilon){
        super(a,b,epsilon);
    }

    public  double execute () {
        double x = b;
        int i = 0;
        while(Math.abs(function(x)) >=  epsilon){
            x -= function(x) / derivativeFunction(x);
            i++;
        }
        System.out.println("Number of iterations = "+ i);
        return x;
    };


}





