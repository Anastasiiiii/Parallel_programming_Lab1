import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

public class Task1 extends Thread {
    private static Data data;

    public Task1(Data data) {
        this.data = data;
    }
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("T1 started");
        //Введення матриці MV
        Scanner in = new Scanner(System.in);
        int N = 3;

        int[][] MV = new int[N][N];

        System.out.println("Введіть елементи матриці MV з розміром 3x3:");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MV[i][j] = in.nextInt();
            }
        }

        System.out.println("Введена матриця MV:");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(MV[i][j] + " ");
            }
            System.out.println();
        }

        //сигнал T2,T3,T4 про введення матиці MV
        data.s1.release(3);

        //чекати сигнали від T2, T4 про введення данних
        data.s2.acquire();
        data.s3.acquire();

        int a1 = data.maxValue();

        data.countTheSecondOperation(a1);
        int[] A1 = new int[N];
        A1 = data.countTheThirdOperation();
        int[][] MA1 = new int[N][N];
        MA1 = data.countTheFourthOperation();
        //сигнали про завершення обчислень T2, T3, T4
        data.s4.release(3);

        //чекати сигнали від T2, T3, T4
        data.s5.acquire();
        data.s6.acquire();
        data.s7.acquire();

        //Копіювання спільних ресурсів (a1, B1, MM1, A1, e1, MA1)
        a1 = data.parameterGenerator();
        A1 = data.vectorWithOnes(N);
        MA1 = data.matrixWithOnes(N);
        int[] B1;
        synchronized (data) {
            B1 = data.copyVector(data.B);
        }
        int[][] MM1;
        synchronized (data) {
            MM1 = data.copyMatrix(data.MM);
        }
        int e1;
        synchronized (data) {
            e1 = data.copyNumber(data.e);
        }

        //
        int[] res = data.theResultOfOperation(a1, A1, e1, data.X, MA1);
        data.B1.await();

        data.s8.release(1);
        data.vectorOutput(res);
    }

}
