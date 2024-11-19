<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,Model.ConnectionPool" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật hãng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            max-width: 800px;
            margin-top: 40px;
            background-color: #ffffff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .form-group label {
            font-weight: bold;
        }

        .form-control {
            height: 45px;
            border: 1px solid #ced4da;
            border-radius: 5px;
        }

        .form-control:focus {
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            border-color: #80bdff;
        }

        .form-group textarea {
            height: 120px;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            padding: 10px 20px;
            border-radius: 5px;
        }

        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
            padding: 10px 20px;
            border-radius: 5px;
        }

        .card {
            background-color: #f8f9fa;
            border: none;
        }

        .card img {
            max-width: 100%;
            border-radius: 5px;
        }

        .form-text {
            font-size: 14px;
            color: #6c757d;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2 class="text-center mb-4 text-primary">Cập nhật hãng</h2>

        <c:if test="${not empty brand and not empty brand.id}">
  
            <form action="${pageContext.request.contextPath}/brand/update" method="post" enctype="multipart/form-data">
    
                <input type="hidden" name="id" value="${brand.id}">

       
                <div class="form-group mb-3">
                    <label for="name">Tên hãng:</label>
                    <input type="text" name="name" id="name" class="form-control" value="${brand.name}" required>
                </div>

                <div class="form-group mb-3">
                    <label for="country">Quốc gia :</label>
                    <input type="text" name="country" id="country" class="form-control" value="${brand.country}" required>
                </div>

                <div class="form-group mb-3">
                    <label for="des">Mô tả:</label>
                    <textarea name="des" id="des" class="form-control" required>${brand.des}</textarea>
                </div>

          
                <div class="form-group mb-3">
                    <label for="contact_email">Email liên hệ:</label>
                    <input type="email" name="contact_email" id="contact_email" class="form-control" value="${brand.contact_email}" required>
                </div>

                
                <div class="form-group mb-3">
                    <label for="contact_phone">Số liên hệ:</label>
                    <input type="text" name="contact_phone" id="contact_phone" class="form-control" required value="${brand.contact_phone}">
                </div>

           
                <div class="form-group mb-3">
                    <label for="website">Website:</label>
                    <input type="url" name="website" id="website" class="form-control" required value="${brand.website}">
                </div>

                <!-- Product Image -->
                <div class="form-group mb-3">
                    <label for="image">Ảnh sản phẩm:</label>
                    <input type="file" name="image" id="image" class="form-control">
                    <small class="form-text">Chọn ảnh mới nếu bạn muốn thay đổi ảnh sản phẩm.</small>
                    <div class="card mt-3 shadow-sm text-center">
                        <div class="card-body">
                            <label class="fw-bold mb-2">Ảnh hiện tại:</label>
                            <img src="${pageContext.request.contextPath}/brand/image?id=${brand.id}" alt="Product Image" class="img-fluid rounded">
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary w-100 mt-3">Cập nhật</button>
            </form>

            <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/brand/show" class="btn btn-secondary">Trở về</a>
            </div>
        </c:if>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
