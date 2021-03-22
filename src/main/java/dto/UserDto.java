package dto;

public class UserDto {

    private String id;

    private String name;

    private String username;

    private String mail;

    private int varsta;

    private String password;

    private boolean isAdmin;

    public UserDto(String id, String name, String username, String mail, int varsta, String password, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.varsta= varsta;
        this.password = password;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public UserDto() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
