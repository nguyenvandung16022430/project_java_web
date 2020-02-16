$(document).ready(function() {

    var dataProduct = {};



    $(".btn-confirm-order").on("click", function () {
        var pdInfo = $(".confirm-order").data("order");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/order/confirm/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            }else {
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
    $(".btn-cancel-order").on("click", function () {
        var pdInfo = $(".cancel-order").data("order");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/order/cancel/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            }else {
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
    $(".btn-delete-order").on("click", function () {
        var pdInfo = $(".delete-order").data("order");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/order/delete/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            }else {
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

});