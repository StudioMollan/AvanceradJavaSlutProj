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

    public void addNpc(Person person) {
//        int length = (int) Arrays.stream(this.person)
//                .filter(Objects::nonNull).count();
//        try {
//            Array.set(this.person, length+1, person);
//        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
//            e.printStackTrace();
//        }

        this.person[0] = person; //TODO Fixa fler personer
    }

    public Person getPersons() { return this.person[0]; }

    public void addObject(GameObject go) {this.inventory.addObject(go); }
    public String toString() { return name + " : " + description + "\n" + inventory; }


}
