<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    </head>
    <body>
        <div class="container text-center mt-5">
            <div class="row">
                <div class="col-3">

                </div>
                <% List<String> categories = new ArrayList<>(Arrays.asList("Ropa", "Medicamentos", "Consumibles")); /*(List<String>) request.getAttribute("products")*/%>

                <div class="col-6 text-start border rounded pt-3 ps-3 pe-3">
                    <form action="SvVehiculos" method="POST">
                        <div class="mb-3">
                            <label for="formGroupExampleInput2" class="form-label">Id</label>
                            <input type="number" class="form-control" name="id" max="100000" placeholder="Ingrese el Id del producto" value="1" ><!--disabled-->
                        </div>
                        <div class="mb-3">
                            <label for="formGroupExampleInput2" class="form-label">Nombre del Producto</label>
                            <input type="text" class="form-control" name="name" maxLenght="50" placeholder="Ingrese el Nombre del producto">
                        </div>
                        <div class="mb-3">
                            <label for="formGroupExampleInput" class="form-label">Categoria</label>
                            <select class="form-select" name="categoryId" placeholder="Ingrese el ID de una Categoria">
                                <% for(int i = 0; i < categories.size(); i++){ %>
                                   <option value="<%=i%>"><%=categories.get(i)%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="formGroupExampleInput2" class="form-label">Proveedor</label>
                            <input type="text" class="form-control" name="supplier" maxLenght="50" id="formGroupExampleInput2" placeholder="Ingrese el nombre del Proveedor">
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Guardar</button>
                        </div>
                    </form>
                </div>
                <div class="col-3">

                </div>

            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    </body>
</html>