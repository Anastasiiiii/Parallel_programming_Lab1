import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

public class Task4 extends Thread{
    private static Data data;

    public Task4(Data data) {
        this.data = data;
    }
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("T4 started");
        Scanner in = new Scanner(System.in);
        int N = 3;
        //Введення вектора B
        int[] B = new int[N];
        System.out.println("Введіть елементи вектора B:");
        for (int i = 0; i < N; i++) {
            B[i] = in.nextInt();
        }

        for (int i = 0; i < N; i++) {
            System.out.println(B[i] + " ");
        }

        //Введення вектора X
        int[] X = new int[N];
        System.out.println("Введіть елементи вектора X:");
        for (int i = 0; i < N; i++) {
            X[i] = in.nextInt();
        }

        for (int i = 0; i < N; i++) {
            System.out.println(X[i] + " ");
        }

        //Введення вектора Z
        int[] Z = new int[N];
        System.out.println("Введіть елементи вектора Z:");
        for (int i = 0; i < N; i++) {
            Z[i] = in.nextInt();
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Z[i] + " ");
        }

        //Введення параметру
        System.out.println("Введіть параметр e:");
        int e = in.nextInt();
        System.out.println(e);

        //сигнали T1, T3, T4 про введення
        data.s3.release(3);

        //чекати сигнали від T1, T2 про введення
        data.s1.acquire();
        data.s2.acquire();

        //Обрахунки
        int a4 = data.maxValue();

        data.countTheSecondOperation(a4);
        int[] A4 = new int[N];
        A4 = data.countTheThirdOperation();
        int[][] MA4 = new int[N][N];
        MA4 = data.countTheFourthOperation();

        //сигнали про завершення обчислень
        data.s7.release(3);

        //чекати сигнали від T1, T3, T4 про завершення обчислень
        data.s4.acquire();
        data.s5.acquire();
        data.s6.acquire();

        //Копіювання спільних ресурсів (a2, B2, MM2, A2, e2, MA2)
        a4 = data.parameterGenerator();
        A4 = data.vectorWithOnes(N);
        MA4 = data.matrixWithOnes(N);
        int[] B4;
        synchronized (data) {
            B4 = data.copyVector(data.B);
        }
        int[][] MM4;
        synchronized (data) {
            MM4 = data.copyMatrix(data.MM);
        }
        int e4;
        synchronized (data) {
            e4 = data.copyNumber(data.e);
        }

        //
        //
        int[] res = data.theResultOfOperation(a4, A4, e4, data.X, MA4);
        data.B1.await();

        data.s8.release(1);
        data.vectorOutput(res);
    }
}
