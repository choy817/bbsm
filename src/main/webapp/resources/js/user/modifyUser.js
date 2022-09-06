//모든 공백 체크 정규식 
var empJ = /\s/g; 
//아이디 정규식 
var idJ = /^[a-z0-9][a-z0-9_\-]{4,}$/; 
// 비밀번호 정규식 
var pwJ =  /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~!@#$%^&*-]).{8,}$/;
// 이메일 검사 정규식 
var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; 
// 휴대폰 번호 정규식 
var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

function sendIt(){
	const form=document.getElementById("modifyUser")
	//console.log(form);

	if(form.userName.value=="" || form.userName.value==null){
		$("#checkName").html("이름을 입력해 주세요");
		$("#checkName").css("color","#f44336");
		form.userName.focus();
		return false;
	}if(form.userMail.value=="" || form.userMail.value==null){
		$("#checkMail").html("이메일을 입력해 주세요");
		$("#checkMail").css("color","#f44336");
		form.userMail.focus();
		return false;
	}if(form.userPhone.value=="" || form.userPhone.value==null){
		$("#checkPhone").html("휴대폰 번호를 입력해 주세요");
		$("#checkPhone").css("color","#f44336");
		form.userPhone.focus();
		return false;
	}if(form.userPw.value=="" || form.userPw.value==null){
		$("#checkPw").html("비밀번호를 입력해 주세요");
		$("#checkPw").css("color","#f44336");
		form.userPw.focus();
		return false;
	}if(form.userPwCheck.value=="" || form.userPwCheck.value==null){
		$("#checkPwCheck").html("비밀번호를 다시 입력해 주세요");
		$("#checkPwCheck").css("color","#f44336");
		form.userPwCheck.focus();
		return false;
	}if(form.userPw.value!=form.userPwCheck.value){
		$("#checkPwCheck").html("비밀번호가 일치하지 않습니다.");
		$("#checkPwCheck").css("color","#f44336");
		form.userPwCheck.focus();
		return false;
	}

	form.submit();
	
}