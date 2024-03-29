<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>

    <!-- Custom fonts for this template -->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
     <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Kirang+Haerang&display=swap" rel="stylesheet">    

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="../index">
                <div class="sidebar-brand-icon rotate-n-15">
                </div>
                <div class="sidebar-brand-text mx-3"style="font-family: 'Kirang Haerang';font-size: 50px;">빵지도 </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
           <!--  <li class="nav-item">
                <a class="nav-link" href="../index">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li> -->

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="/map/list">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>지도 보기 </span></a>
            </li>
			
			<!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="/board/notice">
                    <i class="fas fa-fw fa-table"></i>
                    <span>공지 </span></a>
            </li>
            
            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="/board/free">
                    <i class="fas fa-fw fa-table"></i>
                    <span>자유게시판 </span></a>
            </li>
            
            
            
            <c:choose>
               <c:when test="${sessionScope.user ne null}">
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>${sessionScope.user.userName}</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="/user/logout">로그아웃 </a>
                        <a class="collapse-item" href="/user/modifyUser">Modify</a>
                    </div>
                </div>
            </li>
                </c:when>
                <c:otherwise>
                	<li class="nav-item">
                <a class="nav-link" href="/user/login">
                    <i class="fas fa-fw fa-table"></i>
                    <span>로그인 </span></a>
            </li>
                </c:otherwise>
            </c:choose>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="/img/undraw_profile_1.svg"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler Â· 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="/img/undraw_profile_2.svg"
                                            alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun Â· 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="/img/undraw_profile_3.svg"
                                            alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez Â· 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog Â· 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle"
                                    src="/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
			
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                        </div>
                        <div class="card-body">
                        	<form method="post" action="/board/delete" id="actionForm">
                            <div class="table-responsive">
                                <input type="hidden" name="pageNum" value="${cri.pageNum }">
					    		<input type="hidden" name="amount" value="${cri.amount }">
					    		<input type="hidden" name="keyword" value="${cri.keyword }">
					    		<input type="hidden" name="type" value="${cri.type }">
					    		<input type="hidden" name="boardNo" value="${board.boardNo }">
					    		<input type="hidden" name="cate" value="${board.cate }">
					    		
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <tr>
                                            <th style="border-right:none" colspan="8">${board.boardTitle } </th>
                                        </tr>  
                                        <tr>
                                            <td style="border-right:none">작성자 </td>
                                            <td style="border-right:none">${board.boardWriter }  </td>
                                            <td style="border-right:none">조회수 </td>
                                            <td style="border-right:none">${board.boardView }</td>
                                            <td style="border-right:none">작성일 </td>
                                            <td style="border-right:none">${board.boardDate }</td>
                                            <td style="border-right:none">추천수 </td>
                                            <td style="border-right:none">${board.boardReco }</td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" style="padding-bottom:300px;border-right:none">${board.boardContent } </td>
                                        </tr>
                                 </table>
                                 <div>
                                 	<input type="button" class="recoBtn btn btn-primary" style="display: inline-block;" value="추천 ">
                                 </div>
                    <!-- Comments section-->
                    <section class="mb-5">
                        <div class="card bg-light">
                            <div class="card-body">
                                <!-- Comment form-->
                                	<input type="text" name="replyer" value="${sessionScope.user.userName}" readonly>
                                	<textarea class="form-control" name="reply-content" rows="3" style="resize: none;width: 100%;" placeholder="댓글을 입력하세요."></textarea>
                                	<a href="#" id="addReplyBtn" class="btn btn-primary addReplyBtn">댓글 등록 </a>
                                	<!-- 댓글 리스트  -->
                                	<ul class="replies" style="padding-left: 0;">
                                	</ul>
                                	<div class="paging" style="text-align: center;"></div>
                        </div>
                        </div>
                    </section>
                    <div class="button">
                    	<c:choose>
                    		<c:when test="${sessionScope.user ne null && (board.boardWriter == sessionScope.user.userId)}">
                    	<button type="button" class="btn btn-primary btn-lg" id="submitButton" onclick="location.href='/board/modify${cri.getListLink()}&boardNo=${board.boardNo }'">수정 </button>
                    	<button type="button" class="btn btn-secondary btn-lg" id="submitButton" onclick="javascript:deleteBoard();">삭제 </button>
                    		</c:when>
                    	</c:choose>
                    	<button type="button" class="btn btn-secondary btn-lg backList" id="submitButton">목록 </button>
                    </div>
                    </div>
                    </form>
                </div>
            </div>
            <!-- End of Main Content -->
           	</div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">Ã</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/js/demo/datatables-demo.js"></script>
    
    <!-- write template scripts -->
	<!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
	
	<script src="/js/board/view.js"></script>
	<script src="/js/board/reply.js"></script>
	<script> var contextPath = "${pageContext.request.contextPath}";</script>
	<script>
	$(document).ready(function(){
        var boardNo="${board.boardNo}";
        var replyUL = $(".replies");
        var pageNum = 1;
       	var replyPaging=$(".paging");
        
        showList(pageNum);
        
        //댓글 페이징 처리 
        function showReplyPage(replyCnt){
        	var str="";
        	var endNum=Math.ceil(pageNum/10.0)*10;
        	var startNum=endNum-9;
        	var realEnd=Math.ceil(replyCnt/10.0);
        	
        	var prev=startNum!=1;
        	var next=false;
        	
        	console.log(endNum);
        	console.log(startNum);
        	console.log(realEnd);
        	
        	if(endNum>realEnd/10.0){
        		endNum=realEnd;
        	}
        	if(endNum*10<replyCnt){
        		next=true;
        	}
        	if(prev){
        		str+="<a class='changePage' href='"+(startNum-1)+"'><code>&lt;</code></a>";
        	}
        	for(let i = startNum; i <=endNum; i++){
				if(pageNum == i){
					str+="<code>" + i + "</code>";
					continue;
				}
				str += "<a class='changePage' href='"+i+"'><code>"+ i + "</code></a>";
			}
        	if(next){
        		str+="<a class='changePage' href='"+(endNum+1)+"'><code>&gt;</code></a>";
        	}
        	replyPaging.html(str);
        }
        //페이지 이동 
        	$(".paging").on("click","a.changePage",function(e){
        		e.preventDefault();
        		pageNum=parseInt($(this).attr("href"));
        		//페이지 이동 후 목록 보여주기 
        		showList(pageNum);
        	});
        
       	function showList(page){
    	   replyService.getList({boardNo:boardNo, page:page||1},function(replyCnt, list){
    		   console.log("list:"+list+","+"replyCnt:"+replyCnt);
    		   
    		   var str="";
    		   var userId="${sessionScope.user.userId}";
    		   
    		   if(list==null || list.length==0){
    			   replyUL.html("댓글이 없습니다.");
    			   return;
    		   }
    		   for(var i=0, len=list.length||0;i<len;i++){
    			   	str +="<li class='reply_list' style = 'disply:block;list-style:none;' data-rno='"+ list[i].replyNo +"'>";
					str +="<p><strong>"+list[i].replyer+"</strong></p>";
					str +="<p class='reply"+list[i].replyNo+"'>"+list[i].replyContent+"</p>";
					str +="<div style='text-align:right;'>"+replyService.displayTime(list[i].modDate);
					if(list[i].replyer==userId){
						str	+="<br><a href='"+list[i].replyNo+"'class='modify'>수정</a>";
						str	+="<a href='"+list[i].replyNo+"' class='finish' style='display:none;'>수정 완료</a>";//댓글 작성 시간은 최종 수정시간이 기준 
						str	+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+list[i].replyNo+"'class='remove'>삭제</a>";
					}
					str +="<div class='reply'></div><hr></li>"; 
    		   }
    		   replyUL.html(str);
    		   showReplyPage(replyCnt);
    	   }); 
       	}
        
       	//댓글 등록 
       	$(".addReplyBtn").on("click",function(e){
    	   e.preventDefault();
    	   var replyContent=$("textarea[name='reply-content']").val();
    	   var replyer=$("input[name='replyer']").val();
    	   replyService.add({replyContent:replyContent, replyer:replyer, boardNo:boardNo},function(result){
    		   console.log(result);
    		   alert("등록이 완료되었습니다.");
    		   showList(pageNum);
    	   })
 			
       	});
       	
       	//댓글 삭제
       	$(".replies").on("click","a.remove",function(e){
       		e.preventDefault();
       		var rnoValue=$(this).attr("href");
       		replyService.remove(rnoValue,function(result){
       			alert("댓글이 삭제되었습니다.");
       			showList(pageNum);
       		})
       	})
       
       	//댓글 수정 버튼 눌렀을 때 
       	$(".replies").on("click","a.modify",function(e){
       		e.preventDefault();
       		var rnoValue=$(this).attr("href");
       		var replyTag=$(".reply"+rnoValue);
       		
       		replyTag.html("<textarea class='"+rnoValue+"'>"+replyTag.text()+"</textarea>")
       		$(this).hide();
       		
       		var finishs=$(".finish");
       		
       		for(let i=0; i<finishs.length;i++){
       			if($(finishs[i]).attr("href")==rnoValue){
       				$(finishs[i]).show();
       				break;
       			}
       		}
       	});
       	//댓글 수정 완료 버튼 눌렀을 때 
       $(".replies").on("click","a.finish",function(e){
    	   e.preventDefault();
    	   var rnoValue=$(this).attr("href");
    	   
    	   replyService.update({replyNo:rnoValue,replyContent:$("."+rnoValue).val()},
    			   function(result){
    		   			alert("수정이 완료되었습니다.");
    		   			showList(pageNum);
    	   })
       })
       
       
	//게시글 추천버튼 눌렀을 때 
	$(".recoBtn").on("click",function () {
		console.log(boardNo);
		$.ajax({
			type:"post",
			url :contextPath+"/board/recoCnt",
			contentType:"application/json; charset=UTF-8",
			dataType:"json",
			data: boardNo,
			success:function(data){
				if(data==0){
					console.log("추천 실패");
				}else{
					console.log("추천 완료");
					location.reload();
				}
			}
		
		});
	  });
      //게시글 목록버튼 눌렀을때   	
      $(".backList").on("click", function(){
    	  let cate="${board.cate}";
    	  console.log("cate : "+cate);
    	  if(cate==="free"){
    		  location.href="/board/free"+"${cri.getListLink()}"
    	  }else if(cate==="notice"){
    		  location.href="/board/notice"+"${cri.getListLink()}"
    	  }
      });
        
    });
	
	console.log("${sessionScope.user.userId==board.boardWriter}")
	console.log("${sessionScope.user.userId}")
	console.log("${board.boardWriter}")
	
	</script>
	
	
</body>

</html>