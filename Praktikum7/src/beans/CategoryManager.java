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
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;


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

//
//    public List<Category> getAllCategories() {
//        EntityManager em = catalogManagerFactory.getEntityManagerFactory().createEntityManager();
//        try {
//            return em.createQuery("SELECT c FROM Category c", Category.class)
//                    .getResultList();
//        } finally {
//            em.close();
//        }
//    }

    public void createNew() {
        current = new Category();
        redirect("category.jsf");
    }

    // Kategorie speichern (neu oder geändert)
    public void save() {
        EntityManager em = catalogManagerFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            if (current.getId() == 0) {
                em.persist(current);
            } else {
                em.merge(current);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        redirect("categories.jsf");
    }

    // Kategorie löschen
    public void delete() {
        EntityManager em = catalogManagerFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Category managed = em.find(Category.class, current.getId());
            if (managed != null) {
                em.remove(managed);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        redirect("categories.jsf");
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

//    public Collection getCategories()
//    {
//      EntityManagerFactory factory = catalogManagerFactory.getEntityManagerFactory();
//      EntityManager manager = factory.createEntityManager();
//      try{
//          return manager.createQuery("SELECT c FROM Category c ").getResultList();
//      } finally{
//          manager.close();
//      }
//    }

    public void select(jakarta.faces.event.ActionEvent actionEvent) {
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

        redirect("products.jsf");

    }

    public void selectEdit(jakarta.faces.event.ActionEvent e) {
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

        redirect("category.jsf");
    }
//
//    public String removeProduct(Product p) {
//        EntityManager em = catalogManagerFactory.getEntityManagerFactory().createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Category cat = em.find(Category.class, current.getId());
//            Product  prod = em.find(Product.class, p.getId());
//
//            cat.getProducts().remove(prod);
//            prod.getCategories().remove(cat);
//
//            em.merge(cat);
//            em.merge(prod);
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        redirect("catagory.jsf"); // auf derselben Seite bleiben
//    }

}
