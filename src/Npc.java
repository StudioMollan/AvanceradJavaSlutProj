public abstract class Npc implements Runnable {
    String name;
    Inventory inventory;
    int position;
    String fras;
    Gui gui;
    Room[] map;

    public Npc(String name, int position, String fras, Room[] map, Gui gui) {
        this.fras = fras;
        this.name = name;
        this.position = position;
        this.inventory = new Inventory(1);
        this.map = map;
        this.gui = gui;
    }

    public synchronized Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getFras() {
        return fras;
    }

    public String toString() {
        return this.name + " is carrying " + this.inventory;
    }

    public synchronized void move(int x) {
        this.position = x;
    }
    public int counter =0;
    @Override
    public void run() {
        int x = (int) (Math.random() * 4);
        counter += 1;
        System.out.println("---   ---  ---   --- " +counter+  " ---   ---   ---   ---");
        System.out.println("Random int: " + x);

        // x==0 => Move
        if (x == 0) {
            int rand = (int) (Math.random() * 100) + 1;
            System.out.println("-----MOVE-----");
            System.out.println("before move " + this + ". Current room is: " + map[position].getName());
            if (rand < 50 && this.position != 0) {
                move(this.position -= 1);
                System.out.println(" <50: " + this);
            } else if (rand > 50 && this.position != 3) {
                move(this.position += 1);
                System.out.println(" >50: " + this);
            } else if(this.position==0){
                move(this.position += 1);
                System.out.println(" this.pos == 0   " + this);
            } else if(this.position==3){
                move(this.position -= 1);
                System.out.println(" this.pos == 3   " + this);
            }
            System.out.println("After move " + this + ". Current room is: " + map[position].getName());
        }
        // x==1 => skriv ut fras
        else if (x == 1) {
            System.out.println("-----FRAS-----");
            System.out.println("Fras: " + this.name + " säger:  " + this.fras);


            this.gui.setTalk(this.fras);
            System.out.println("test");
        }

        // x==2 => Plocka upp object
        else if (x == 2) {
            System.out.println("-----PICK UP OBJECT-----");
            System.out.println("Person before pick up: " + this);
            System.out.println("Room before pick up: " + map[this.position].getInventory().toString());

            map[this.position].getInventory().pickUp(this.inventory, map[this.position].getInventory().getRandomObject());

            System.out.println("Person after pick up: " + this);
            System.out.println("Room after pick up: " + map[this.position].getInventory().toString());

        }
        // x==3 => Lägg ned object
        else {
            System.out.println("-----PUT DOWN OBJECT-----");
            System.out.println("Person before put down:" + this);
            System.out.println("Room before put down: " + map[this.position].getInventory().toString());

            this.inventory.putDown(map[this.position].getInventory(), this.inventory.getRandomObject());

            System.out.println("Person after put down:" + this);
            System.out.println("Room after put down: " + map[this.position].getInventory().toString());
            System.out.println("IF random int==3. Lägg ned ");
        }
            System.out.println();

    }
}
