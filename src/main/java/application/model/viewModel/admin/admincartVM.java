package application.model.viewModel.admin;

import application.model.viewModel.common.LayoutHeaderAdminVM;
import application.model.viewModel.common.ProductVM;

import java.util.List;

public class AdminCartVM {
    private List<ProductVM> productVMList;
    private String keyWord;
    private LayoutHeaderAdminVM layoutHeaderAdminVM;

    public List<ProductVM> getProductVMList() {
        return productVMList;
    }

    public void setProductVMList(List<ProductVM> productVMList) {
        this.productVMList = productVMList;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }
}
