public class Player {
    String name;
    Inventory inventory;
    int position;
    Gui gui;
    Room[] map;
    public Player (String name, int position, Room[] map, Gui gui ){

        this.name = name;
        this.position = position;
        this.inventory = new Inventory(5);
        this.map = map;
        this.gui = gui;

    }
    public Inventory getInventory() {
        return this.inventory;
    }
    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }
}

