/*
Програмне забезпечення високопродуктивних комп'ютерних систем
Лабораторна робота №1
Фартушняк Анастасія ІП-04
Варіант 21

Завдання:
R = max(Z)*(B*MV) +e*X*(MM*MC)
Введення, виведення даних:
(1) MV
(2) MM, R
(3) -
(4) B, X, e, Z
*/


public class Main {
    public static void main(String[] args) throws InterruptedException {
        long time1 = System.currentTimeMillis();
        System.out.println("All threads started");
        int N = 3;
        Data data = new Data(N);

        Task1 T1 = new Task1(data);
        Task2 T2 = new Task2(data);
        Task3 T3 = new Task3(data);
        Task4 T4 = new Task4(data);

        T1.start();
        T2.start();
        T3.start();
        T4.start();

        T1.join();
        T2.join();
        T3.join();
        T4.join();

        System.out.println("All threads finished");

        long time2 = System.currentTimeMillis();
        long time = time2 - time1;
        System.out.println("Execution time (ms): " + time);
    }
}
