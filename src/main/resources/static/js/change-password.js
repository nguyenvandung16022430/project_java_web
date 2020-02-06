$(document).ready(function() {
    $(".change-password").click(function () {
            var changePassword ={};
            console.log("1");
            changePassword.currentPassword = $(".currentPassword").val();
            console.log(changePassword.currentPassword);
            changePassword.newPassword = $(".newPassword").val();
            changePassword.confirmPassword = $(".confirmPassword").val();
            console.log(changePassword.confirmPassword);
            NProgress.start();
            var linkPost = "/api/user/change-password";
            axios.post(linkPost,changePassword).then(function (res) {
                NProgress.done();
                if(res.data.success){
                    swal(
                        'success',
                        res.data.message,
                        'success'
                    ).then(function () {
                        location.assign("/user/detail")

                    });
                }else {
                    swal(
                        'Fail',
                        res.data.message,
                        'error'
                    );
                }

            },function(err){
                    NProgress.done();
                    swal(
                        'Error',
                        'Fail',
                        'error'
                    );
                });
    });
});