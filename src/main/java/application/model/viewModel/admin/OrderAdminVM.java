package application.model.viewModel.admin;

import application.model.viewModel.common.LayoutHeaderAdminVM;
import application.model.viewModel.order.OrderVM;

import java.util.ArrayList;
import java.util.List;

public class OrderAdminVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private List<OrderVM> orderVMList = new ArrayList<>();

    public List<OrderVM> getOrderVMList() {
        return orderVMList;
    }

    public void setOrderVMList(List<OrderVM> orderVMList) {
        this.orderVMList = orderVMList;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }
}
