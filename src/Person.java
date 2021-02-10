public class Person extends Npc{

    Inventory inventory;

    public Person(String name, int position, String fras) {
        super(name, position,fras);
        this.name =name;
        this.position = position;
        this.inventory = new Inventory(2);

    }








}
