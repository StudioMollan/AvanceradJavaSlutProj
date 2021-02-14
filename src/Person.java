public class Person extends Npc{

    Inventory inventory;

    public Person(String name, int position, String fras, Room[] map, Gui gui ) {
        super(name, position,fras, map, gui);
        this.name =name;
        this.position = position;
        this.inventory = new Inventory(1);


    }








}
