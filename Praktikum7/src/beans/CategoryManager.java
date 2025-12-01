/*
 * $RCSFile$
 *
 * Created on 08.03.2007
 * for Project: 
 * by steins
 *
 * (C) 2005-2006 by 
 */
package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import jpa.Category;

@Named("categoryManager")
@SessionScoped
public class CategoryManager implements Serializable
{
    @Inject
    private CatalogManagerFactory catalogManagerFactory;


    private Category current;

    public Category getCurrent()
    {
      return current;
    }

    public void setCurrent(Category current)
    {
      this.current = current;
    }

    public Collection getCategories()
    {
      EntityManagerFactory factory = catalogManagerFactory.getEntityManagerFactory();
      EntityManager manager = factory.createEntityManager();
      try{
          return manager.createQuery("SELECT c FROM Category c ").getResultList();
      } finally{
          manager.close();
      }
    }

    public void select(jakarta.faces.event.ActionEvent actionEvent)
    {
      FacesContext facesContext = FacesContext.getCurrentInstance();
      Map params = facesContext.getExternalContext().getRequestParameterMap();
      Integer selectedId = Integer.valueOf((String) params.get("selectedId"));
      System.out.println(selectedId);

      EntityManagerFactory factory = catalogManagerFactory.getEntityManagerFactory();
      EntityManager manager = factory.createEntityManager();

      EntityTransaction tx = manager.getTransaction();
      tx.begin();
      try {
         current = manager.find(Category.class, selectedId);
         System.out.println(current.getName());
         tx.commit();


      } catch (Exception ex) {
         ex.printStackTrace(System.err);
         tx.rollback();

      }finally {
            manager.close();
      }
      try {
          facesContext.getExternalContext().redirect("products.jsf");
      } catch (IOException e) {
          e.printStackTrace();
      }
      FacesContext.getCurrentInstance().responseComplete();

    }
}
