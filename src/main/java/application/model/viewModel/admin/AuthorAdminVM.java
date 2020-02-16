package application.model.viewModel.admin;

import application.model.viewModel.common.AuthorVM;
import application.model.viewModel.common.LayoutHeaderAdminVM;

import java.util.ArrayList;
import java.util.List;

public class AuthorAdminVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private List<AuthorVM> authorVMList = new ArrayList<>();
    private String keyWord;

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

    public List<AuthorVM> getAuthorVMList() {
        return authorVMList;
    }

    public void setAuthorVMList(List<AuthorVM> authorVMList) {
        this.authorVMList = authorVMList;
    }
}
