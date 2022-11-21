function sendIt(){
    const form=document.getElementById("contactForm");

    console.log(form);
    
    if(form.boardTitle.value=="" || form.boardTitle.value==null){
		$("#checkTitle").html("제목을 입력해 주세요");
		$("#checkTitle").css("color","#f44336");
		form.boardTitle.focus();
		return false;
	}
    // if(form.ckeditor.value=="" || form.ckeditor.value==null){
    //     $("#checkContent").html("내용을 입력해 주세요");
	// 	$("#checkContent").css("color","#f44336");
	// 	form.ckeditor.focus();
	// 	return false;
    // }

    form.submit();
}