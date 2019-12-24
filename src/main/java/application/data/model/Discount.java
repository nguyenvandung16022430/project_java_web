package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_discount")
public class Discount {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discount_id")
    @Id
    private int id;

    @Column(name = "discount_percent")
    private int discountPercent;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

}
