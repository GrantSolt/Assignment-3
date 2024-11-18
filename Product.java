public class Product implements Comparable<Product>{
    private String ID;
    private String Name;
    private String category;
    private String Price;
    public Product(String ID, String name, String category, String price) {
        this.ID = ID;
        this.Name = name;
        this.category = category;
        this.Price = price;
    }
    public String getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getCategory() {
        return category;
    }
    public String getPrice() {
        return Price;
    }
    public void printInfo(){
        System.out.println("ID: " + ID);
        System.out.println("Name: " + Name);
        System.out.println("Category: " + category);
        System.out.println("Price: " + Price);
    }
    @Override
    public int compareTo(Product other) {
        return this.ID.compareTo(other.ID);
    }

}
