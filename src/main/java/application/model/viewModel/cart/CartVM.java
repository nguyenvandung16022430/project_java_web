package application.model.viewModel.cart;

import application.model.viewModel.common.CartHeaderVM;
import application.model.viewModel.common.LayoutHeaderVM;

import java.util.List;

public class CartVM {

    private int productAmount;
    private List<CartProductVM> cartProductVMS;
    private CartHeaderVM cartHeaderVM;
    private String totalPrice;
    private int cartId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public List<CartProductVM> getCartProductVMS() {
        return cartProductVMS;
    }

    public void setCartProductVMS(List<CartProductVM> cartProductVMS) {
        this.cartProductVMS = cartProductVMS;
    }

    public CartHeaderVM getCartHeaderVM() {
        return cartHeaderVM;
    }

    public void setCartHeaderVM(CartHeaderVM cartHeaderVM) {
        this.cartHeaderVM = cartHeaderVM;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
