/*
 * $RCSFile$
 *
 * Created on 11.06.2007
 * for Project: 
 * by steins
 *
 * (C) 2005-2006 by 
 */
package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import jpa.Product;
@Named("productManager")
@SessionScoped
public class ProductManager implements Serializable
{
    @Inject
    private CatalogManagerFactory catalogManagerFactory;

   private Product current;
   
   public Product getCurrent()
   {
      return current;
   }

   public void select(jakarta.faces.event.ActionEvent actionEvent) {
      FacesContext facesContext = FacesContext.getCurrentInstance();
      Map params = facesContext.getExternalContext().getRequestParameterMap();
      String selectedId = (String) params.get("selectedId");
//      System.out.println(selectedId);

      EntityManagerFactory factory = catalogManagerFactory.getEntityManagerFactory();
      EntityManager manager = factory.createEntityManager();

      EntityTransaction tx = manager.getTransaction();
      tx.begin();
      try {
         current = manager.find(Product.class, selectedId);

         tx.commit();


      } catch (Exception ex) {
         ex.printStackTrace(System.err);
         tx.rollback();

      } finally {
            manager.close();
      }

      try {
        facesContext.getExternalContext().redirect("product.jsf");
     } catch (IOException e) {
        e.printStackTrace();
     }
    FacesContext.getCurrentInstance().responseComplete();

}

}
