$(document).ready(function () {
    $("#printf").on("click",function () {
        $('.imageProduct').css({
            display : 'none',
        })
       document.getElementById('price').colSpan = "6";
        document.getElementById('status').colSpan ="6";

        var divToPrint=document.getElementById("printTable");
        document.write(divToPrint.outerHTML);
        window.print();
        location.reload();
    })

})