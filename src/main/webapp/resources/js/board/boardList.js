var actionForm=$("#actionForm");
var pageForm=$("#pageForm");

$(".page-link").on("click",function(e){
    e.preventDefault();
    actionForm.find("input[name='pageNum']").val($(this).attr("href"));
    actionForm.submit();
});

$(".move").on("click",function(e){
    e.preventDefault();
    pageForm.append("<input type='hidden' name='boardNo' value='"+$(this).attr("href")+"'>");
    pageForm.attr("action","/board/view");
    pageForm.submit();
});
