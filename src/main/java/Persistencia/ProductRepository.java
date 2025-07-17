package Persistencia;

import entities.Product;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import org.bson.Document;

public class ProductRepository {

    private final MongoDatabase database;
    private final MongoCollection<Document> productCollection;

    public ProductRepository() {
        var client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("AbarroMaxWeb");
        productCollection = database.getCollection("Products");
    }

    public ArrayList<Product> getProducts() throws Exception {
        var productsDocuments = productCollection.find();
        ArrayList<Product> products = new ArrayList<>();
        try {
            for (Document doc : productsDocuments) {
                var product = Product.fromDocument(doc);
                products.add(product);
            }
            return products;
        } catch (Exception exe) {
            throw new Exception("Error al obtener productos: " + exe.getMessage());
        }
    }

    public boolean addProduct(Product product) throws Exception {
        try {
            var document = product.toDocument();
            productCollection.insertOne(document);
            return true;
        } catch (Exception exe) {
            throw new Exception("Error al agregar producto: " + exe.getMessage());
        }
    }

    public boolean updateProduct(Product product, Product originalProduct) throws Exception {
        try {
            productCollection.updateOne(
                Filters.eq("id", originalProduct.getId()),
                Updates.combine(
                    Updates.set("id", product.getId()),
                    Updates.set("name", product.getName()),
                    Updates.set("categoryId", product.getCategoryId()),
                    Updates.set("supplier", product.getSupplier())
                )
            );
            System.out.println("Producto actualizado correctamente");
            return true;
        } catch (Exception exe) {
            throw new Exception("Error al actualizar producto: " + exe.getMessage());
        }
    }
}
