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
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jpa.Product;
import jpa.Category;


@Named("productManager")
@SessionScoped
public class ProductManager implements Serializable
{
    @Inject
    private CatalogManagerFactory catalogManagerFactory;


   private Product current;
    @Named("categoryManager")
    @Inject
    private CategoryManager categoryManager;

    public Product getCurrent()
   {
      return current;
   }


//    private List<String> selectedCategoryIds;
//
//    public List<String> getSelectedCategoryIds() {
//        return selectedCategoryIds;
//    }
//
//    public void setSelectedCategoryIds(List<String> selectedCategoryIds) {
//        this.selectedCategoryIds = selectedCategoryIds;
//    }

   public  void createNew(){
       current = new Product();

       Category selectedCategory = categoryManager.getCurrent();
       current.getCategories().add(selectedCategory);
       redirect("product.jsf");
   }

    public void save() {
       System.out.println("Saving product");
        EntityManager em = catalogManagerFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            if (current.getId() == null) {
                em.persist(current);
            } else {
                current = em.merge(current);
            }

            // Wichtig: Many-To-Many von beiden Seiten pflegen.
            for (Category c : current.getCategories()) {
                c.getProducts().add(current);
                em.merge(c);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        //addProductToCategories(current.getId(), selectedCategoryIds);
        redirect("products.jsf");

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

        redirect("product.jsf");

    }

    public void delete() {
        EntityManager em = catalogManagerFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Product managed = em.find(Product.class, current.getId());

            for (Category c : managed.getCategories()) {
                c.getProducts().remove(managed);
                em.merge(c);
            }

            em.remove(managed);

            // Liste aktualisieren
            Category updated = em.find(Category.class, categoryManager.getCurrent().getId());
            categoryManager.setCurrent(updated);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        redirect("products.jsf");
    }

    private void redirect(String page) {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fc.responseComplete();
    }

//
//    public void addProductToCategories(String productId, List<String> categoryIds) {
//        EntityManager em = catalogManagerFactory.getEntityManagerFactory().createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Product p = em.find(Product.class, productId);
//
//            for (String catId : categoryIds) {
//                Category c = em.find(Category.class, catId);
//                p.addCategory(c);     // setzt beide Seiten
//            }
//
//            em.merge(p);
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//    }

}



