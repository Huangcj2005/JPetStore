$(function (){
    $('#keyword').on('keyup',function (){
        var keyword = $(this).val();    // 按下键盘后，获取keyword里的值
        if (keyword !== '' && keyword !== null && keyword.length !== 0){
            $.ajax({
                type        :'GET',
                url         :'http://localhost:8080/MyWebShop_Web_exploded/ProductAuto?keyword='+keyword,
                success     :function (data){
                    console.log(data);
                    var productListHTML = '';
                    for (var i = 0;i<data.length;i ++){
                        productListHTML += '<li class=\"productAutoItem\" data-productId="';
                        productListHTML += data[i].productId;
                        productListHTML +='">';
                        productListHTML += data[i].categoryId;
                        productListHTML += ':';
                        productListHTML += data[i].name;
                        productListHTML += '</li>';
                    }
                    $('#productAutoList').html(productListHTML);
                    $('#productAutoComplete').show();
                },
                error       :function (errorMsg){
                    console.log(errorMsg);
                }
            })
        }else{
            $('#productAutoComplete').hide();
        }
    });

    // 将事件绑在动态对象的父节点(静态)--事件冒泡
    $(document).on('click','.productAutoItem',function (){
        var productId = $(this).data('productid');
        console.log(productId);
        $('#productAutoComplete').hide();
        $('#keyword').val('');
        window.location.href = 'http://localhost:8080/MyWebShop_Web_exploded/productForm?productId=' + productId;
    });

    $('#productAutoComplete').on('mouseleave',function (){
        $('this').hide();
        $('#keyword').val('');
    })

});