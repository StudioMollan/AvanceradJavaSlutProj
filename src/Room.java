import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private String description;
    private Inventory inventory;
    private Person[] person;


    public Room(String roomName, String roomDescription) {
        this.person = new Person[5];
        this.name = roomName;
        this.description = roomDescription;
        this.inventory = new Inventory(10);
    }

    public String getName() {
        return name;
    }

    public synchronized void addNpc(Person person) {
        this.person[0] = person;
    }

    public Person getPersons() { return this.person[0]; }
    public Inventory getInventory() {
        return this.inventory;
    }
    public synchronized void addObject(GameObject go) {this.inventory.addObject(go); }
    public String toString() { return name + " : " + description + "\n" + inventory; }


}
