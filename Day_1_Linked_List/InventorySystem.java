import java.util.Scanner;

class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;

    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head = null;

    public void addFirst(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addLast(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newItem;
    }

    public void addAtPosition(int pos, String name, int id, int quantity, double price) {
        if (pos == 0) {
            addFirst(name, id, quantity, price);
            return;
        }
        Item newItem = new Item(name, id, quantity, price);
        Item temp = head;
        for (int i = 0; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) return;
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public void updateQuantity(int id, int quantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = quantity;
                return;
            }
            temp = temp.next;
        }
    }

    public void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                printItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                printItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found.");
    }

    public void displayInventory() {
        Item temp = head;
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }

    public void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + total);
    }

    public void sortInventory(String criteria, boolean ascending) {
        head = mergeSort(head, criteria, ascending);
    }

    private Item mergeSort(Item head, String criteria, boolean ascending) {
        if (head == null || head.next == null)
            return head;

        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, criteria, ascending);
        Item right = mergeSort(nextOfMiddle, criteria, ascending);

        return sortedMerge(left, right, criteria, ascending);
    }

    private Item sortedMerge(Item a, Item b, String criteria, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        int compare;
        if (criteria.equals("name")) {
            compare = a.name.compareToIgnoreCase(b.name);
        } else {
            compare = Double.compare(a.price, b.price);
        }

        if ((ascending && compare <= 0) || (!ascending && compare > 0)) {
            a.next = sortedMerge(a.next, b, criteria, ascending);
            return a;
        } else {
            b.next = sortedMerge(a, b.next, criteria, ascending);
            return b;
        }
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void printItem(Item i) {
        System.out.println("ID: " + i.id + ", Name: " + i.name + ", Qty: " + i.quantity + ", Price: " + i.price);
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\n1.Add First\n2.Add Last\n3.Add at Position\n4.Remove by ID\n5.Update Quantity\n6.Search by ID\n7.Search by Name\n8.Display All\n9.Total Value\n10.Sort by Name\n11.Sort by Price\n12.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.print("Name ID Quantity Price: ");
                    inventory.addFirst(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                }
                case 2 -> {
                    System.out.print("Name ID Quantity Price: ");
                    inventory.addLast(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                }
                case 3 -> {
                    System.out.print("Position Name ID Quantity Price: ");
                    inventory.addAtPosition(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                }
                case 4 -> {
                    System.out.print("Enter ID to remove: ");
                    inventory.removeById(sc.nextInt());
                }
                case 5 -> {
                    System.out.print("Enter ID and new Quantity: ");
                    inventory.updateQuantity(sc.nextInt(), sc.nextInt());
                }
                case 6 -> {
                    System.out.print("Enter ID to search: ");
                    inventory.searchById(sc.nextInt());
                }
                case 7 -> {
                    System.out.print("Enter Name to search: ");
                    inventory.searchByName(sc.next());
                }
                case 8 -> inventory.displayInventory();
                case 9 -> inventory.calculateTotalValue();
                case 10 -> {
                    System.out.print("Sort Ascending? (true/false): ");
                    inventory.sortInventory("name", sc.nextBoolean());
                }
                case 11 -> {
                    System.out.print("Sort Ascending? (true/false): ");
                    inventory.sortInventory("price", sc.nextBoolean());
                }
                case 12 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
            sc.close();
        }
    }
}
