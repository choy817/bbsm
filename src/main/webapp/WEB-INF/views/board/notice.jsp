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

    <title>공지사항 </title>

    <!-- Custom fonts for this template -->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

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
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="/index">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="/board/map">
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

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">자유게시판 </h1>
 -->                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h4 class="m-0 font-weight-bold text-primary">공지사항 </h4>
                        </div>
                        <div class="card-body">
                        	<div class="row">
                        		<div class="col-sm-12 col-md-6">
                        			<div id="dataTables_filter" class="dataTables_filter">
                        				<form action="/board/notice" id="searchForm" method="get">
                        					<label>
                                				<select name="type" style="border: 1px solid #e3e6f0">
                                					<option ${pageMaker.cri.type == null? 'selected':'' } value="">검색기준 </option>
                                					<option ${pageMaker.cri.type == 'T'? 'selected':'' } value="T">제목 </option>
                                					<option ${pageMaker.cri.type == 'C'? 'selected':'' } value="C">내용 </option>
                                					<option ${pageMaker.cri.type == 'W'? 'selected':'' } value="W">작성자 </option>
                                					<option ${pageMaker.cri.type == 'TC'? 'selected':'' } value="TC">제목 또는 내용 </option>
                                					<option ${pageMaker.cri.type == 'TW'? 'selected':'' } value="TW">제목 또는 작성자 </option>
                                					<option ${pageMaker.cri.type == 'TCW'? 'selected':'' } value="TCW">제목 또는 내용 또는 작성자 </option>
                                				</select>
                                				<input type="text" name="keyword" value="${pageMaker.cri.keyword }" style="border: 1px solid #e3e6f0">
                                				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                                				<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                                				<input type="hidden" name="cate" value="notice">
                                				<a href="#" class="btn btn-primary btn-icon-split">검색 </a>
                           					</label>
                                		</form>
                                	</div>
                                </div>
                               	<div class="col-sm-12 col-md-6">
                               		<a href="/board/write" class="btn btn-primary btn-lg" style="float: right;margin-bottom: 8px;">글쓰기 </a>
                               	</div>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>글번호 </th>
                                            <th>글제목 </th>
                                            <th>작성자 </th>
                                            <th>조회수 </th>
                                            <th>작성일 </th>
                                            <th>추천수 </th>
                                        </tr>
                                    </thead>
                                    <tbody>
	                                    <c:forEach items="${list }" var="board">
                                    		<tr>
	                                    		<td>${board.boardNo}</td>
	                                    		<td><a class="move" href="${board.boardNo }">${board.boardTitle}</a></td>
	                                    		<td>${board.boardWriter}</td>
	                                    		<td>${board.boardView}</td>
	                                    		<td>${board.boardDate}</td>
	                                    		<td>${board.boardReco}</td>
                                    		</tr>	
	                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="row"> 
                                <div class="col-sm-12 col-md-7">
                                <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                                	<ul class="pagination">
                                		<%-- 이전 페이지  --%>
                                		<c:if test="${pageMaker.prev }">
                                			<li class="paginate_button page-item" id="dataTable_previous">
                                				<a class="page-link" aria-controls="dataTable" data-dt-idx="${pageMaker.startPage-1 }" href="${pageMaker.startPage-1 }">&laquo;</a>
                                			</li>
                                		</c:if>
                                		<%-- 현재 페이지  --%>
                                		<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
                                			<c:choose>
                                				<c:when test="${pageMaker.cri.pageNum == num }">
                                					<li class="paginate_button page-item active">
                                					<a class="page-link" href="#" aria-controls="dataTable" data-dt-idx="${num }" tabindex="0">${num }</a>
                                					</li>
                                				</c:when>
                                				<c:otherwise>
                                					<li class="paginate_button page-item ">
                                						<a class="page-link" aria-controls="dataTable" data-dt-idx="${num }" tabindex="0" href="${num }">${num }</a>
                                					</li>
                                				</c:otherwise>
                                			</c:choose>
                                		</c:forEach>
                                		
                                		<%-- 다음 페이지  --%>
                                		<c:if test="${pageMaker.next }">
                                			<li class="paginate_button page-item next" id="dataTable_next">
                                				<a class="page-link" href="${pageMaker.endPage+1 }">&raquo;</a>
                                			</li>
                                		</c:if>
                                	</ul>
                                	<form id="actionForm" action="/board/notice" method="get">
                                		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                                		<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                                		<input type="hidden" name="type" value="${pageMaker.cri.type }">
                                		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
                                		<input type="hidden" name="cate" value="notice">
                                	</form>
                                	<%-- 다른 페이지로 이동 한 후 다시 목록 돌아올 때 이전 페이지 번호를 기억  --%>
                                	<form id="pageForm" action="">
                                		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                                		<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                                		<input type="hidden" name="type" value="${pageMaker.cri.type }">
                                		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
                                		<input type="hidden" name="cate" value="notice">
                                	</form>
                                </div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

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
    
    <script type="text/javascript" src="/js/board/boardList.js"></script>
    
    <script type="text/javascript">
	const msg="${msg}"
		if(msg=="modSuccess"){
			alert("게시글 수정이 완료되었습니다.")
		}else if(msg=="delSuccess"){
			alert("게시글 삭제가 완료되었습니다.")
		}
	</script>
    <script type="text/javascript">console.log("${pageMaker.endPage}")</script>

    <!-- Page level plugins -->
   <!--  <script src="/vendor/datatables/jquery.dataTables.min.js"></script>-->
  <!--  <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>-->

    <!-- Page level custom scripts -->
   <!--  <script src="/js/demo/datatables-demo.js"></script>-->

</body>

</html>