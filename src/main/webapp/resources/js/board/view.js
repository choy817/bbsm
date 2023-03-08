var actionForm=$("#actionForm");

	function deleteBoard(){
		console.log("들어옴.")
		if(!confirm("게시글을 삭제할까요?")){
			return false;
		}else{
			actionForm.submit();
		}
			
	}
// $(document).ready(function(){
// 	$(".recoBtn").on("click",function () {
// 		$.ajax({
// 			type:"POST",
// 			url :"/board/recoCnt",
// 			dataType:"json",
// 			data: boardNo,
// 			success:function(data){
// 				if(data==0){
// 					console.log("추천 실패");
// 				}else{
// 					console.log("추천 완료");
// 					location.reload();
// 				}
// 			}
		
// 		});
// 	  });
// 	});

	