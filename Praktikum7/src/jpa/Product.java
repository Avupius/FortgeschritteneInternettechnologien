package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String description;

	private BigDecimal price;

	private String name;

	private BigDecimal width;

	private BigDecimal height;

	@ManyToMany
	@JoinTable(
                    name="product_category",
		joinColumns=@JoinColumn(name="product_id"),
		inverseJoinColumns=@JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<>();

	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

    public Set<Category> getCategories() {
        return categories;
    }

	public Set<Category> getCategoryCollection() {
		return this.categories;
	}

    public void setCategoryCollection(Set<Category> categories) {
        this.categories = categories;
    }


    public void addCategory(Category category)
    {
       Set<Category> categories = getCategoryCollection();
       if (!categories.contains(category))
       {
          categories.add(category);
          category.addProduct(this);
       }
    }
}
