package application.model.viewModel.productdetail;

import application.model.viewModel.common.LayoutHeaderVM;
import application.model.viewModel.common.ProductVM;

import java.util.List;

public class ProductDetailVM {

    private ProductVM productVM;

    private LayoutHeaderVM layoutHeaderVM;
    private List<ProductVM> productVMList;

    public List<ProductVM> getProductVMList() {
        return productVMList;
    }

    public void setProductVMList(List<ProductVM> productVMList) {
        this.productVMList = productVMList;
    }

    public ProductVM getProductVM() {
        return productVM;
    }

    public void setProductVM(ProductVM productVM) {
        this.productVM = productVM;
    }

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }
}
