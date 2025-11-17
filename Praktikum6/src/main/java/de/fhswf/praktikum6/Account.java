package de.fhswf.praktikum6;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private String account;

    private String salt;
    private String hash_password;

    public Account() {}

    public String getAccount() {
        return account;
    }


    public String getSalt() {
        return salt;
    }

    public String getHashPassword() {
        return hash_password;
    }
}
