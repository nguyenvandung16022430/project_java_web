package application.model.viewModel.common;

import java.util.ArrayList;
import java.util.List;

public class LayoutHeaderVM {

   private List<CategoryVM> categoryVMList = new ArrayList<>();

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }
}
