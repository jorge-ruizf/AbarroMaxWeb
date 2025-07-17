/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import entities.Product;
import Persistencia.VehiculoRepositorio;
import java.util.ArrayList;

/**
 *
 * @author jufeq
 */
public class VehiculoServicio {

    private final VehiculoRepositorio vehiculoRepositorio;

    public VehiculoServicio() {
        vehiculoRepositorio = new VehiculoRepositorio();
    }

    public ArrayList<Product> getVehiculos() throws Exception {
        return vehiculoRepositorio.getProducts();
    }

    public boolean addVehiculo(Product vehiculo) throws Exception {
        if (!vehiculoValido(vehiculo)) {
            return false;
        }

        if (vehiculoRepositorio.idExiste(vehiculo.getId())) {
            System.out.println("El ID ya existe. No se puede agregar.");
            return false;
        }

        return vehiculoRepositorio.addProduct(vehiculo);
    }


    public boolean updateVehiculo(Product product, Product productOrigin) throws Exception {
        System.out.println("VehiculoServicio.updateVehiculo: nuevo=" + product + ", original=" + productOrigin);
        if (!vehiculoValido(product)) {
            System.out.println("vehiculoValido: FALSO");
            return false;
        }
        if (vehiculoRepositorio.idExiste(product.getId())) {
            System.out.println("El ID ya existe. No se puede agregar.");
            return false;
        }
        boolean resultado = vehiculoRepositorio.updateProduct(product, productOrigin);
        System.out.println("vehiculoRepositorio.updateProduct resultado: " + resultado);
        return resultado;
    }

    public boolean deleteVehiculoById(int id) throws Exception {
        System.out.println("VehiculoServicio.deleteVehiculoById id=" + id);
        boolean resultado = vehiculoRepositorio.deleteProductById(id);
        System.out.println("vehiculoRepositorio.deleteProductById resultado: " + resultado);
        return resultado;
    }

    private boolean vehiculoValido(Product product) {
        if (product == null) {
            System.out.println("Producto es nulo");
            return false;
        }
        if (product.getId() < 0) {
            System.out.println("El id no puede ser negativo");
            return false;
        }
        if (product.getName() == null || product.getName().isBlank()) {
            System.out.println("El nombre no puede ser nulo o vacío");
            return false;
        }
        if (product.getCategoryId() < 0) {
            System.out.println("El categoryId debe ser mayor o igual a 0");
            return false;
        }
        if (product.getSupplier() == null || product.getSupplier().isBlank()) {
            System.out.println("El supplier no puede ser nulo o vacío");
            return false;
        }
        return true;
    }

}
