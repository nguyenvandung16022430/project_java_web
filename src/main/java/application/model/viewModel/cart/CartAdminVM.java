package application.model.viewModel.cart;

import application.model.viewModel.common.CartHeaderVM;
import application.model.viewModel.common.LayoutHeaderAdminVM;

import java.util.List;

public class CartAdminVM {
    private int productAmount;
    private List<CartProductVM> cartProductVMS;
    private LayoutHeaderAdminVM layoutHeaderAdminVM;

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

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    private String totalPrice;
    private int cartId;
}
