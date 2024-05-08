import java.util.Scanner;

public class BankersAlgorithm {
    private static int[][] max;
    private static int[][] alloc;
    private static int[][] need;
    private static int[] avail;
    private static int n, r;

    public static void main(String[] args) {
        System.out.println("********** Banker's Algo ************");
        input();
        show();
        calculate();
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the no of Processes");
        n = scanner.nextInt();
        System.out.println("Enter the no of resources instances");
        r = scanner.nextInt();
        max = new int[n][r];
        alloc = new int[n][r];
        need = new int[n][r];
        avail = new int[r];
        System.out.println("Enter the Max Matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                max[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Enter the Allocation Matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                alloc[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Enter the available Resources");
        for (int j = 0; j < r; j++) {
            avail[j] = scanner.nextInt();
        }
    }

    private static void show() {
        System.out.println("Process\t Allocation\t Max\t Available\t");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + "\t   ");
            for (int j = 0; j < r; j++) {
                System.out.print(alloc[i][j] + " ");
            }
            System.out.print("\t");
            for (int j = 0; j < r; j++) {
                System.out.print(max[i][j] + " ");
            }
            System.out.print("\t");
            if (i == 0) {
                for (int j = 0; j < r; j++)
                    System.out.print(avail[j] + " ");
            }
            System.out.println();
        }
    }

    private static void calculate() {
        int[] finish = new int[100];
        int[] safe = new int[100];
        int flag = 1, c1 = 0;
        for (int i = 0; i < n; i++) {
            finish[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }
        while (flag == 1) {
            flag = 0;
            for (int i = 0; i < n; i++) {
                int c = 0;
                for (int j = 0; j < r; j++) {
                    if ((finish[i] == 0) && (need[i][j] <= avail[j])) {
                        c++;
                        if (c == r) {
                            for (int k = 0; k < r; k++) {
                                avail[k] += alloc[i][j];
                                finish[i] = 1;
                                flag = 1;
                            }
                            System.out.print("P" + i + "->");
                            if (finish[i] == 1) {
                                i = n;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (finish[i] == 1) {
                c1++;
            } else {
                System.out.print("P" + i + "->");
            }
        }
        if (c1 == n) {
            System.out.println("\n The system is in safe state");
        } else {
            System.out.println("\n Process are in dead lock");
            System.out.println("\n System is in unsafe state");
        }
    }
}