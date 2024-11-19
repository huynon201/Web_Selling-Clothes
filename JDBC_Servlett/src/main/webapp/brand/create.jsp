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
                        <h1>Quản lý hãng</h1>
                        <nav>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
                                <li class="breadcrumb-item"><a href="index.html">Quản lý</a></li>
                                <li class="breadcrumb-item active">Quản lý hãng</li>
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
                                            <h1>Tạo hãng</h1>
                                            <hr>
                                            <form action="${pageContext.request.contextPath}/brand/create" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
										    <div class="mb-3 col-12 col-md-6">
										        <label for="name" class="form-label">Tên:</label>
										        <input type="text" class="form-control" id="name" name="name" required>
										    </div>
										    <div class="mb-3 col-12 col-md-6">
										        <label for="country" class="form-label">Thành phố:</label>
										        <input type="text" class="form-control" id="country" name="country" required>
										    </div>
										    <div class="mb-3 col-12">
										        <label for="des" class="form-label">Mô tả:</label>
										        <textarea class="form-control" id="des" name="des" required></textarea>
										    </div>
										    <div class="mb-3 col-12 col-md-6">
										        <label for="contact_email" class="form-label">Email liên hệ:</label>
										        <input type="email" class="form-control" id="contact_email" name="contact_email" required>
										    </div>
										    <div class="mb-3 col-12 col-md-6">
										        <label for="contact_phone" class="form-label">Số liên hệ:</label>
										        <input type="text" class="form-control" id="contact_phone" name="contact_phone" required>
										    </div>
										    <div class="mb-3 col-12 col-md-6">
										        <label for="website" class="form-label">Website:</label>
										        <input type="url" class="form-control" id="website" name="website" required>
										    </div>
										    <div class="mb-3 col-12 col-md-6">
										        <label for="image" class="form-label">Ảnh:</label>
										        <input class="form-control" type="file" id="image" name="image" accept=".png, .jpg, .jpeg">
										    </div>
										    <div class="col-12 md-5">
											        <img style="max-height: 250px; display: none;" alt="avatar preview" id="avatarPreview">
											    </div>
										    <div class="col-12">
										        <button type="submit" class="btn btn-primary mb-4">Thêm hãng</button>
										    </div>
										</form>
											<div class="text-center" style="position: relative;">
               									 <a class="btn btn-primary" href="${pageContext.request.contextPath}/brand/show">Trở về</a>
            								</div>

										
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