function sendIt(){
    const form=document.getElementById("contactForm");
    console.log(form);
    
    if(form.storeName.value=="" || form.storeName.value==null){
		$("#checkStoreName").html("가게명을 입력해 주세요");
		$("#checkStoreName").css("color","#f44336");
		form.storeName.focus();
		return false;
	}
    if(form.latitude.value=="" || form.latitude.value==null){
		$("#checkLatitude").html("위도를 입력해 주세요");
		$("#checkLatitude").css("color","#f44336");
		form.latitude.focus();
		return false;
	}
	if(form.longitude.value=="" || form.longitude.value==null){
		$("#checkLongitude").html("경도를 입력해 주세요");
		$("#checkLongitude").css("color","#f44336");
		form.longitude.focus();
		return false;
	}

    form.submit();
}