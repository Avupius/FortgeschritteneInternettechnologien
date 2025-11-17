package de.fhswf.praktikum6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.commons.codec.digest.Crypt;

public class AccountManager {

    private final EntityManagerFactory emf;

    /**
     * Initialisierung des AccountManagers
     * @param puName
     */
    public AccountManager(String puName) {
        this.emf = Persistence.createEntityManagerFactory(puName);
    }


    /**
     * Passwortprüfung gegen gespeichertes Passwort
     * @param inputUsername
     * @param inputPassword
     * @return true, wenn Passwort korrekt
     */
    public boolean checkPassword(String inputUsername, String inputPassword) {

        try (EntityManager em = emf.createEntityManager()) {
            Account acc = em.find(Account.class, inputUsername);

            if (acc == null) {
                return false;
            }


            String storedHash = acc.getHashPassword();

            // Vergleich
            return storedHash.equals(Crypt.crypt(inputPassword, storedHash));

        }


    }

    public void kill(){
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}

