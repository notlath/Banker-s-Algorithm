import java.util.Scanner;

public class BankersAlgorithm {
    int[][] max;
    int[][] alloc;
    int[][] need;
    int[] avail;
    int n, r;

    public static void main(String[] args) {
        BankersAlgorithm ba = new BankersAlgorithm();
        ba.input();
        ba.show();
        ba.cal();
    }

    void input() {
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

    void show() {

        System.out.println("Process\t Allocation\t Max\t Available\t Need\t");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i) + "\t   ");
            for (int j = 0; j < r; j++) {
                System.out.print(alloc[i][j] + " ");
            }
            System.out.print("\t");
            for (int j = 0; j < r; j++) {
                System.out.print(max[i][j] + " ");
            }
            System.out.print("\t");
            System.out.print("  ");
            if (i == 0) {

                for (int j = 0; j < r; j++)
                    System.out.print(avail[j] + " ");

            }

            else {
                // Print the value of the available resources [avail - alloc]
                for (int j = 0; j < r; j++) {
                    int sum = 0;
                    for (int k = 0; k < i; k++) {

                        sum += alloc[k][j];
                    }
                    System.out.print((avail[j] + sum) + " ");
                }

            }
            System.out.print("   ");
            System.out.print("\t");
            for (int j = 0; j < r; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }
    }

    void cal() {
        int[] finish = new int[n];
        int flag = 1;
        int c1 = 0;
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
        if (c1 == n) // what is c1?
        //
        {

            System.out.println("\nThe system is in safe state");
        } else {
            System.out.println("\nProcess are in dead lock");
            System.out.println("System is in unsafe state");
        }
    }
}