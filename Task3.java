import java.util.concurrent.BrokenBarrierException;

public class Task3 extends Thread{
    private static Data data;

    public Task3(Data data) {
        this.data = data;
    }
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("T3 started");
        int N = 3;

        //чекати сигнали від T1, T2, T4 про введення
        data.s1.acquire();
        data.s2.acquire();
        data.s3.acquire();

        //Обрахунки
        int a3 = data.maxValue();

        data.countTheSecondOperation(a3);
        int[] A3 = new int[N];
        A3 = data.countTheThirdOperation();
        int[][] MA3 = new int[N][N];
        MA3 = data.countTheFourthOperation();

        //сигнали про завершення обчислень
        data.s6.release(3);

        //чекати сигнали від T1, T3, T4 про завершення обчислень
        data.s4.acquire();
        data.s5.acquire();
        data.s7.acquire();

        //Копіювання спільних ресурсів (a2, B2, MM2, A2, e2, MA2)
        a3 = data.parameterGenerator();
        A3 = data.vectorWithOnes(N);
        MA3 = data.matrixWithOnes(N);
        int[] B3;
        synchronized (data) {
            B3 = data.copyVector(data.B);
        }
        int[][] MM3;
        synchronized (data) {
            MM3 = data.copyMatrix(data.MM);
        }
        int e3;
        synchronized (data) {
            e3 = data.copyNumber(data.e);
        }

        //
        //
        int[] res = data.theResultOfOperation(a3, A3, e3, data.X, MA3);
        data.B1.await();

        data.s8.release(1);
        data.vectorOutput(res);

    }
}
