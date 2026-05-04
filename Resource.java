package resource;

public class Resource {
    private String name;
    private int quantity;

    public Resource(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void display() {
        System.out.println("Resource: " + name + " | Quantity: " + quantity);
    }
}
