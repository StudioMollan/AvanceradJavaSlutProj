public class GameObject {
    private String name;
    private boolean moveable;

    public GameObject(String name ,boolean moveable){
        this.name = name;
        this.moveable = true;
    }
    public boolean isMoveable(){
        return this.moveable;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){
        return this.name;
    }
}
