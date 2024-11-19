<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,Model.ConnectionPool" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>
    <!-- Add Bootstrap CSS if not already included -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .card-footer {
            position: absolute;
            bottom: 0;
            right: 0;
            padding: 1rem;
        }
        .card {
            position: relative;
        }
    </style>
</head>

<body>
    <main id="main" class="main">
        <div class="pagetitle">
            <h1>Chi tiết sản phẩm</h1>
        </div><!-- End Page Title -->
        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body d-flex">
                            <!-- Card hình ảnh sản phẩm nằm bên trái -->
                            <div class="card me-4" style="width: 30%;">
                                <img class="card-img-top" src="${pageContext.request.contextPath}/product/image?id=${product.id}" alt="Card image cap">
                            </div>

                            <!-- Thông tin sản phẩm nằm bên phải ảnh -->
                            <div class="card-body" style="flex-grow: 1; text-align: left;">
                                <div class="card-header">
                                    <h5>Thông tin sản phẩm</h5>
                                </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">ID: ${product.id}</li>
                                    <li class="list-group-item">Tên sản phẩm: ${product.name}</li>
                                    <li class="list-group-item">Giá: ${product.price} đ</li>
                                    <li class="list-group-item">Hãng: ${product.brand}</li>
                                    <li class="list-group-item">Mô tả: ${product.detailDesc}</li>
                                    <li class="list-group-item">Mô tả ngắn: ${product.shortDesc}</li>
                                    <li class="list-group-item">Giới tính: ${product.target}</li>
                                    <li class="list-group-item">Ngày tạo: ${product.createDate}</li>
                                </ul>
                            </div>
                        </div>

                        <!-- Nút trở về ở góc dưới bên phải -->
                        <div class="card-footer text-end">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/product/show">Trở về</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <!-- Add Bootstrap JS if not already included -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
