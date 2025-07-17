<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos - Crear Producto</title>
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
        
        <footer>
            <svg id="olitaFooter" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 220"><path fill="#0099ff" fill-opacity="1" d="M0,224L26.7,229.3C53.3,235,107,245,160,218.7C213.3,192,267,128,320,117.3C373.3,107,427,149,480,154.7C533.3,160,587,128,640,122.7C693.3,117,747,139,800,144C853.3,149,907,139,960,122.7C1013.3,107,1067,85,1120,106.7C1173.3,128,1227,192,1280,208C1333.3,224,1387,192,1413,176L1440,160L1440,320L1413.3,320C1386.7,320,1333,320,1280,320C1226.7,320,1173,320,1120,320C1066.7,320,1013,320,960,320C906.7,320,853,320,800,320C746.7,320,693,320,640,320C586.7,320,533,320,480,320C426.7,320,373,320,320,320C266.7,320,213,320,160,320C106.7,320,53,320,27,320L0,320Z"></path></svg>
        </footer>
    </body>
</html>