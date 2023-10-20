package methods;

public abstract class Method {

    protected final static double EPS = 0.000001;

    protected  double[][] matrix;
    protected  double[] vectorB;
    //protected  int N;

    public Method(double[][] matrix, double[] vectorB) {
        this.matrix = matrix;
        this.vectorB = vectorB;
        //this.N = matrix.length;
    }

    public abstract void run();

    protected void print(double[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println("x" + i + " = " + values[i]);
        }
        System.out.println("\n");
    }
}