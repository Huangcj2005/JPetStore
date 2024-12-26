$(function (){
    $('.mainImage').mouseover(function (e){
        var mousePos = mousePosition(e);
        var xOffset = 20;
        var yOffset = 25;
        var imageName = e.target.alt;
        // console.log(imageName);

        // 依据 imageName查询内容后展示
        $.ajax({
            type:       'GET',
            url:        'http://localhost:8080/MyWebShop_Web_exploded/MainImageHover?categoryId='+imageName,
            success:     function (data){
                console.log(data);
                var hoverProductListHTML = '';
                for (var i = 0;i<data.length;i ++){
                    hoverProductListHTML += '<li class=\"hoverItem\" data-productId="';
                    hoverProductListHTML += data[i].productId;
                    hoverProductListHTML +='">';
                    hoverProductListHTML += data[i].categoryId;
                    hoverProductListHTML += ':';
                    hoverProductListHTML += data[i].name;
                    hoverProductListHTML += '</li>';
                }
                $('#hoverProductList').html(hoverProductListHTML);
                if (mousePos.x > 1000){
                    $("#hover").css("display","block").css("position","absolute")
                        .css("top",(mousePos.y - yOffset) + "px").css("left",(mousePos.x + xOffset) + "px");
                }else{
                    $("#hover").css("display","block").css("position","absolute")
                        .css("top",(mousePos.y - yOffset) + "px").css("left",(mousePos.x + xOffset - 189) + "px");
                }

            },
            error:       function (errorMsg){
                console.log(errorMsg);
            }
        })
    });

    // 鼠标离开 隐藏悬浮窗
    $('.mainImage').mouseout(function (){
        $('#hoverProductList').html();
        $('#hover').css("display","none");
    })
});
function mousePosition(ev){
    ev = ev || window.event;
    if(ev.pageX || ev.pageY){
        return {x:ev.pageX, y:ev.pageY};
    }
    return {
        x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
        y:ev.clientY + document.body.scrollTop - document.body.clientTop
    };
}
