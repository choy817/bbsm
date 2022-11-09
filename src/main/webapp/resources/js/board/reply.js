console.log("=========reply module============");

var replyService=(function(){
    function add(reply,callback,error){
        console.log("add reply...........");
        
        $.ajax({
            type: "post",
            url: "/replies/create",
            data: JSON.stringify(reply),
            contentType:"application/json; charset=utf-8",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            }
        });
    }

    function getList(param, callback, error){
        var boardNo=param.boardNo;
        var page=param.page || 1;

        $.getJSON("/replies/pages/"+boardNo+"/"+page+".json",function(data){
            if(callback){
                callback(data);
            }
        }).fail(function(xhr,status,err){
            if(error){
                error(err);
            }
        });
    }

    function remove(replyNo, callback, error) {
        $.ajax({
            type : "delete",
            url : "/replies/"+replyNo,
            success : function(deleteResult, status, xhr){
                if(callback){
                    callback(deleteResult);
                }
            }
        });
      }

      function update(reply, callback,error){
        $.ajax({
            type:"put",
            url : "/replies/"+reply.replyNo,
            data : JSON.stringify(reply),
            contentType:"application/json; charset=utf-8",
            success : function(result,status,xhr){
                if(callback){
                    callback(result);
                }
            }
        });
      }

      function get(replyNo, callback, error){
        $.get("/replies/"+replyNo+".json",function(result){
            if(callback){
                callback(result); 
            }
        });
      }

      function displayTime(timeValue){
        var today=new Date();
        var dateObj=new Date(timeValue);
        var gap=today.getTime()-dateObj.getTime();

        if(gap < (24 * 60 * 60 * 1000)){
            var hh=dateObj.getHours();
            var mi=dateObj.getMinutes();
            var ss=dateObj.getSeconds();
            
            return [(hh > 9 ? '':'0')+hh,(mi > 9 ? '':'0')+mi,(ss > 9 ? '':'0')+ss].join(" : ");
        }else{
            var yy=dateObj.getFullYear();
            var mm=dateObj.getMonth()+1 //getMonth()은 0부터 시작
            var dd=dateObj.getDate();

            return [yy,(mm > 9 ? '':'0')+mm,(dd > 9 ? '':'0')+dd].join(" - ");
        }
      }
    return{add:add, getList:getList, remove:remove, update:update, get:get, displayTime:displayTime};
})();