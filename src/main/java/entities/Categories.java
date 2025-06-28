package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Categories {

    private ArrayList<String> categories;

    public Categories() {
        this.categories = new ArrayList<>();
    }

    public Categories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    // Agregar una categoría directamente (sin preguntar)
    public void addCategory(String category) {
        if (!categories.contains(category)) {
            this.categories.add(category);
        }
    }
}
