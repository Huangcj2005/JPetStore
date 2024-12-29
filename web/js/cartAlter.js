$(function (){
    $('td input')
        .on('keyup',function (){
            var $alteredInput = $(this);
            var alterItemId = $(this).parent().siblings().eq(0).text();
            var quantity = Number($(this).val());
            var ex_total = Number($(this).parent().siblings('.total').attr('total'));

            if(quantity > 0){
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/MyWebShop_Web_exploded/updateCartItem?alterItemId="+alterItemId+"&quantity="+quantity,
                    success    :function (data){
                        var total = Number(data.total);

                        $alteredInput.parent().siblings().eq(5).html('$'+total.toFixed(2)).attr('total',total);

                        var ex_subTotal = Number($alteredInput.parent().parent().siblings('.sub').children().eq(1).attr('subTotal'));
                        var subTotal = ex_subTotal - ex_total + total;

                        $alteredInput.parent().parent().siblings('.sub').children().eq(1).html('Sub Total:$'+subTotal.toFixed(2)).attr('subTotal',subTotal);

                        $alteredInput.parent().parent().siblings('.sub').children().eq(0).text(' ');
                    },
                    error       :function (errorMsg){
                        console.log(errorMsg);
                    }
                })
            }else {
                $alteredInput.parent().parent().siblings('.sub').children().eq(0).text('请输入大于0的数字');
            }
    })
})