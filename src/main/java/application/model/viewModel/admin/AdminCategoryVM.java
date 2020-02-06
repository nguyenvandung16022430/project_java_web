package application.model.viewModel.admin;
import application.model.viewModel.common.CategoryVM;
import application.model.viewModel.common.LayoutHeaderAdminVM;

import java.util.List;

public class AdminCategoryVM {

    private List<CategoryVM> categoryVMList;
    private String keyWord;
    private LayoutHeaderAdminVM layoutHeaderAdminVM;

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
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
