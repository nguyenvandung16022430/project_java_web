package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name ="dbo_cart")
public class Cart {
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "cart_id")
   @Id
   private int id;
   @Column(name = "guid")
   private String guid;
    @Column(name = "user_name")
   private String username;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
   private List<CartProduct> listCartProduct = new ArrayList<>();

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getGuid() {
      return guid;
   }

   public void setGuid(String guid) {
      this.guid = guid;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public List<CartProduct> getListCartProduct() {
      return listCartProduct;
   }

   public void setListCartProduct(List<CartProduct> listCartProduct) {
      this.listCartProduct = listCartProduct;
   }
}
