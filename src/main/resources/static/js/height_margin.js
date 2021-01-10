$(function() {
    var height=$("#header").height();
    $("body").css("margin-top", height + 10);//10pxだけ余裕をもたせる
});

$(function() {
    var height=$("#footer").height();
    $("body").css("margin-bottom", height + 10);//10pxだけ余裕をもたせる
});