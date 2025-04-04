import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    private User head = null;

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        newUser.next = head;
        head = newUser;
    }

    public User findUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    public User findUserByName(String name) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void addFriend(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 != null && user2 != null && userId1 != userId2) {
            if (!user1.friendIds.contains(userId2)) user1.friendIds.add(userId2);
            if (!user2.friendIds.contains(userId1)) user2.friendIds.add(userId1);
        }
    }

    public void removeFriend(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 != null && user2 != null) {
            user1.friendIds.remove((Integer) userId2);
            user2.friendIds.remove((Integer) userId1);
        }
    }

    public void displayFriends(int userId) {
        User user = findUserById(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            for (int fid : user.friendIds) {
                User friend = findUserById(fid);
                if (friend != null) {
                    System.out.println("-> " + friend.name + " (ID: " + friend.userId + ")");
                }
            }
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 != null && user2 != null) {
            System.out.println("Mutual friends between " + user1.name + " and " + user2.name + ":");
            for (int id1 : user1.friendIds) {
                if (user2.friendIds.contains(id1)) {
                    User mutual = findUserById(id1);
                    System.out.println("-> " + mutual.name + " (ID: " + mutual.userId + ")");
                }
            }
        }
    }

    public void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }

    public void displayAllUsers() {
        User temp = head;
        while (temp != null) {
            System.out.println("User: " + temp.name + ", ID: " + temp.userId + ", Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaDemo {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();

        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 30);
        sm.addUser(3, "Charlie", 22);
        sm.addUser(4, "Diana", 28);

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);
        sm.addFriend(3, 4);

        sm.displayAllUsers();
        sm.displayFriends(1);
        sm.findMutualFriends(1, 2);
        sm.countFriends();
        sm.removeFriend(1, 2);
        System.out.println("\nAfter removing friendship:");
        sm.displayFriends(1);
    }
}
