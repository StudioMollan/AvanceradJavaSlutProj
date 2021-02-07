public class Room {
    private String name;
    private String description;
    private Inventory inventory;
    private Person[] person;

    public Room(String roomName, String roomDescription){
        this.person = new Person[5];
        this.name = roomName;
        this.description= roomDescription;
        this.inventory = new Inventory(10);
    }
    public void addNpc(Person person) {

        this.person[0] = person; //TODO Fixa fler personer
    }

    public Person getPersons(){
        return this.person[0];
    }
    public void addObject(GameObject go){
        this.inventory.addObject(go);
    }
    public String toString(){
        return name+" : "+description +"\n" +inventory;
    }


}
