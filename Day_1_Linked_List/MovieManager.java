import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie prev, next;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }
}

class MovieList {
    private Movie head = null;
    private Movie tail = null;

    public void addFirst(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addLast(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos == 0) {
            addFirst(title, director, year, rating);
            return;
        }
        Movie temp = head;
        for (int i = 0; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addLast(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        newMovie.next = temp.next;
        newMovie.prev = temp;
        temp.next.prev = newMovie;
        temp.next = newMovie;
    }

    public void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Movie not found.");
            return;
        }
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
        System.out.println("Movie removed.");
    }

    public void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found for this director.");
    }

    public void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with this rating.");
    }

    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }

    public void displayForward() {
        Movie temp = head;
        while (temp != null) {
            printMovie(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            printMovie(temp);
            temp = temp.prev;
        }
    }

    private void printMovie(Movie m) {
        System.out.println(m.title + " | " + m.director + " | " + m.year + " | Rating: " + m.rating);
    }
}

public class MovieManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieList list = new MovieList();

        while (true) {
            System.out.println("\n1. Add First\n2. Add Last\n3. Add at Position\n4. Remove by Title\n5. Search by Director\n6. Search by Rating\n7. Update Rating\n8. Display Forward\n9. Display Reverse\n10. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Title Director Year Rating: ");
                    list.addFirst(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                }
                case 2 -> {
                    System.out.print("Title Director Year Rating: ");
                    list.addLast(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                }
                case 3 -> {
                    System.out.print("Position Title Director Year Rating: ");
                    int pos = sc.nextInt(); sc.nextLine();
                    list.addAtPosition(pos, sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                }
                case 4 -> {
                    System.out.print("Enter title to remove: ");
                    list.removeByTitle(sc.nextLine());
                }
                case 5 -> {
                    System.out.print("Enter director: ");
                    list.searchByDirector(sc.nextLine());
                }
                case 6 -> {
                    System.out.print("Enter rating: ");
                    list.searchByRating(sc.nextDouble());
                    sc.nextLine();
                }
                case 7 -> {
                    System.out.print("Title and new rating: ");
                    list.updateRating(sc.nextLine(), sc.nextDouble());
                    sc.nextLine();
                }
                case 8 -> list.displayForward();
                case 9 -> list.displayReverse();
                case 10 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
            sc.close();
        }
    }
}
