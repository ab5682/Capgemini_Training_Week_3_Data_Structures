import java.util.*;

class Process {
    int processID;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime = 0;
    int turnaroundTime = 0;
    Process next;

    public Process(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}

class RoundRobinScheduler {
    private Process head = null;

    public void addProcess(int pid, int burstTime, int priority) {
        Process newNode = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    public void removeProcess(Process target) {
        if (head == null) return;

        if (head == target && head.next == head) {
            head = null;
            return;
        }

        Process curr = head, prev = null;
        while (curr != target) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == head) {
            while (curr.next != head) curr = curr.next;
            curr.next = head.next;
            head = head.next;
        } else {
            prev.next = curr.next;
        }
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes.");
            return;
        }

        Process temp = head;
        do {
            System.out.println("PID: " + temp.processID + ", Remaining: " + temp.remainingTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public void roundRobin(int timeQuantum) {
        if (head == null) return;

        Process curr = head;
        int time = 0;
        List<Integer> pids = new ArrayList<>();
        Map<Integer, Integer> waitingTimes = new HashMap<>();
        Map<Integer, Integer> turnaroundTimes = new HashMap<>();

        while (head != null) {
            Process nextProcess = curr.next;
            if (curr.remainingTime > timeQuantum) {
                curr.remainingTime -= timeQuantum;
                time += timeQuantum;
            } else {
                time += curr.remainingTime;
                curr.remainingTime = 0;
                curr.turnaroundTime = time;
                curr.waitingTime = curr.turnaroundTime - curr.burstTime;
                waitingTimes.put(curr.processID, curr.waitingTime);
                turnaroundTimes.put(curr.processID, curr.turnaroundTime);
                pids.add(curr.processID);
                removeProcess(curr);
            }

            if (head != null) curr = nextProcess;

            System.out.println("\nProcesses after " + time + " units:");
            displayProcesses();
        }

        System.out.println("\n--- Completion Stats ---");
        double totalWT = 0, totalTAT = 0;
        for (int pid : pids) {
            int wt = waitingTimes.get(pid);
            int tat = turnaroundTimes.get(pid);
            System.out.println("PID: " + pid + ", Waiting Time: " + wt + ", Turnaround Time: " + tat);
            totalWT += wt;
            totalTAT += tat;
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / pids.size());
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / pids.size());
    }
}

public class RoundRobinDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter burst time and priority for process " + i + ": ");
            int bt = sc.nextInt();
            int pr = sc.nextInt();
            scheduler.addProcess(i, bt, pr);
        }

        System.out.print("Enter time quantum: ");
        int tq = sc.nextInt();

        scheduler.roundRobin(tq);
    }    
}
