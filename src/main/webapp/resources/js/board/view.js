var actionForm=$("#actionForm");
	function deleteBoard(){
		console.log("들어옴.")
		if(!confirm("게시글을 삭제할까요?")){
			return false;
		}else{
			actionForm.submit();
		}
			
	}