package application.model.viewModel.home;

import application.model.viewModel.common.CategoryVM;
import application.model.viewModel.common.LayoutHeaderVM;
import application.model.viewModel.common.ProductVM;

import java.util.List;

public class HomeLandingVM {


    private LayoutHeaderVM layoutHeaderVM;
    private List<ProductVM> productVMList;
    private List<ProductVM> productHotVMList;

    public List<ProductVM> getProductHotVMList() {
        return productHotVMList;
    }

    public void setProductHotVMList(List<ProductVM> productHotVMList) {
        this.productHotVMList = productHotVMList;
    }

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
