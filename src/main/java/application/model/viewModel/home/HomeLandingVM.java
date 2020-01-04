package application.model.viewModel.home;

import application.model.viewModel.common.CategoryVM;
import application.model.viewModel.common.LayoutHeaderVM;
import application.model.viewModel.common.ProductVM;

import java.util.List;

public class HomeLandingVM {


    private LayoutHeaderVM layoutHeaderVM;
    private List<ProductVM> productVMList;


    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public List<ProductVM> getProductVMList() {
        return productVMList;
    }

    public void setProductVMList(List<ProductVM> productVMList) {
        this.productVMList = productVMList;
    }
}
