package jpa;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	@Id
	private String username;

	private String password;

    private String hashedPassword;

    private String salt;

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getHashedPassword(){return this.hashedPassword;}
    public String getSalt(){return this.salt;}

}
