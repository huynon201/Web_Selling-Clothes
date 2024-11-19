<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,Model.ConnectionPool" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật sản phẩm</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        .container {
            max-width: 900px;
            margin-top: 30px;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-control {
            height: 40px;
        }

        .form-group textarea {
            height: 120px;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2 class="text-center mb-4">Cập nhật sản phẩm</h2>

     <c:if test="${not empty product and not empty product.id}">
    <!-- Form for updating product -->
    <form action="${pageContext.request.contextPath}/product/update" method="post" enctype="multipart/form-data">
        <!-- Hidden field for the product ID -->
        <input type="hidden" name="id" value="${product.id}">

        <!-- Product Name -->
        <div class="form-group mb-3">
            <label for="name">Tên sản phẩm:</label>
            <input type="text" name="name" id="name" class="form-control" value="${product.name}" required>
        </div>

        <!-- Product Price -->
        <div class="form-group mb-3">
            <label for="price">Giá:</label>
            <input type="number" name="price" id="price" class="form-control" value="${product.price}" required>
        </div>

        <!-- Product Brand -->
        <div class="form-group mb-3">
            <label for="brand">Hãng:</label>
            <input type="text" name="brand" id="brand" class="form-control" value="${product.brand}" required>
        </div>

        <!-- Product Target -->
        <div class="form-group mb-3">
            <label for="target">Giới tính:</label>
            <input type="text" name="target" id="target" class="form-control" value="${product.target}" required>
        </div>
		<!-- Product Description -->
        <div class="form-group mb-3">
            <label for="shortDesc">Mô tả ngắn:</label>
            <textarea name="shortDesc" id="shortDesc" class="form-control" required>${product.shortDesc}</textarea>
        </div>
        <!-- Product Description -->
        <div class="form-group mb-3">
            <label for="detailDesc">Mô tả:</label>
            <textarea name="detailDesc" id="detailDesc" class="form-control" required>${product.detailDesc}</textarea>
        </div>

        <!-- Product Image -->
        <div class="form-group mb-3">
            <label for="image">Ảnh sản phẩm:</label>
            <input type="file" name="image" id="image" class="form-control">
            <small class="form-text text-muted">Chọn ảnh mới nếu bạn muốn thay đổi ảnh sản phẩm.</small>
            <div class="mt-3">
                <label>Ảnh hiện tại:</label><br>
                <img src="${pageContext.request.contextPath}/product/image?id=${product.id}" alt="Product Image" width="150px">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>

    <div class="text-center">
        <a href="${pageContext.request.contextPath}/product/show" class="btn btn-secondary">Trở về</a>
    </div>
</c:if>



    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
