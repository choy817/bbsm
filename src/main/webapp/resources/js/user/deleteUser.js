function sendIt(){
    const form=document.getElementById("deleteUser");
    
    if(form.userPw.value=="" || form.userPw.value==null){
		$("#checkPw").html("비밀번호를 입력해 주세요");
		$("#checkPw").css("color","#f44336");
		form.userPw.focus();
		return false;
	}

    form.submit();
}