import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;


public class Data {

    int Task_num = 4;
    int h;
    int N;
    public int[][] MA, MV, MM, MC;
    public int[] R, A, B, X, Z;
    public int e;
    public AtomicInteger a;

    public CyclicBarrier B1 = new CyclicBarrier(Task_num);

    public Semaphore s1 = new Semaphore(0,true);
    public Semaphore s2 = new Semaphore(0,true);
    public Semaphore s3 = new Semaphore(0,true);
    public Semaphore s4 = new Semaphore(0,true);
    public Semaphore s5 = new Semaphore(0,true);
    public Semaphore s6 = new Semaphore(0,true);
    public Semaphore s7 = new Semaphore(0,true);
    public Semaphore s8 = new Semaphore(0,true);


    public  Data(int N) {
        this.N = createN();
       // this.inputOption = inputOption;
        h = N/Task_num;
        a = new AtomicInteger(0);
        MA = new int[N][N];
        MV = new int[N][N];
        MM = new int[N][N];
        MC = new int[N][N];
        MC = new int[][]{{1, 2, 5},
                {9, 3, 2},
                {7, 4, 6}
        };

        R = new int[N];
        A = new int[N];
        B = new int[N];
        X = new int[N];
        Z = new int[N];

        e = 0;

    }
    public static int createN() {
        int N=3;
        return N;
    }

    public int[][] matrixWithOnes(int N) {
        int[][] res = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res[i][j] = 1;
            }
        }
        return res;
    }

    public int[] vectorWithOnes(int N) {
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = 1;
        }
        return res;
    }

    public int parameterGenerator() {
        Random random = new Random();
        int p = random.nextInt(0, 100);
        return p;
    }

    public int[][] copyMatrix(int[][] Matrix) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Matrix[i][j];
            }
        }
        return matrix;
    }

    public int[] copyVector(int[] Vector) {
        int[] vector = new int[N];
        for (int i = 0; i < N; i++) {
                vector[i] = Vector[i];
        }
        return vector;
    }

    public int copyNumber(int p) {
        return p;
    }

    //функції для обрахунків
    public int maxValue() {
        int ai = Z[0];
        for (int i = 0; i < Z.length; i++) {
            if(Z[i] > ai) {
                ai = Z[i];
            }
        }
        return ai;
    }

    public int countTheSecondOperation(int ai) {
        a.addAndGet(ai);
        return ai;
    }

    public int[] countTheThirdOperation() {
        int[] vector = new int[N];
        int[][] matrix = new int[N][N];

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] result = new int[rows];

        for (int j = 0; j < rows; j++) {
            int sum = 0;
            for (int i = 0; i < cols; i++) {
                sum += vector[i] * matrix[i][j];
            }
            result[j] = sum;
        }
        return result;
    }

    public int[][] countTheFourthOperation() {
        int[][] matrix1 = new int[N][N];
        int[][] matrix2 = new int[N][N];
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                int sum = 0;
                for (int k = 0; k < cols1; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }


    //Rh = a*A + e*Xh*MA

    public int[] theResultOfOperation(int a, int[] A, int e, int[] X, int[][]MA) {
        int[] R = new int[N];
        int[] temp = multiplyVectorOnScalar(a, A);
        int[] temp2 = multiplyVectorOnScalar(e, X);
        int[] temp3 = multiplyMatricesOnVector(temp2, MA);
        R = addVectors(temp, temp3);

        System.out.println(R);
        return R;
    }

    //множення вектору на параметр
    public static int[] multiplyVectorOnScalar(int p, int[] vector) {

        int[] result = new int[vector.length];

        for (int i = 0; i < vector.length; i++) {
            int sum;
            sum = p * vector[i];

            result[i] = sum;
        }
        return result;
    }

    //множення вектора на матрицю
    public static int[] multiplyMatricesOnVector( int[] vector, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] result = new int[rows];

        for (int j = 0; j < rows; j++) {
            int sum = 0;
            for (int i = 0; i < cols; i++) {
                sum += vector[i] * matrix[i][j];
            }
            result[j] = sum;
        }
        return result;
    }

    //додавання векторів
    public static int[] addVectors(int[] vector1, int[] vector2) {

        int[] result = new int[vector1.length];

        if (vector1.length != vector2.length) {
            System.out.println("Error! Vectors have to have equal length");
        }

        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] + vector2[i];
        }
        return result;
    }

    public int[] vectorOutput(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.println(Arrays.toString(vector));
        }
        return vector;
    }
}
