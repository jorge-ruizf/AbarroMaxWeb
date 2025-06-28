package entities;

import org.bson.Document;

public class Product {

    private int id;
    private String name;
    private int categoryId;
    private String supplier;

    public Product() {

    }

    public Product(int id, String name, int categoryId, String supplier) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        if (id >= 0) {
            this.id = id;
        } else {
            System.out.println("El nombre no puede estar vacío.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("El nombre no puede estar vacío.");
        }
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        if (categoryId > 0) {
            this.categoryId = categoryId;
        } else {
            System.out.println("ID de categoría inválido.");
        }
    }

    public String getSupplier() {
        return supplier;
    }
    
    public void setSupplier(String supplier) {
        if (!supplier.isEmpty()) {
            this.supplier = supplier;
        } else {
            System.out.println("El supplier no puede estar vacío.");
        }
    }
    public static Product fromDocument(Document doc) {
        int id = doc.getInteger("id", 0); // valor por defecto 0 si no existe
        String name = doc.getString("name");
        int categoryId = doc.getInteger("categoryId", 0);
        String supplier = doc.getString("supplier");

        return new Product(id, name, categoryId, supplier);
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("name", name)
                .append("categoryId", categoryId)
                .append("supplier", supplier);
    }

}
