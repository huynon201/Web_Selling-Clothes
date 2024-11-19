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
  
  <div class="container py-5">
    <div class="row">
      <div class="col-md-6">
        <img src="product/image?id=${product.id}" class="img-fluid rounded" alt="${product.name}" />
      </div>
      <div class="col-md-6">
        <h1><c:out value="${product.name}" /></h1>
        <h3 class="text-danger"><c:out value="${product.price}" /> đ</h3>
        <p>Giới tính: <c:out value="${product.target}" /></p>
        <p>Hãng: <c:out value="${product.brand}" /></p>
        <p>Mô tả: <c:out value="${product.shortDesc}" /></p>
        <!-- Tăng/giảm số lượng -->
            <div class="d-flex align-items-center mb-2">
                <button class="btn btn-secondary" id="decreaseQty">-</button>
                <input type="number" id="quantity" value="1" min="1" class="form-control mx-2" style="width: 60px;" />
                <button class="btn btn-secondary" id="increaseQty">+</button>
            </div>
        
        < <!-- Input số lượng -->
  <label for="quantity">Số lượng: </label>
  <input type="number" id="quantity" value="1" min="1" class="form-control mb-2" onchange="updateTotalPrice(${product.price})" />
  
		  <!-- Tổng giá -->
		  <p id="totalPrice">Tổng giá: <span><c:out value="${product.price}" /></span> đ</p>
		  
		  <!-- Nút thêm vào giỏ hàng -->
		  <a href="javascript:void(0);" class="btn btn-success mb-2" id="addToCartBtn" onclick="addToCart(${product.id}, ${product.price})">Thêm vào giỏ hàng</a>
	    
	    <!-- Nút xem mô tả chi tiết -->
	    <button id="toggleDescriptionButton" class="btn btn-info mb-2">Xem mô tả chi tiết</button>
	    
	    <!-- Mô tả chi tiết -->
	    <div id="detailedDescription" style="display:none;">
	        <p><c:out value="${product.detailDesc}" /></p>
	    </div>
    </div>
  </div>

  <jsp:include page="layout_user/footer.jsp" />
  <a
      href="#"
      class="btn btn-primary border-3 border-primary rounded-circle back-to-top"
      ><i class="fa fa-arrow-up"></i
    ></a>

    <!-- JavaScript Libraries -->
	
	<script>
	// Đối tượng giỏ hàng trong bộ nhớ trình duyệt
	var cart = {};

	// Cập nhật tổng giá khi thay đổi số lượng
	function updateTotalPrice(price) {
	  var quantity = document.getElementById("quantity").value;
	  var totalPrice = price * quantity;

	  // Cập nhật tổng giá hiển thị
	  document.getElementById("totalPrice").innerHTML = "Tổng giá: " + totalPrice + " đ";
	}

	// Thêm sản phẩm vào giỏ hàng (lưu vào đối tượng cart)
	function addToCart(productId, price) {
	  var quantity = parseInt(document.getElementById("quantity").value);

	  // Kiểm tra xem số lượng có hợp lệ không
	  if (isNaN(quantity) || quantity <= 0) {
	    alert("Số lượng không hợp lệ!");
	    return;
	  }

	  // Tính toán tổng giá
	  var totalPrice = price * quantity;

	  // Kiểm tra nếu sản phẩm đã có trong giỏ hàng
	  if (cart[productId]) {
	    // Cập nhật số lượng và tổng giá sản phẩm trong giỏ hàng
	    cart[productId].quantity += quantity;
	    cart[productId].totalPrice += totalPrice;
	  } else {
	    // Thêm sản phẩm vào giỏ hàng
	    cart[productId] = { quantity: quantity, totalPrice: totalPrice };
	  }

	  // Cập nhật số lượng giỏ hàng
	  updateCartQuantity();

	  // Hiển thị thông báo
	  alert("Sản phẩm đã được thêm vào giỏ hàng.");
	}

	// Cập nhật số lượng giỏ hàng
	function updateCartQuantity() {
	  var totalQuantity = 0;
	  for (var productId in cart) {
	    totalQuantity += cart[productId].quantity;
	  }

	  // Cập nhật số lượng giỏ hàng hiển thị
	  document.getElementById("cartQuantity").textContent = totalQuantity;
	}

	// Gọi hàm cập nhật giỏ hàng khi trang được tải
	window.onload = function() {
	  updateCartQuantity();
	};

	</script>

	
	
    <script>
	    var toggleButton = document.getElementById("toggleDescriptionButton");
	    var detailedDescription = document.getElementById("detailedDescription");
	
	    toggleButton.addEventListener("click", function() {
	        if (detailedDescription.style.display === "none") {
	            detailedDescription.style.display = "block";
	            toggleButton.textContent = "Ẩn mô tả chi tiết";
	        } else {
	            detailedDescription.style.display = "none";
	            toggleButton.textContent = "Xem mô tả chi tiết";
	        }
	    });
	</script>
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/lightbox/js/lightbox.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="js_user/main.js"></script>
</body>
</html>
