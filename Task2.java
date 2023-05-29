import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

public class Task2 extends Thread{
    private static Data data;

    public Task2(Data data) {
        this.data = data;
    }
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("T2 started");
        //Введення матриці MM
        Scanner in = new Scanner(System.in);
        int N = 3;

        int[][] MM = new int[N][N];

        System.out.println("Введіть елементи матриці MM з розміром 3x3:");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MM[i][j] = in.nextInt();
            }
        }

        System.out.println("Введена матриця MM:");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(MM[i][j] + " ");
            }
            System.out.println();
        }

        //Введення вектора R
        int[] R = new int[N];
        System.out.println("Введіть елементи вектора R:");
        for (int i = 0; i < N; i++) {
            R[i] = in.nextInt();
        }

        //Виведення вектора
        for (int i = 0; i < N; i++) {
            System.out.println(R[i] + " ");
        }

        //сигнали T1, T3, T4 про введення
        data.s2.release(3);

        //чекати сигнали від T1, T4 про введення
        data.s1.acquire();
        data.s3.acquire();

        //Обрахунки
        int a2 = data.maxValue();

        data.countTheSecondOperation(a2);
        int[] A2 = new int[N];
        A2 = data.countTheThirdOperation();
        int[][] MA2 = new int[N][N];
        MA2 = data.countTheFourthOperation();

        //сигнали про завершення обчислень
        data.s5.release(3);

        //чекати сигнали від T1, T3, T4 про завершення обчислень
        data.s4.acquire();
        data.s6.acquire();
        data.s7.acquire();

        //Копіювання спільних ресурсів (a2, B2, MM2, A2, e2, MA2)
        a2 = data.parameterGenerator();
        A2 = data.vectorWithOnes(N);
        MA2 = data.matrixWithOnes(N);
        int[] B2;
        synchronized (data) {
            B2 = data.copyVector(data.B);
        }
        int[][] MM2;
        synchronized (data) {
            MM2 = data.copyMatrix(data.MM);
        }
        int e2;
        synchronized (data) {
            e2 = data.copyNumber(data.e);
        }

        //
        //
        int[] res = data.theResultOfOperation(a2, A2, e2, data.X, MA2);
        data.B1.await();

        data.s8.release(1);
        data.vectorOutput(res);
    }
}
