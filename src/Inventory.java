import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Inventory {
    private GameObject[] list;
    private int size;

    public Inventory(int size) {
        this.size = size;
        list = new GameObject[size];
//        GameObject ghost =new GameObject(" ", true);
//
//        for (int i =1; i<size;i++ ) {
//            if(list[i]==null) {
//                list[i] = ghost;
//            }
//        }
    }

    public synchronized void addObject(GameObject go) {
        int index = getFirstEmptyIndex();
        if (index == -1) {
            System.out.println("Inventory är fullt");
            return;
        }
        this.list[index] = go;
//        System.out.println("ny print "+this.list[index]);
    }

    public synchronized void putDown(Inventory i2, GameObject go) {
        if (this.list[0] != null) {
            i2.addObject(go);
            this.list[0] = null;
        } else {
            System.out.println("Inventory is empty.");
        }
    }

    public synchronized void pickUp(Inventory i2, GameObject go) {
//        System.out.println("test "+go);
        boolean hasMoved = false;
        if (go.isMoveable() && i2.list[0] == null) {
//            System.out.println("test2");
            i2.addObject(go);
            hasMoved = true;
        } else {
            System.out.println("Non movable object.");
        }
//        System.out.println("test3");
        if (hasMoved) {
//            System.out.println("test4");
            this.list = Arrays.stream(this.list)
                    .filter(x -> x != go)
                    .collect(Collectors.toList())
                    .toArray(GameObject[]::new);
//            System.out.println(Arrays.toString(this.list));
        }
    }

    public synchronized void playerpickUp(Inventory playerInv, GameObject go) {
        System.out.println("Room inv before"+this.toString());
        System.out.println("Player inv before"+playerInv.toString());
        System.out.println("Object before"+go);
        boolean hasMoved = false;
        if (go.isMoveable() /*&& playerInv.list[0] == null*/) {
//            System.out.println("test2");
            playerInv.addObject(go);
            hasMoved = true;
        } else {
            System.out.println("Non movable object.");
        }
//        System.out.println("test3");
        if (hasMoved) {
//            System.out.println("test4");
            this.list = Arrays.stream(this.list)
                    .filter(x -> x != go)
                    .collect(Collectors.toList())
                    .toArray(GameObject[]::new);
//            System.out.println(Arrays.toString(this.list));
            System.out.println("Room inv after"+this.toString());
            System.out.println("Player inv after"+playerInv.toString());
        }


    }
    public synchronized GameObject getMoveableObject() {
        GameObject[] moveableList = Arrays.stream(this.list)
                .filter(GameObject::isMoveable)
                .collect(Collectors.toList())
                .toArray(GameObject[]::new);
        int index = (int) (Math.random() * (getFirstEmptyIndex() - 1));
        if (moveableList.length == 1) {
            return moveableList[0];
        } else {
            return moveableList[index];
        }


    }
    public GameObject getRandomObject() {



        int index = (int) (Math.random() * (getFirstEmptyIndex() - 1));
        if (this.list.length == 1) {
            return this.list[0];
        } else {
            return this.list[index];
        }
    }

    public synchronized GameObject getObject(int index) {
        return this.list[index];
    }

    public String toString() {
        //return Arrays.toString(this.list);
        GameObject[] showList = Arrays.stream(this.list)
                .filter(Objects::nonNull)
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
