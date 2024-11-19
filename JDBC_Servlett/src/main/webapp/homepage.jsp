<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,Model.ConnectionPool" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Steamen - Thời trang</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="keywords" />
    <meta content="" name="description" />

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
      rel="stylesheet"
    />

    <!-- Icon Font Stylesheet -->
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
      rel="stylesheet"
    />

    <!-- Libraries Stylesheet -->
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css_user/bootstrap.min.css" rel="stylesheet" />

    <!-- Template Stylesheet -->
    <link href="css_user/style.css" rel="stylesheet" />
  </head>
<body>
 <jsp:include page="layout_user/header.jsp" />
<!-- Homepage Content -->
    <!-- Homepage Content -->
     <div class="container py-5">
    <h2 class="text-center mb-4 text-uppercase fw-bold">Sản phẩm nổi bật</h2>
    <div class="row g-4">
        <!-- Kiểm tra xem có sản phẩm nào không -->
        <c:if test="${not empty products}">
            <c:forEach var="product" items="${products}">
                <div class="col-lg-3 col-md-6">
                    <div class="card h-100 text-center border-0 shadow-sm rounded-3">
                        <img 
                            src="product/image?id=${product.id}" 
                            class="card-img-top rounded-top" 
                            style="height: 250px; object-fit: cover;" 
                            alt="${product.name}">
                        <div class="card-body">
                            <h5 class="card-title text-truncate fw-bold text-primary">
                                <c:out value="${product.name}" />
                            </h5>
                            <span class="badge bg-secondary mb-2">
                                <c:out value="${product.target}" />
                            </span>
                            <p class="card-text text-danger fw-bold">
                                <c:out value="${product.price}" /> đ
                            </p>
                            <p class="card-text text-muted text-truncate">
                                <c:out value="${product.shortDesc}" />
                            </p>
                            <a href="shopDetail?id=${product.id}" class="btn btn-outline-primary btn-sm mt-2">
                                Xem chi tiết
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <!-- Nếu không có sản phẩm nào -->
        <c:if test="${empty products}">
            <div class="col-12 text-center">
                <p class="text-muted">Không có sản phẩm nào để hiển thị.</p>
            </div>
        </c:if>
    </div>
</div>

     <jsp:include page="layout_user/footer.jsp" />
     
     <a
      href="#"
      class="btn btn-primary border-3 border-primary rounded-circle back-to-top"
      ><i class="fa fa-arrow-up"></i
    ></a>

    <!-- JavaScript Libraries -->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/lightbox/js/lightbox.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="js_user/main.js"></script>
</body>
</html>