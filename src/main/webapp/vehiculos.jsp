<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehiculos MongoDB</title>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.3.2/js/dataTables.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/2.3.2/css/dataTables.dataTables.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    </head>
    <body>
        <div class="container text-center mt-5">
            <div class="row">
                <div class="col">

                </div>
                <div class="col-12">
                    <% List<Product> products = (List<Product>) request.getAttribute("products");%>
                    <table id="example" class="display">
                        <thead class="table-light">
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>CategoryId</th>
                                <th>Supplier</th>
                                <th style="text-align: center">Actions</th>
                            </tr>
                        <tbody class="table-group-divider">
                            <%for (Product product : products) {%>
                            <tr>
                                <td>
                                    <%= product.getId()%>
                                </td>
                                <td>
                                    <%= product.getName()%>
                                </td>
                                <td>
                                    <%= product.getCategoryId()%>
                                </td>
                                <td>
                                    <%= product.getSupplier()%>
                                </td>
                                <td>
                                    <a class="btn btn-primary" href="SvVehiculos?accion=editar&id=<%= product.getId()%>" role="button">Editar</a>
                                    <a class="btn btn-danger" href="SvVehiculos?accion=eliminar&id=<%= product.getId()%>" role="button" onclick="return confirm('¿Estás seguro de eliminar este vehículo?');">Eliminar</a>
                                </td>
                            </tr>              
                            <% }%>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>CategoryId</th>
                                <th>Suplieer</th>
                                <th style="text-align: center">Actions</th>
                            </tr>
                        </tfoot>
                        </thead>
                    </table>

                </div>
                <div class="col">

                </div>
            </div>
            <div>
                <div class="col-12 text-end">
                    <a class="btn btn-primary" href="registro_vehiculos.jsp">Nuevo Vehículo</a>
                </div>
            </div>
        </div>
        <script>
            new DataTable('#example');
        </script>
    </body>
</html>
