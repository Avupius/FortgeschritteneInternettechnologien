package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPATest
{
   /**
    * @param args
    */
   public static void main(String[] args)
   {
      EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("catalog");
      EntityManager manager = factory.createEntityManager();
      EntityTransaction tx = manager.getTransaction();
      tx.begin();
      try
      {
//         Product product = manager.find(Product.class, "7");
//         Category newCategory = manager.find(Category.class, 4);
//         product.addCategory(newCategory);
//         manager.persist(product);
//         Collection<Category> categories = product.getCategoryCollection();
//         for (Category category : categories)
//         {
//            System.out.println(category.getName());
//         }
         Product product = new Product();
         product.setDescription("Noch ein Testprodukt");
         product.setName("Produkt");
         manager.persist(product);
//         User user = new User();
//         user.setUsername("bestertester2");
//         System.out.println(DigestUtils.md5("masterkey"));
//         user.setPassword(DigestUtils.md5("masterkey"));
//         manager.persist(user);
         
         tx.commit();
      }
      catch (Exception ex)
      {
         ex.printStackTrace(System.err);
         tx.rollback();
      }
      
//      CategoryManager cm = new CategoryManager();
//      Collection<Category> categories = cm.getCategories();
//      for (Category category : categories)
//      {
//         System.out.println(category.getName());
//         Set<Product> products = category.getProductCollection();
//         for (Product product : products)
//         {
//            System.out.println("\t" + product.getName());
//         }
//
//      }
   }
}
