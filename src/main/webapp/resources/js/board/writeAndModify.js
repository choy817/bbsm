var regex=new RegExp("(.*?)\.(exe|sh|zip|alz|dmg)$");
var maxSize=5242880;//5MB

function checkExtension(fileName, fileSize){
    if(fileSize>=maxSize){
        alert("파일 용량 초과입니다.");
        return false;
    }
    if(regex.test(fileName)){
        alert("해당 종류의 파일은 업로드 할 수 없습니다.");
        return false;
    }
    return true;
}

$(document).ready(function(e){
    $("#uploadButton").on("click",function(e){
        var formData= new FormData();
        var inputFile=$("input[name='fileUpload']");
        var files=inputFile[0].files;
        console.log(files);

        for(var i=0; i<files.length;i++){
            if(!checkExtension(files[i].name, files[i].size)){
                return false;
            }
            formData.append("fileUpload",files[i]);
        }


        $.ajax({
            url:"/board/fileUpload",
            processData: false,
            contentType: false,
            data: formData,
            type: "POST",
            dataType: "json",
            success : function(result){
                alert("파일 첨부 완료");
                console.log(result);
            }
        });//end ajax
    });
});

function sendIt(){
    const form=document.getElementById("contactForm");

//    const serchParams=new URLSearchParams(location.search);
//     alert(serchParams.get('cate'));

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