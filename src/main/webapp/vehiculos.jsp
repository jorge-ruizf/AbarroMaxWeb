<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos - Pagina Principal</title>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.3.2/js/dataTables.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/2.3.2/css/dataTables.dataTables.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <style>
            #olitaFooter {
                position: fixed;
                bottom: 0px;
            }
        </style>
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
                    <a class="btn btn-primary" href="registro_vehiculos.jsp">Nuevo Producto</a>
                </div>
            </div>
        </div>
        <script>
            new DataTable('#example');
        </script>
        <footer>
            <svg id="olitaFooter" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 170"><path fill="#0099ff" fill-opacity="1" d="M0,128L60,138.7C120,149,240,171,360,165.3C480,160,600,128,720,117.3C840,107,960,117,1080,106.7C1200,96,1320,64,1380,48L1440,32L1440,320L1380,320C1320,320,1200,320,1080,320C960,320,840,320,720,320C600,320,480,320,360,320C240,320,120,320,60,320L0,320Z"></path></svg>
        </footer>
    </body>
</html>
