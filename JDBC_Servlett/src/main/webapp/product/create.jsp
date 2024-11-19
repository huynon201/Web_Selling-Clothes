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
			    <link href="../assets/img/favicon.png" rel="icon">
			    <link href="../assets/img/apple-touch-icon.png" rel="apple-touch-icon">
			
			    <!-- Google Fonts -->
			    <link href="https://fonts.gstatic.com" rel="preconnect">
			    <link
			        href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
			        rel="stylesheet">
			
			    <!-- Vendor CSS Files -->
			    <link href="../css/bootstrap.min.css" rel="stylesheet">
			    <link rel="stylesheet"
			        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
			    <link href="../css/boxicons.min.css" rel="stylesheet">
			    <link href="../css/quill.snow.css" rel="stylesheet">
			    <link href="../css/quill.bubble.css" rel="stylesheet">
			    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css">
			
			    <!-- JS Files -->
			    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
			    <link href="../css/style-data.css" rel="stylesheet">
			
			    <script>
			        $(document).ready(() => {
			            const avatarFile = $("#avatarFile");
			            avatarFile.change(function (e) {
			                const imgURL = URL.createObjectURL(e.target.files[0]);
			                $("#avatarPreview").attr("src", imgURL);
			                $("#avatarPreview").css({ "display": "block" });
			            });
			        });
			    </script>
			    
			    <!-- Template Main CSS File -->
			    <link href="../css/style.css" rel="stylesheet">
			</head>

            <body>
                <jsp:include page="../layout/header.jsp" />
                <jsp:include page="../layout/sidebar.jsp" />

                <main id="main" class="main">
                    <div class="pagetitle">
                        <h1>Quản lý sản phẩm</h1>
                        <nav>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
                                <li class="breadcrumb-item"><a href="index.html">Quản lý</a></li>
                                <li class="breadcrumb-item active">Quản lý sản phẩm</li>
                            </ol>
                        </nav>
                    </div>
                    <!-- End Page Title -->
                    <section class="section">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-md-8 col-12 mx-auto">
                                            <h1>Create a product</h1>
                                            <hr>
                                            <form action="${pageContext.request.contextPath}/product/create" method="post" enctype="multipart/form-data">

											    <div class="mb-3 col-12 col-md-6">
											        <label for="name" class="form-label">Tên:</label>
											        <input type="text" class="form-control" id="name" name="name" required>
											    </div>
											    <div class="mb-3 col-12 col-md-6">
											        <label for="price" class="form-label">Giá:</label>
											        <input type="number" class="form-control" id="price" name="price" required step="0.01">
											    </div>
											
											    <div class="mb-3 col-12">
											        <label for="detailDesc" class="form-label">Mô tả chi tiết:</label>
											        <textarea class="form-control" id="detailDesc" name="detailDesc" required></textarea>
											    </div>
											    <div class="mb-3 col-12 col-md-6">
											        <label for="shortDesc" class="form-label">Mô tả ngắn:</label>
											        <input type="text" class="form-control" id="shortDesc" name="shortDesc" required>
											    </div>
											    <div class="mb-3 col-12 col-md-6">
											        <label for="quantity" class="form-label">Số lượng:</label>
											        <input type="number" class="form-control" id="quantity" name="quantity" required>
											    </div>
											    <div class="mb-3 col-12 col-md-6">
											        <label for="brand" class="form-label">Hãng:</label>
											        <input type="text" class="form-control" id="brand" name="brand" required>
											    </div>
											    <div class="mb-3 col-12">
											        <label for="target" class="form-label">Giới tính:</label>
											        <select class="form-select" id="target" name="target" required>
											            <option value="Nam">Nam</option>
											            <option value="Nữ">Nữ</option>
											            
											        </select>
											    </div>
											    <div class="mb-3 col-12 col-md-6">
											        <label for="image" class="form-label">Ảnh:</label>
											        <input class="form-control" type="file" id="image" name="image" accept=".png, .jpg, .jpeg" required>
											    </div>
											    <div class="col-12 md-5">
											        <img style="max-height: 250px; display: none;" alt="avatar preview" id="avatarPreview">
											    </div>
											    <div class="col-12">
											       <button type="submit" class="btn btn-primary m-3 mb-2">Thêm sản phẩm</button>
											    </div>
											</form>


										
										    <script>
										        // Optional: Script to preview selected image
										       document.getElementById('image').addEventListener('change', function(event) {
												    const preview = document.getElementById('avatarPreview');
												    const file = event.target.files[0];
												    if (file) {
												        const reader = new FileReader();
												        reader.onload = function(e) {
												            preview.src = e.target.result;
												            preview.style.display = 'block';
												        };
												        reader.readAsDataURL(file);
												    }
										        });
										    </script>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>

                </main><!-- End #main -->

                <jsp:include page="../layout/footer.jsp" />

                <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                        class="bi bi-arrow-up-short"></i></a>

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