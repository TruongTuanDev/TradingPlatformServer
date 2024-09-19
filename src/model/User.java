package model;

public class User {
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private boolean isOnline;
    private String fullname;
    private String role;
    private String sex;
    private int idWallets;
    private int idWatchlist;
    private String address;

    public User() {
    }
    

    public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public User(String username, String password, String email, String phonenumber, boolean isOnline, 
                String fullname, String role, String sex, int idWallets, int idWatchlist, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.isOnline = isOnline;
        this.fullname = fullname;
        this.role = role;
        this.sex = sex;
        this.idWallets = idWallets;
        this.idWatchlist = idWatchlist;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIdWallets() {
        return idWallets;
    }

    public void setIdWallets(int idWallets) {
        this.idWallets = idWallets;
    }

    public int getIdWatchlist() {
        return idWatchlist;
    }

    public void setIdWatchlist(int idWatchlist) {
        this.idWatchlist = idWatchlist;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}