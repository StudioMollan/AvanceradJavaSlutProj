import java.util.Arrays;
import java.util.stream.Collectors;

public class Inventory {
    private GameObject[] list;
    private int size;
    public Inventory( int size) {
        this.size = size;
        list = new GameObject[size];
    }

    public void addObject(GameObject go) {
        int index = getFirstEmptyIndex();

        if (index == -1) {
            System.out.println("Inventory är fullt");
            return;
        }
        this.list[index] = go;
    }

    public void moveObject(Inventory i2, GameObject go) {
        //if (!isObjectHere(go)){ //Felmeddelande}
        i2.addObject(go);
        //this.removeObject(go);
    }
    public void removeObject (/*Inventory inv,*/ GameObject go){

    }

    public String toString() {
        //return Arrays.toString(this.list);
        GameObject[] showList = Arrays.asList(this.list)
                .stream()
                .filter(x -> x != null)
                .collect(Collectors.toList())
                .toArray(GameObject[]::new);
        return Arrays.toString(showList);
    }

    private int getFirstEmptyIndex() {

        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] == null) {
                return i;
            }
        }
        return -1;


    }
}
