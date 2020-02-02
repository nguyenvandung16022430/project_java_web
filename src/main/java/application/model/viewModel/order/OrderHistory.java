package application.model.viewModel.order;

import application.model.viewModel.common.CartHeaderVM;
import application.model.viewModel.common.LayoutHeaderVM;

import java.util.List;

public class OrderHistory {


    private List<OrderVM> orderVMS;
    private CartHeaderVM cartHeaderVM;

    public CartHeaderVM getCartHeaderVM() {
        return cartHeaderVM;
    }

    public void setCartHeaderVM(CartHeaderVM cartHeaderVM) {
        this.cartHeaderVM = cartHeaderVM;
    }

    public List<OrderVM> getOrderVMS() {
        return orderVMS;
    }

    public void setOrderVMS(List<OrderVM> orderVMS) {
        this.orderVMS = orderVMS;
    }
}
