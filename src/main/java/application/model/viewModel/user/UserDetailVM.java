package application.model.viewModel.user;

import application.model.viewModel.common.CartHeaderVM;
import application.model.viewModel.common.LayoutHeaderVM;

public class UserDetailVM {

   private CartHeaderVM cartHeaderVM;
    private UserVM userVM;

    public UserVM getUserVM() {
        return userVM;
    }

    public void setUserVM(UserVM userVM) {
        this.userVM = userVM;
    }

    public CartHeaderVM getCartHeaderVM() {
        return cartHeaderVM;
    }

    public void setCartHeaderVM(CartHeaderVM cartHeaderVM) {
        this.cartHeaderVM = cartHeaderVM;
    }
}
