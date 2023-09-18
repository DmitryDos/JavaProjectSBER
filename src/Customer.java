public class Customer {
    private int maxSize;
    public Customer(int maxSize){
        this.maxSize = maxSize;
    }
    public void buy(FurnitureInterface furniture){
        if(furniture.area() < maxSize){

        }
    }
}
