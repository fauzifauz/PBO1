public abstract class User {
    protected String username; // Username user
    protected String password; // Password user
    protected String role;     // Role user (Admin/Passenger)

    // Constructor untuk inisialisasi user
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Validasi login
    public boolean checkCredentials(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public String getUsername() { return username; } // Getter username
    public String getRole() { return role; }         // Getter role

    // Method abstrak, wajib diimplementasikan child class
    public abstract void showMenu();
}
