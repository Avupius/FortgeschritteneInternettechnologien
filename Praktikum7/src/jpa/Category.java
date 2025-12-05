package jpa;

import java.io.Serializable;
import java.util.*;

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



    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

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

   public void addProduct(Product product)
   {
      Set<Product> products = getProducts();
      if (!products.contains(product))
      {
         products.add(product);
         product.addCategory(this);
      }
   }

}
