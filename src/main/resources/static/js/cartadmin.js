$(document).ready(function() {

    $(".change-product-amount").change(function () {
        dataCartProduct = {};
        dataCartProduct.id = $(this).data("id");
        dataCartProduct.amount = $(this).val();

        console.log(1)
        console.log(dataCartProduct.amount);


        NProgress.start();

        var linkPost = "/api/cart-product/update";

        axios.post(linkPost, dataCartProduct).then(function(res){
            NProgress.done();
            if(res.data.success) {
                location.reload();
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                ).then(function() {
                    location.reload();
                });
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });
    $(".delete-cart-product").on("click",function(){
        var pdInfo = $(this).data("id");

        NProgress.start();
        var linkGet = "/api/cart-product/delete/"+pdInfo;
        axios.get(linkGet).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    })
 var orderData ={};
    $(".check_out_admin").on("click", function () {

       orderData.id = $(".cartid").val();
       console.log(orderData.id);
       orderData.phoneNumber = $(".input-phonenumber").val();
       console.log(orderData.phoneNumber);
       orderData.customerName = $(".input-customername").val();
       console.log(orderData.customerName);
        NProgress.start();
        var linkPost = "/api/order/checkout";
        axios.post(linkPost, orderData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.assign("/admin/order?&sort=4");
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when checkout',
                'error'
            );
        })
    });

});