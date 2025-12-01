/*
 * $RCSFile$
 *
 * Created on 06.12.2006
 * for Project: 
 * by steins
 *
 * (C) 2005-2006 by 
 */
package beans;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import jpa.User;
import static util.DigestUtils.md5;

@Named("userManager")
@SessionScoped
public class UserManager implements Serializable {

    @Inject
    private CatalogManagerFactory catalogManagerFactory;

    private User current;

	private boolean loggedIn;

	public UserManager() {
		current = new User();
	}

	public String login() {
		String outcome = "failure";
		if (current.getUsername() != null && current.getUsername().length() > 0
				&& current.getPassword() != null
				&& current.getPassword().length() > 0) {
			EntityManagerFactory factory = catalogManagerFactory.getEntityManagerFactory();
			EntityManager manager = factory.createEntityManager();
			try {

                Query query = manager
                        .createQuery("SELECT u FROM User u where u.username = :username and u.password = :password");
                query.setParameter("username", current.getUsername());
                query.setParameter("password", (current.getPassword()));
                List results = query.getResultList();

                if (!results.isEmpty()) {
                    loggedIn = true;
                    current = (User) results.get(0);
                    outcome = "success";
                }
            } finally {
                manager.close();
            }

		}
		System.out.println(outcome);
        System.out.println(loggedIn);
		return outcome;
	}

	public String logout() {
		loggedIn = false;
		current = new User();
		return "home.jsf";
	}

	public void setUsername(String username) {
		current.setUsername(username);
	}

	public String getUsername() {
		return current.getUsername();
	}

	public void setPassword(String password) {
		current.setPassword(password);
	}

	public String getPassword() {
		return current.getPassword();
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public User getCurrent() {
		EntityManagerFactory factory = catalogManagerFactory.getEntityManagerFactory();
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			current = manager.find(User.class, getUsername());

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			tx.rollback();
		} finally {
            manager.close();
        }

		return current;
	}
}
