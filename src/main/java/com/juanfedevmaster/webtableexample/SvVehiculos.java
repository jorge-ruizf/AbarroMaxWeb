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

        String accion = request.getParameter("accion");
        String idStr = request.getParameter("id");
        VehiculoServicio vehiculoServicio = new VehiculoServicio();

        System.out.println("doGet accion: " + accion + ", id: " + idStr);
        
        try {
            if (accion != null && idStr != null) {
                int id = Integer.parseInt(idStr);

                switch (accion) {
                    case "editar":
                        List<Product> productos = vehiculoServicio.getVehiculos();
                        Product productoEditar = productos.stream()
                                .filter(p -> p.getId() == id)
                                .findFirst()
                                .orElse(null);

                        if (productoEditar != null) {
                            request.setAttribute("productModify", productoEditar);
                            request.getRequestDispatcher("actualizar_vehiculos.jsp").forward(request, response);
                            return;
                        } else {
                            request.setAttribute("mensaje", "Producto no encontrado");
                        }
                        break;

                    case "eliminar":
                        boolean eliminado = vehiculoServicio.deleteVehiculoById(id);
                        if (!eliminado) {
                            request.setAttribute("mensaje", "No se pudo eliminar el producto con ID " + id);
                        }
                        response.sendRedirect("SvVehiculos");
                        return;
                }
            }

            List<Product> products = vehiculoServicio.getVehiculos();
            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("vehiculos.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("mensaje", "Error: " + ex.getMessage());
            ex.printStackTrace();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entró a doPost. Editar: " + request.getParameter("editar"));
        boolean editar = request.getParameter("editar") != null && !request.getParameter("editar").isBlank();

        VehiculoServicio vehiculoServicio = new VehiculoServicio();

        Product product = new Product();
        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setName(request.getParameter("name"));
        product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        product.setSupplier(request.getParameter("supplier"));
        
        System.out.println("PARÁMETROS RECIBIDOS:");
        System.out.println("id: " + request.getParameter("id"));
        System.out.println("name: " + request.getParameter("name"));
        System.out.println("categoryId: " + request.getParameter("categoryId"));
        System.out.println("supplier: " + request.getParameter("supplier"));


        System.out.println("Datos recibidos para guardar/editar: " + product);

        if (!editar) {
            try {
                System.out.println("Intentando agregar producto");
                boolean resultado = vehiculoServicio.addVehiculo(product);
                System.out.println("Resultado agregar: " + resultado);
            } catch (Exception ex) {
                System.out.println("Error en agregar: " + ex.getMessage());
            }
        } else {
            Product productOrigin = new Product();
            productOrigin.setId(Integer.parseInt(request.getParameter("id-o")));
            productOrigin.setName(request.getParameter("name-o"));
            productOrigin.setCategoryId(Integer.parseInt(request.getParameter("categoryId-o")));
            productOrigin.setSupplier(request.getParameter("supplier-o"));

            System.out.println("Datos para editar: original=" + productOrigin + ", nuevo=" + product);
            try {
                boolean resultado = vehiculoServicio.updateVehiculo(product, productOrigin);
                System.out.println("Resultado editar: " + resultado);
            } catch (Exception ex) {
                System.out.println("Error en editar: " + ex.getMessage());
            }
        }
        response.sendRedirect("SvVehiculos");
    }

}
