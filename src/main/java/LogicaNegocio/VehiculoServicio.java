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
    
    public ArrayList<Product> getVehiculos() throws Exception{
        return vehiculoRepositorio.getProducts();
    }

    public boolean addVehiculo(Product vehiculo) throws Exception {
        
        if (!vehiculoValido(vehiculo)) {
            return false;
        }

        return vehiculoRepositorio.addProduct(vehiculo);
    }
    
    public boolean updateVehiculo(Product product, Product productOrigin) throws Exception {
        
        if (!vehiculoValido(product)) {
            return false;
        }

        return vehiculoRepositorio.updateProduct(product, productOrigin);
    }


    private boolean vehiculoValido(Product product) {
        /*  Ajustar Samuel
        if (product == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo");
        }

        if (product.getBrand() == null || product.getBrand().isBlank()) {
            throw new IllegalArgumentException("La marca del vehículo no puede ser nula o vacia");
        }

        if (product.getType() == null || product.getType().isBlank()) {
            throw new IllegalArgumentException("El tipo del vehículo no puede ser nulo o vacio");
        }

        if (product.getYearIntroduced() == 0 || product.getYearIntroduced() < 1886) {
            throw new IllegalArgumentException("El año del vehículo no puede ser nulo o vacio");
        }

        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del vehículo debe ser no puede ser nulo o vacio");
        }
        */
        return true;
    }
}
