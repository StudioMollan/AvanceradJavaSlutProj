public class Player {
    String name;
    Inventory inventory;
    int position;
    Gui gui;
    public Player (String name){
        this.name = name;
        this.inventory = new Inventory(5);

    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }
}

