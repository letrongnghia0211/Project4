package letrongnghia.aprotrain.foodapp.Database;

public class Food {
    int Id;
    String title, context;
    float price;

    public Food(int id) {
        Id = id;
    }

    public Food(int id, String title, String context, float price) {
        Id = id;
        this.title = title;
        this.context = context;
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
