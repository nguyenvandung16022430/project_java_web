package application.model.viewModel.common;

import java.util.Date;
import java.util.List;

public class ProductVM {

    private int id;
    private String name;
    private String publisherName;
    private String mainImage;
    private String shortDesc;
    private double price;
    private int amount;
    private Date createdDate;
    private List<ProductImageVM> productImageVMS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ProductImageVM> getProductImageVMS() {
        return productImageVMS;
    }

    public void setProductImageVMS(List<ProductImageVM> productImageVMS) {
        this.productImageVMS = productImageVMS;
    }
}
