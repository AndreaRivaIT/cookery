package it.unimib.cookery.models;

public class User {

    public String username;
    public String name;
    public String surname;
    public String mail;
    public String password;

    @Override
    public String toString() {
        return "user{" +
                "username = '" + username + '\'' +
                '}';
    }

    public User(String username, String name, String surname, String mail, String password) {

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }


}
