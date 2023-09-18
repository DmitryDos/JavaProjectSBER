abstract public class Furniture implements FurnitureInterface{

    // abstract - нельзя создавать класс.
    protected int width;
    protected int height;

    public Furniture(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int area(){
        return width * height;
    }
}
