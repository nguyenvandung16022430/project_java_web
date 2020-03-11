$(document).ready(function() {
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    var delete_cookie = function(name) {
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    };

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for(var i=0;i < ca.length;i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1,c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
        }
        return null;
    }

    function checkCookie() {
        var user = getCookie("username");
        if (user != "") {
            alert("Welcome again " + user);
        } else {
            user = prompt("Please enter your name:", "");
            if (user != "" && user != null) {
                setCookie("username", user, 365);
            }
        }
    }

    $(".add-to-cart").on("click", function () {
    var dataCart = {};
    var pdInfo = $(this).data("product");
    dataCart.amount = 1;
    dataCart.productId = pdInfo;
    dataCart.guid = getCookie("guid");

    NProgress.start();

    var linkPost = "/api/cart-product/add";

    axios.post(linkPost, dataCart).then(function(res){
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
});
    $('.hot-product').slick({
        dots: true,
        speed: 300,
        autoplay: true,
        infinite : true,
        autoplaySpeed: 1500,
        slidesToShow: 4,
        slidesToScroll: 2,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
            // You can unslick at a given breakpoint now by adding:
            // settings: "unslick"
            // instead of a settings object
        ]
    });
    $('.multiple-items').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 3
    });

});
