function sendIt(){
    const form=document.getElementById("login");
    console.log(form);
    if(form.userId.value=="" || form.userId.value==null){
		$("#checkId").html("아이디를 입력해 주세요");
		$("#checkId").css("color","#f44336");
		form.userId.focus();
		return false;
	}if(form.userPw.value=="" || form.userPw.value==null){
		$("#checkPw").html("비밀번호를 입력해 주세요");
		$("#checkPw").css("color","#f44336");
		form.userPw.focus();
		return false;
	}

    form.submit();
}

function naverLogin(){
    $.ajax({
        url: '/user/naverLogin',
        type: 'post',
    }).done(function (res) {
        location.href = res;
    });
}