public class Customer {
    private int maxSize;

    public Customer(int maxSize){
        this.maxSize = maxSize;
    }

    public void buy(Object furniture){
        if(furniture instanceof Table){
            Table t = (Table) furniture;
        }

        if(furniture instanceof Chair){
            Chair c = (Chair) furniture;
        }
    }
}
