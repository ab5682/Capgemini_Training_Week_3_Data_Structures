import java.util.Scanner;

class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;

    Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    private Student head = null;

    public void addFirst(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addLast(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newStudent;
    }

    public void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos == 0) {
            addFirst(roll, name, age, grade);
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        Student temp = head;
        for (int i = 0; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteByRoll(int roll) {
        if (head == null) return;

        if (head.rollNumber == roll) {
            head = head.next;
            return;
        }

        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Roll Number not found.");
            return;
        }

        temp.next = temp.next.next;
    }

    public void searchByRoll(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.println("Found: " + temp.rollNumber + " " + temp.name + " " + temp.age + " " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    public void updateGrade(int roll, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                temp.grade = newGrade;
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Roll Number not found.");
    }

    public void displayAll() {
        Student temp = head;
        while (temp != null) {
            System.out.println(temp.rollNumber + " " + temp.name + " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();

        while (true) {
            System.out.println("\n1. Add First\n2. Add Last\n3. Add at Position\n4. Delete by Roll\n5. Search\n6. Update Grade\n7. Display All\n8. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.print("Roll Name Age Grade: ");
                    list.addFirst(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                }
                case 2 -> {
                    System.out.print("Roll Name Age Grade: ");
                    list.addLast(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                }
                case 3 -> {
                    System.out.print("Position Roll Name Age Grade: ");
                    list.addAtPosition(sc.nextInt(), sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                }
                case 4 -> {
                    System.out.print("Enter Roll to Delete: ");
                    list.deleteByRoll(sc.nextInt());
                }
                case 5 -> {
                    System.out.print("Enter Roll to Search: ");
                    list.searchByRoll(sc.nextInt());
                }
                case 6 -> {
                    System.out.print("Roll and New Grade: ");
                    list.updateGrade(sc.nextInt(), sc.next());
                }
                case 7 -> list.displayAll();
                case 8 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
            sc.close();
        }
    }
}
