<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,Model.ConnectionPool" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Chi tiết thông tin hãng</title>

    <!-- Cập nhật các liên kết CSS từ CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Đảm bảo container chiếm đủ chiều cao của viewport */
        body, html {
           
            margin: 0;
        }
		.card {
		 max-heigth: 10vh;
		}
        .container {
            max-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-between; 
        }

        
        .card img {
            width: 100%;
            max-height: 100%; 
            object-fit: conver;
        }
    

        
        .card-body {
            flex-grow: 1;
        }

        .card-footer {
            text-align: right;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1 class="text-center mb-2">Chi tiết thông tin hãng</h1>

        <div class="card shadow-sm">
            <div class="row g-0">
     
                <div class="col-md-4">
                    <img src="${pageContext.request.contextPath}/brand/image?id=${brand.id}" alt="Brand Image" class="img-fluid rounded-start">
                </div>

       
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin hãng</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>ID:</strong> ${brand.id}</li>
                            <li class="list-group-item"><strong>Tên hãng:</strong> ${brand.name}</li>
                            <li class="list-group-item"><strong>Mô tả:</strong> ${brand.des}</li>
                            <li class="list-group-item"><strong>Quốc gia:</strong> ${brand.country}</li>
                            <li class="list-group-item"><strong>Email:</strong> ${brand.contact_email}</li>
                            <li class="list-group-item"><strong>Số điện thoại:</strong> ${brand.contact_phone}</li>
                            <li class="list-group-item"><strong>Website:</strong> <a href="${brand.website}" target="_blank">${brand.website}</a></li>
                        </ul>
                    </div>
                </div>
            </div>

  
            <div class="card-footer">
                <a href="${pageContext.request.contextPath}/brand/show" class="btn btn-primary">Trở về</a>
            </div>
        </div>
    </div>

   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
