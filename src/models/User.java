package models;

public class User {
    private int id;
    private String username;
    private String password;

    private static int currentUserId = 0; // 0 = no user logged in

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public static void setCurrentUser(int id) {
        currentUserId = id;
    }

    public static int getCurrentUser() {
        return currentUserId;
    }

    public static boolean isLoggedIn() {
        return currentUserId != 0;
    }
}
