package com.juanfedevmaster.webtableexample;

import entities.Product;
import LogicaNegocio.ProductService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SvProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getQueryString();
        boolean hasFilters = query != null && !query.isBlank();

        List<Product> products = new ArrayList<>();
        ProductService productService = new ProductService();

        try {
            products = productService.getAllProducts();

            if (hasFilters) {
                String[] filters = request.getParameter("filtro").split(";");

                switch (filters[0]) {
                    case "Name":
                        products = products.stream()
                                .filter(x -> x.getName().equals(filters[1]))
                                .toList();
                        break;
                    case "Editar":
                        Product productToEdit = products.stream()
                                .filter(x -> x.getName().equals(filters[1]))
                                .findFirst().get();

                        request.setAttribute("productModify", productToEdit);
                        RequestDispatcher dispatcherEdit = request.getRequestDispatcher("actualizar_productos.jsp");
                        dispatcherEdit.forward(request, response);
                        return;
                }
            }

            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("vehiculos.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SvProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isEdit = request.getParameter("editar") != null && !request.getParameter("editar").isBlank();

        ProductService productService = new ProductService();

        Product product = new Product();
        product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        product.setName(request.getParameter("name"));
        product.setSupplier(request.getParameter("supplier"));
        product.setId(Integer.parseInt(request.getParameter("id")));

        if (!isEdit) {
            try {
                boolean result = productService.addProduct(product);
                if (result) {
                    System.out.println("Producto agregado correctamente");
                }
            } catch (Exception ex) {
                request.setAttribute("mensaje", "Error: " + ex.getMessage());
            }
        } else {
            Product originalProduct = new Product();
            originalProduct.setCategoryId(Integer.parseInt(request.getParameter("marca-o")));
            originalProduct.setName(request.getParameter("nombreVehiculo-o"));
            originalProduct.setSupplier(request.getParameter("tipoVehiculo-o"));
            originalProduct.setId(Integer.parseInt(request.getParameter("yearIntroduccion-o")));

            try {
                boolean result = productService.updateProduct(product, originalProduct);
                if (result) {
                    System.out.println("Producto modificado correctamente");
                }
            } catch (Exception ex) {
                request.setAttribute("mensaje", "Error: " + ex.getMessage());
            }
        }

        response.sendRedirect("SvProducts");
    }
}   
