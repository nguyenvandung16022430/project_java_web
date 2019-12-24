package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_publisher")
public class Publisher {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publisher_id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publisher")
    private List<Product> listProduct = new ArrayList<>();

    @Column(name = "publisher_name")
    private String publisherName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
