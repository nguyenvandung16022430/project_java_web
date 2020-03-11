package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "dbo_product")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    @Id
    private int id;

    @Column(name = "publisher_id", insertable = false, updatable = false)
    private Integer publisherId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "discount_id", insertable = false, updatable = false)
    private Integer discountId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> ProductImagelist = new ArrayList<>();

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<ProductCategory> productCategories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<AuthorProduct> authorProducts = new ArrayList<>();
    @Column(name = "name")
    private String name;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private double price;

    @Column(name = "published_date")
    private Date publishedDate;
    @Column(name = "hot")
    private Integer hot;

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public List<AuthorProduct> getAuthorProducts() {
        return authorProducts;
    }

    public void setAuthorProducts(List<AuthorProduct> authorProducts) {
        this.authorProducts = authorProducts;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<ProductImage> getProductImagelist() {
        return ProductImagelist;
    }

    public void setProductImagelist(List<ProductImage> productImagelist) {
        ProductImagelist = productImagelist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
