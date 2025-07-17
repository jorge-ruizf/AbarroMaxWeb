<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos - Editar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container text-center mt-5">
        <div class="row">
            <div class="col-3"></div>

            <%
                Product productModify = (Product) request.getAttribute("productModify");
                List<String> categories = new ArrayList<>(Arrays.asList("Ropa", "Medicamentos", "Consumibles"));
            %>

            <div class="col-6 text-start border rounded pt-3 ps-3 pe-3">
                <form action="SvVehiculos" method="POST">
                    <input type="hidden" name="id-o" value="<%=productModify.getId()%>">
                    <input type="hidden" name="name-o" value="<%=productModify.getName()%>">
                    <input type="hidden" name="categoryId-o" value="<%=productModify.getCategoryId()%>">
                    <input type="hidden" name="supplier-o" value="<%=productModify.getSupplier()%>">
                    <input type="hidden" name="editar" value="editar">

                    <div class="mb-3">
                        <label class="form-label">ID</label>
                        <input type="number" class="form-control" max="100000" name="id" value="<%=productModify.getId()%>">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Nombre del Producto</label>
                        <input type="text" class="form-control" maxLenght="50" name="name" value="<%=productModify.getName()%>">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Categoría</label>
                        <select class="form-select" name="categoryId">
                            <% for (int i = 0; i < categories.size(); i++) { %>
                                <option value="<%=i%>" <%= (i == productModify.getCategoryId()) ? "selected" : "" %>><%= categories.get(i) %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Proveedor</label>
                        <input type="text" class="form-control" maxLenght="50" name="supplier" value="<%=productModify.getSupplier()%>">
                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
