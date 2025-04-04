import java.util.Scanner;

class Book {
    String title, author, genre;
    int bookID;
    boolean isAvailable;
    Book next, prev;

    Book(String title, String author, String genre, int bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head = null, tail = null;

    public void addFirst(String title, String author, String genre, int bookID, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookID, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addLast(String title, String author, String genre, int bookID, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookID, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addAtPosition(int pos, String title, String author, String genre, int bookID, boolean isAvailable) {
        if (pos <= 0) {
            addFirst(title, author, genre, bookID, isAvailable);
            return;
        }

        Book newBook = new Book(title, author, genre, bookID, isAvailable);
        Book temp = head;

        for (int i = 0; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            addLast(title, author, genre, bookID, isAvailable);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
        }
    }

    public void removeById(int bookID) {
        Book temp = head;

        while (temp != null && temp.bookID != bookID) {
            temp = temp.next;
        }

        if (temp == null) return;

        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (temp == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    public void updateAvailability(int bookID, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookID == bookID) {
                temp.isAvailable = status;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void displayForward() {
        Book temp = head;
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    public int countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    private void printBook(Book b) {
        System.out.println("ID: " + b.bookID + ", Title: " + b.title + ", Author: " + b.author + ", Genre: " + b.genre + ", Available: " + b.isAvailable);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n1.Add First\n2.Add Last\n3.Add at Position\n4.Remove by ID\n5.Search by Title\n6.Search by Author\n7.Update Availability\n8.Display Forward\n9.Display Reverse\n10.Count Books\n11.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.print("Title Author Genre ID Available(true/false): ");
                    library.addFirst(sc.next(), sc.next(), sc.next(), sc.nextInt(), sc.nextBoolean());
                }
                case 2 -> {
                    System.out.print("Title Author Genre ID Available(true/false): ");
                    library.addLast(sc.next(), sc.next(), sc.next(), sc.nextInt(), sc.nextBoolean());
                }
                case 3 -> {
                    System.out.print("Position Title Author Genre ID Available(true/false): ");
                    library.addAtPosition(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextInt(), sc.nextBoolean());
                }
                case 4 -> {
                    System.out.print("Enter Book ID: ");
                    library.removeById(sc.nextInt());
                }
                case 5 -> {
                    System.out.print("Enter Title: ");
                    library.searchByTitle(sc.next());
                }
                case 6 -> {
                    System.out.print("Enter Author: ");
                    library.searchByAuthor(sc.next());
                }
                case 7 -> {
                    System.out.print("Enter Book ID and Availability(true/false): ");
                    library.updateAvailability(sc.nextInt(), sc.nextBoolean());
                }
                case 8 -> library.displayForward();
                case 9 -> library.displayReverse();
                case 10 -> System.out.println("Total Books: " + library.countBooks());
                case 11 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
            sc.close();
        }
    }
}
