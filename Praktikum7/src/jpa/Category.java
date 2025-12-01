package jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable
{
   @Id
   private int id;

   private String name;

   private String description;

   @ManyToMany(mappedBy = "categoryCollection")
   private Set<Product> productCollection;

   private static final long serialVersionUID = 1L;

   public Category()
   {
      super();
   }

   public int getId()
   {
      return this.id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return this.description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   /**
    * The method <code>getProductCollection()</code> returns a
    * Collection object that is incompatible with JSF.
    *
    * @return collection of product objects.
    */
   public Collection<Product> getProducts()
   {
      Collection<Product> c = new ArrayList<Product>();
      
      for (Iterator iter = getProductCollection().iterator(); iter.hasNext();)
      {
         Product product = (Product) iter.next();
         c.add(product);
//         System.out.println(product.getName());
      }
      
      return c;
   }

   public Set<Product> getProductCollection()
   {
      return this.productCollection;
   }

   public void setProductCollection(Set<Product> productCollection)
   {
      this.productCollection = productCollection;
   }

   public void addProduct(Product product)
   {
      Set<Product> products = getProductCollection();
      if (!products.contains(product))
      {
         products.add(product);
         product.addCategory(this);
      }
   }

}
