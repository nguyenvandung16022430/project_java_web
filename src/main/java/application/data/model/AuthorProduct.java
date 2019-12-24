package application.data.model;

import javax.persistence.*;

@Entity(name = "dbo_product_author")
public class AuthorProduct {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_author_id")
    @Id
    private int id;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "author_id", insertable = false, updatable = false)
    private int authorId;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "prouct_id")
    private Product product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
