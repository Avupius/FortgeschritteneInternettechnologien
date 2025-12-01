package beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Singleton-Verwaltung der EntityManagerFactory.
 * Wird einmal beim Start der Anwendung initialisiert (@ApplicationScoped).
 */
@ApplicationScoped
public class CatalogManagerFactory {

    // Verwenden Sie den korrekten Namen, um Verwirrung zu vermeiden
    private EntityManagerFactory factory;

    /**
     * @PostConstruct: Diese Methode wird einmal ausgeführt,
     * nachdem die Bean erstellt wurde (beim Start der Anwendung).
     */
    @PostConstruct
    public void init() {
        System.out.println("CatalogManagerFactory wird initialisiert...");
        // Der Persistence-Unit-Name aus der persistence.xml
        factory = Persistence.createEntityManagerFactory("catalog");
        System.out.println("EntityManagerFactory 'Catalog' erfolgreich erstellt.");
    }

    /**
     * @PreDestroy: Diese Methode wird einmal ausgeführt,
     * bevor die Bean zerstört wird (beim Herunterfahren der Anwendung).
     */
    @PreDestroy
    public void destroy() {
        if (factory != null && factory.isOpen()) {
            System.out.println("CatalogManagerFactory wird geschlossen...");
            factory.close();
            System.out.println("EntityManagerFactory 'Catalog' geschlossen.");
        }
    }

    /**
     * Getter-Methode, um die Singleton-Instanz der Factory bereitzustellen.
     */
    public EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }
}