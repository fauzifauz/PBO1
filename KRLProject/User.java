public abstract class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        if (username == null || username.trim().isEmpty()) throw new IllegalArgumentException("Username tidak boleh kosong");
        if (password == null) throw new IllegalArgumentException("Password tidak boleh null");
        this.username = username.trim();
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    protected boolean checkPassword(String p) { return password.equals(p); }
    public boolean checkCredentials(String u, String p) {
        return username.equals(u) && checkPassword(p);
    }
    public String getRole() { return role; }

    public void setPassword(String newPass) {
        if (newPass == null || newPass.length() < 4) throw new IllegalArgumentException("Password minimal 4 karakter");
        this.password = newPass;
    }

    public abstract void showMenu(java.util.Scanner sc, DataStore store);
}
