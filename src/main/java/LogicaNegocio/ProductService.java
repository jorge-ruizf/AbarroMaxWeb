package LogicaNegocio;

import entities.Product;
import Persistencia.ProductRepository;
import java.util.ArrayList;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public ArrayList<Product> getAllProducts() throws Exception {
        return productRepository.getProducts();
    }

    public boolean addProduct(Product product) throws Exception {
        if (!isProductValid(product)) {
            return false;
        }
        return productRepository.addProduct(product);
    }

    public boolean updateProduct(Product updatedProduct, Product originalProduct) throws Exception {
        if (!isProductValid(updatedProduct)) {
            return false;
        }
        return productRepository.updateProduct(updatedProduct, originalProduct);
    }

    private boolean isProductValid(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }

        if (product.getSupplier() == null || product.getSupplier().isBlank()) {
            throw new IllegalArgumentException("El proveedor del producto no puede ser nulo o vacío");
        }

        if (product.getCategoryId() <= 0) {
            throw new IllegalArgumentException("El ID de categoría debe ser mayor a cero");
        }

        if (product.getId() <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor a cero");
        }

        return true;
    }
}