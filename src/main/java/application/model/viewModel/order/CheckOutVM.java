package application.model.viewModel.order;

import application.model.viewModel.cart.CartProductVM;
import application.model.viewModel.common.CartHeaderVM;
import application.model.viewModel.common.LayoutHeaderVM;
import application.model.viewModel.user.UserVM;

import java.util.List;

public class CheckOutVM {

    private CartHeaderVM cartHeaderVM;
    private UserVM userVM;
    private List<CartProductVM> cartProductVMS;
    private Double totalPrice;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartProductVM> getCartProductVMS() {
        return cartProductVMS;
    }

    public void setCartProductVMS(List<CartProductVM> cartProductVMS) {
        this.cartProductVMS = cartProductVMS;
    }

    public UserVM getUserVM() {
        return userVM;
    }

    public void setUserVM(UserVM userVM) {
        this.userVM = userVM;
    }

    public CartHeaderVM getCartHeaderVM() {
        return cartHeaderVM;
    }

    public void setCartHeaderVM(CartHeaderVM cartHeaderVM) {
        this.cartHeaderVM = cartHeaderVM;
    }
}
