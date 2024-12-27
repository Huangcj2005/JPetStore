$(function (){
    var ex_quantity;
    $('td input')
        .on('focus', function(){
            ex_quantity = $(this).val();
        })
        .on('keyup',function (){
        var alterItemId = $(this).parent().siblings().eq(0).text();
        var quantity = $(this).val();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/MyWebShop_Web_exploded/updateCartItem?alterItemId="+alterItemId+"&quantity="+quantity,
            success    :function (data){
                $(this).parent().siblings().eq(6).html("<td>$"+ data.item.listPrice + "</td>")
            }
        })
    })
})