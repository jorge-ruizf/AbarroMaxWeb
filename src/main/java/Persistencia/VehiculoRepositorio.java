package Persistencia;

import entities.Product;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import org.bson.Document;

public class VehiculoRepositorio {

    private final MongoDatabase database;
    private final MongoCollection<Document> productCollection;

    public VehiculoRepositorio() {
        var client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("AbarroMaxWeb");
        productCollection = database.getCollection("Products");
    }

    public ArrayList<Product> getProducts() throws Exception {
        var productsDocuments = productCollection.find();
        ArrayList<Product> products = new ArrayList<>();
        try {
            for (Document doc : productsDocuments) {
                var vehiculo = Product.fromDocument(doc);
                products.add(vehiculo);
            }

            return products;
        } catch (Exception exe) {
            throw new Exception("Ha ocurrido un error, contacte al Admin: " + exe.getMessage());
        }
    }
    
    public boolean idExiste(int id) {
        Document doc = productCollection.find(Filters.eq("id", id)).first();
        return doc != null;
    }


    public boolean addProduct(Product product) throws Exception {
        try {
            var document = product.toDocument();
            productCollection.insertOne(document);
            return true;
        } catch (Exception exe) {
            throw new Exception("Ha ocurrido un error, contacte al Admin: " + exe.getMessage());
        }
    }

    public boolean updateProduct(Product product, Product productOrigin) throws Exception {
        try {
            var result = productCollection.updateOne(
                Filters.eq("id", productOrigin.getId()),
                Updates.combine(
                    Updates.set("id", product.getId()),
                    Updates.set("name", product.getName()),
                    Updates.set("categoryId", product.getCategoryId()),
                    Updates.set("supplier", product.getSupplier())
                )
            );
            return result.getModifiedCount() > 0;
        } catch (Exception exe) {
            throw new Exception("Ha ocurrido un error, contacte al Admin: " + exe.getMessage());
        }
    }


    public boolean deleteProductById(int id) throws Exception {
        try {
            var result = productCollection.deleteOne(Filters.eq("id", id));
            return result.getDeletedCount() > 0;
        } catch (Exception exe) {
            throw new Exception("Error al eliminar producto: " + exe.getMessage());
        }
    }


}
