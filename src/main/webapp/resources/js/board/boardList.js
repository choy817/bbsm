var actionForm=$("#actionForm");
var pageForm=$("#pageForm");
var searchForm=$("#searchForm");

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

$("#searchForm a").on("click",function(e){
    e.preventDefault();

    if(!searchForm.find("option:selected").val()){
        alert("검색 기준을 선택하세요");
        return false;
    }
    if(!searchForm.find("input[name='keyword']").val()){
        alert("검색어를 입력하세요");
        return false;
    }
    //pageNum의 번호가 1이 되도록 함
    searchForm.find("input[name='pageNum']").val("1");
    searchForm.submit();
});
