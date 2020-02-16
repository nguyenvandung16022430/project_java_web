package application.model.viewModel.order;

import application.model.viewModel.common.CartHeaderVM;
import application.model.viewModel.common.LayoutHeaderVM;

import java.util.List;

public class OderDetailVM {


    private List<OrderProductVM> orderProductVMS;
    private String totalPrice;
    private int totalProduct;
    private int statusId;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    private CartHeaderVM cartHeaderVM;

    public CartHeaderVM getCartHeaderVM() {
        return cartHeaderVM;
    }

    public void setCartHeaderVM(CartHeaderVM cartHeaderVM) {
        this.cartHeaderVM = cartHeaderVM;
    }

    public List<OrderProductVM> getOrderProductVMS() {
        return orderProductVMS;
    }

    public void setOrderProductVMS(List<OrderProductVM> orderProductVMS) {
        this.orderProductVMS = orderProductVMS;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
}
