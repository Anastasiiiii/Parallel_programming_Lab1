import java.util.Scanner;
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
    public String inputOption;
    public CyclicBarrier B1 = new CyclicBarrier(Task_num);

    public Semaphore s1 = new Semaphore(0,true);
    public Semaphore s2 = new Semaphore(0,true);
    public Semaphore s3 = new Semaphore(0,true);
    public Semaphore s4 = new Semaphore(0,true);
    public Semaphore s5 = new Semaphore(0,true);
    public Semaphore s6 = new Semaphore(0,true);
    public Semaphore s7 = new Semaphore(0,true);
    public Semaphore s8 = new Semaphore(0,true);

    public int[][] index;

    public  Data(int N, String inputOption) {
        this.N = createN();
        this.inputOption = inputOption;
        h = N/Task_num;
        a = new AtomicInteger(0);
        MA = new int[N][N];
        MV = new int[N][N];
        MM = new int[N][N];
        MC = new int[N][N];

        R = new int[N];
        A = new int[N];
        B = new int[N];
        X = new int[N];
        Z = new int[N];

        e = 0;

    }
    public static int createN() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = in.nextInt();
        System.out.println(N);
        return N;
    }


}
