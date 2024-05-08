import java.util.Arrays;
import java.util.Scanner;

public class BankersAlgorithm {
    private static int[][] max;
    private static int[][] alloc;
    private static int[][] need;
    private static int[] avail;
    private static int n, r;

    public static void main(String[] args) {
        System.out.println("********** Banker's Algo ************");
        input(); // Call the input method to get user input
        show(); // Display the initial state of the system
        calculate(); // Run the Banker's algorithm to check for safe state
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the no of Processes");
        n = scanner.nextInt(); // Get the number of processes from the user
        System.out.println("Enter the no of resources instances");
        r = scanner.nextInt(); // Get the number of resource instances from the user
        max = new int[n][r];
        alloc = new int[n][r];
        need = new int[n][r];
        avail = new int[r];
        System.out.println("Enter the Max Matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                max[i][j] = scanner.nextInt(); // Get the maximum resource requirement for each process
            }
        }
        System.out.println("Enter the Allocation Matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                alloc[i][j] = scanner.nextInt(); // Get the current allocation of resources for each process
            }
        }
        System.out.println("Enter the available Resources");
        for (int j = 0; j < r; j++) {
            avail[j] = scanner.nextInt(); // Get the available resource instances
        }
        scanner.close();
    }

    private static void calculate() {
        int[] finish = new int[100];
        int[] safe = new int[100];
        int flag = 1, c1 = 0;
        for (int i = 0; i < n; i++) {
            finish[i] = 0; // Initialize the finish array
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                need[i][j] = max[i][j] - alloc[i][j]; // Calculate the resource need for each process
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
                                avail[k] += alloc[i][k]; // Release the allocated resources
                                System.out.println("Updated avail: " + Arrays.toString(avail)); // Print the updated
                                                                                                // available resources
                                finish[i] = 1; // Mark the process as finished
                                flag = 1; // Set the flag to indicate changes in the system state
                            }

                            System.out.print("P" + i + "->");
                            if (finish[i] == 1) {
                                i = n; // Break out of the loop if a process is finished
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
                System.out.print("P" + i + "->"); // Print the processes that are in deadlock
            }
        }
        if (c1 == n) {
            System.out.println("\n The system is in safe state"); // Print the system is in safe state
        } else {
            System.out.println("\n Process are in dead lock");
            System.out.println("\n System is in unsafe state"); // Print the system is in unsafe state
        }
    }

    private static void show() {
        System.out.println("Process\t Allocation\t Max\t Available\t Remaining\t");
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
            for (int j = 0; j < r; j++) {
                System.out.print(avail[j] + " ");
            }
            System.out.print("\t");
            for (int j = 0; j < r; j++) {
                System.out.print((max[i][j] - alloc[i][j]) + " ");
            }
            System.out.println();
        }
    }

}