import java.util.Scanner;

public class BankersAlgorithm {
    // Declare matrices and arrays to hold the maximum demand, current allocation,
    // remaining need, and available resources
    private static int[][] max;
    private static int[][] alloc;
    private static int[][] need;
    private static int[] avail;
    private static int n, r; // n is the number of processes, r is the number of resources

    public static void main(String[] args) {
        System.out.println("********** Banker's Algo ************");
        input(); // Get user input for number of processes, resources, max demand, current
                 // allocation, and available resources
        show(); // Display the initial state of the system
        calculate(); // Run the Banker's algorithm to check if the system is in a safe state
    }

    private static void input() {
        // This method gets user input for number of processes, resources, max demand,
        // current allocation, and available resources
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

        int[] finish = new int[n];
        int[] safe = new int[n];
        need = new int[n][r];
        int flag = 1, c1 = 0; // flag is used to indicate changes in the system state, c1 is used to count the
                              // number of finished processes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }
        System.out.println();
        // The following loop continues as long as there are changes in the system state
        while (flag == 1) {
            flag = 0;
            for (int i = 0; i < n; i++) {
                int c = 0; // Counter to track the number of resources a process needs that are available
                for (int j = 0; j < r; j++) {
                    if ((finish[i] == 0) && (need[i][j] <= avail[j])) {
                        c++;
                        if (c == r) {
                            for (int k = 0; k < r; k++) {
                                avail[k] += alloc[i][k]; // Release the allocated resources
                                finish[i] = 1; // Mark the process as finished
                                flag = 1; // Set the flag to indicate changes in the system state
                            }
                            System.out.print("P" + i + " -> "); // Print the process that just finished
                            if (finish[i] == 1) {
                                i = n; // Break out of the loop if a process is finished
                            }
                        }
                    }
                }
            }
        }
        // The following loop checks if all processes have finished executing
        for (int i = 0; i < n; i++) {
            if (finish[i] == 1) {
                c1++;
            } else {
                System.out.print("P" + i + " -> "); // Print the processes that are in deadlock
            }
        }
        if (c1 == n) {
            System.out.println("\nThe system is in safe state"); // Print the system is in safe state
        } else {
            System.out.println("\nProcess are in dead lock");
            System.out.println("\nSystem is in unsafe state"); // Print the system is in unsafe state
        }
    }

    private static void show() {
        // This method displays the initial state of the system
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
            System.out.print("   ");
            for (int j = 0; j < r; j++) {
                avail[j] = avail[j] + alloc[i][j];
                System.out.print(avail[j] + " ");
            }
            System.out.print("\t");
            System.out.print("   ");
            for (int j = 0; j < r; j++) {
                System.out.print((max[i][j] - alloc[i][j]) + " ");
            }
            System.out.println();
        }
    }
}