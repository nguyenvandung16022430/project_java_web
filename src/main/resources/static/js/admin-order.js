$(document).ready(function() {



    $(".status-order").on("click", function () {
       var dataOrder = {};
        dataOrder.statusId = $(this).val();
        console.log(dataOrder.statusId);
        var pdInfo = $(this).data("order");
        dataOrder.orderId = pdInfo;
        console.log(pdInfo);
        NProgress.start();
        var linkPost = "/api/order/confirm";
        axios.post(linkPost,dataOrder).then(function(res){
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
                ).then(function () {
                    location.reload();

                });
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