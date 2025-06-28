package com.juanfedevmaster.webtableexample;

import entities.Vehiculo;
import LogicaNegocio.VehiculoServicio;
import entities.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SvVehiculos extends HttpServlet {

    //protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getQueryString();
        boolean tieneFiltros = false;

        if (query != null && !query.isBlank()) {
            tieneFiltros = true;
        }

        List<Product> products = new ArrayList<>();
        VehiculoServicio vehiculoServicio = new VehiculoServicio();

        try {
            products = vehiculoServicio.getVehiculos();

            if (tieneFiltros) {
                String filtros[] = request.getParameter("filtro").split(";");

                switch (filtros[0]) {
                    /*case "Brand":
                        products = products.stream()
                                .filter(x -> x.getBrand().equals(filtros[1]))
                                .toList();
                        break;*/
                    case "Name":
                        products = products.stream()
                                .filter(x -> x.getName().equals(filtros[1]))
                                .toList();
                        break;
                    /*case "Type":
                        products = products.stream()
                                .filter(x -> x.getType().equals(filtros[1]))
                                .toList();
                        break;
                    case "Year Introduce":
                        products = products.stream()
                                .filter(x -> x.getYearIntroduced() == Integer.parseInt(filtros[1]))
                                .toList();
                        break;*/
                    case "Editar":
                        Product vehiculoFiltrado = products.stream()
                                .filter(x -> x.getName().equals(filtros[1]))
                                .findFirst().get();

                        request.setAttribute("productModify", vehiculoFiltrado);
                        RequestDispatcher dispatcherEditar = request.getRequestDispatcher("actualizar_vehiculos.jsp");
                        dispatcherEditar.forward(request, response);

                        return;
                }
            }

            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("vehiculos.jsp");

            dispatcher.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SvVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean editar = (request.getParameter("editar") == null || request.getParameter("editar").isBlank()) ? false : true;

        VehiculoServicio vehiculoServicio = new VehiculoServicio();

        Product product = new Product();
        product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        product.setName(request.getParameter("name"));
        product.setSupplier(request.getParameter("supplier"));
        product.setId(Integer.parseInt(request.getParameter("id")));

        if (!editar) {
            try {
                boolean resultado = vehiculoServicio.addVehiculo(product);
                if (resultado) {
                    System.out.println("Producto agregado correctamente");
                }
            } catch (Exception ex) {
                request.setAttribute("mensaje", "Error: " + ex.getMessage());
            }
        } else {
            Product productOrigin = new Product();
            productOrigin.setCategoryId(Integer.parseInt(request.getParameter("marca-o")));
            productOrigin.setName(request.getParameter("nombreVehiculo-o"));
            productOrigin.setSupplier(request.getParameter("tipoVehiculo-o"));
            productOrigin.setId(Integer.parseInt(request.getParameter("yearIntroduccion-o")));

            try {
                boolean resultado = vehiculoServicio.updateVehiculo(product, productOrigin);
                if (resultado) {
                    System.out.println("Vehículo modificado correctamente");
                }
            } catch (Exception ex) {
                request.setAttribute("mensaje", "Error: " + ex.getMessage());
            }
        }
        
        response.sendRedirect("SvVehiculos");

    }
}
