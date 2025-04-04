import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head = null;
    private Task current = null;

    public void addFirst(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            Task temp = head;
            while (temp.next != head)
                temp = temp.next;
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
        current = head;
    }

    public void addLast(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            Task temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = newTask;
            newTask.next = head;
        }
    }

    public void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos == 0) {
            addFirst(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 0; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
    }

    public void removeById(int id) {
        if (head == null) return;

        if (head.id == id) {
            if (head.next == head) {
                head = null;
                current = null;
                return;
            }
            Task last = head;
            while (last.next != head) {
                last = last.next;
            }
            head = head.next;
            last.next = head;
            current = head;
            return;
        }

        Task temp = head;
        while (temp.next != head && temp.next.id != id) {
            temp = temp.next;
        }

        if (temp.next.id == id) {
            temp.next = temp.next.next;
        }
    }

    public void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks.");
            return;
        }
        printTask(current);
    }

    public void moveToNextTask() {
        if (current != null)
            current = current.next;
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) return;
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                printTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No task found with this priority.");
    }

    private void printTask(Task t) {
        System.out.println("ID: " + t.id + ", Name: " + t.name + ", Priority: " + t.priority + ", Due Date: " + t.dueDate);
    }
}

public class TaskManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();

        while (true) {
            System.out.println("\n1. Add First\n2. Add Last\n3. Add at Position\n4. Remove by ID\n5. View Current Task\n6. Next Task\n7. Display All Tasks\n8. Search by Priority\n9. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addFirst(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                }
                case 2 -> {
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addLast(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                }
                case 3 -> {
                    System.out.print("Enter Position, ID, Name, Priority, Due Date: ");
                    int pos = sc.nextInt();
                    scheduler.addAtPosition(pos, sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                }
                case 4 -> {
                    System.out.print("Enter ID to remove: ");
                    scheduler.removeById(sc.nextInt());
                }
                case 5 -> scheduler.viewCurrentTask();
                case 6 -> scheduler.moveToNextTask();
                case 7 -> scheduler.displayTasks();
                case 8 -> {
                    System.out.print("Enter Priority: ");
                    scheduler.searchByPriority(sc.nextInt());
                }
                case 9 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        sc.close();
        }
    }
}
