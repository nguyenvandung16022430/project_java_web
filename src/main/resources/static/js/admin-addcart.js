$(document).ready(function() {


    var dataProduct = {};

    $(".edit-product").on("click", function () {
        var pdInfo = $(this).data("product");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/product/detail/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataProduct.productId = res.data.data.id;
                $("#input-product-name").val(res.data.data.name);
                $("#input-product-desc").val(res.data.data.shortDesc);
                $("#input-product-category").val(res.data.data.categoryId);
                let i = $("#input-product-category").val();
                console.log(i);
                $("#input-product-price").val(res.data.data.price);
                if(res.data.data.mainImage != null) {
                    $('.product-main-image').attr('src', res.data.data.mainImage);
                }
            }else {
                console.log("ahihi");
            }
        }, function(err){
            NProgress.done();
        })
    });



    $(".btn-save-product").on("click", function () {
        if($("#input-product-Amount").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        dataProduct.amount = $('#input-product-Amount').val();
        NProgress.start();
        var linkPost = "/api/admin/addcart";
        axios.post(linkPost, dataProduct).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
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
                'Some error when saving product',
                'error'
            );
        })
    });
    $(".viewcart").on("click",function () {
        location.assign("/admin/viewcart")

    })
});