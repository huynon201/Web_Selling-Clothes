<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,Model.ConnectionPool" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
		    <meta charset="utf-8">
		    <meta content="width=device-width, initial-scale=1.0" name="viewport">
		
		    <title>Tables / Data - NiceAdmin Bootstrap Template</title>
		    <meta content="" name="description">
		    <meta content="" name="keywords">
		
		    <!-- Favicons -->
		    <link href="/assets/img/favicon.png" rel="icon">
		    <link href="/assets/img/apple-touch-icon.png" rel="apple-touch-icon">
		
		    <!-- Google Fonts -->
		    <link href="https://fonts.gstatic.com" rel="preconnect">
		    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
		
		    <!-- Vendor CSS Files -->
	
		    <link href="../css/bootstrap.min.css" rel="stylesheet">
		    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
		    <link href="../css/boxicons.min.css" rel="stylesheet">
		    <link href="../css/quill.snow.css" rel="stylesheet">
		    <link href="../css/quill.bubble.css" rel="stylesheet">
		    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css">
		    <link href="../css/style-data.css" rel="stylesheet">
		    <!-- Template Main CSS File -->
		    <link href="../css/style.css" rel="stylesheet">
		    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
		</head>

        <body>
             <jsp:include page="../layout/header.jsp" />
			 <jsp:include page="../layout/sidebar.jsp" />

            <main id="main" class="main">
                <div class="pagetitle">
                    <h1>Quản lý hãng</h1>
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
                            <li class="breadcrumb-item"><a href="index.html">Quản lý</a></li>
                            <li class="breadcrumb-item active">Quản lý hãng</li>
                        </ol>
                    </nav>
                </div><!-- End Page Title -->
                <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between">
                                <h5 class="card-title">Danh sách hãng</h5>
                                <a class="btn btn-primary m-3 mb-2" href="../brand/create">Thêm hãng</a>
                            </div>
                             
                            <!-- Table with stripped rows -->
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="col-1">STT</th>
                                        <th class="col-2">Tên hãng</th>
                   
                                        <th class="col-2">Email liên hệ</th>
                                        <th class="col-2">Số liên hệ</th>
                                        <th class="col-3 ps-5">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="brand" items="${brands}" varStatus="status">
                                   
                                        <tr>
                                            <td>${status.count}</td>
                                            <td style = "display:none">${brand.id}</td>
                                            <td>${brand.name}</td>
                                            <td>${brand.contact_email}</td>
                                            <td>${brand.contact_phone}</td>
                                            <td>
							                  <a class="btn btn-success" href="${pageContext.request.contextPath}/brand/detail/${brand.id}">Chi tiết</a>


							                    <a href="${pageContext.request.contextPath}/brand/update?id=${brand.id}" class="btn btn-primary">Sửa</a>

							                    <a class="btn btn-danger"
												   href="javascript:void(0);"
												   onclick="confirmDelete('${pageContext.request.contextPath}/brand/delete/${brand.id}')">Xóa</a>

							                </td>
                                        </tr>
                                    </c:forEach>
                                
                                </tbody>
                            </table>
                       
                        </div>
                    </div>
                </div>
            </div>
        </section>

            </main><!-- End #main -->

             <jsp:include page="../layout/footer.jsp" />


            <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                    class="bi bi-arrow-up-short"></i></a>
			
		<script>
		    function confirmDelete(deleteUrl) {
		        if (confirm("Bạn có chắc chắn muốn xóa hãng này không này không?")) {
		            window.location.href = deleteUrl; // Điều hướng đến URL xóa
		        }
		    }
		</script>
		
            <script src="../js/apexcharts.min.js"></script>
			<script src="../js/bootstrap.bundle.min.js"></script>
			<script src="../js/chart.umd.js"></script>
			<script src="../js/echarts.min.js"></script>
			<script src="../js/quill.js"></script>
			<script src="../js/simple-datatables.js"></script>
			<script src="../js/tinymce.min.js"></script>
			<script src="../js/validate.js"></script>
			<!-- Template Main JS File -->
			<script src="../js/main.js"></script>


        </body>

        </html>