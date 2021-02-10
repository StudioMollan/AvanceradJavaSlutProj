public abstract class Npc implements Runnable {
    String name;
    Inventory inventory;
    int position;
    String fras;
    Gui gui;
    Room room;

    public Npc(String name, int position, String fras) {
        this.fras = fras;
        this.name = name;
        this.position = position;
        this.inventory = new Inventory(2);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String toString() {
        return this.name + " is carrying " + this.inventory;
    }

    public synchronized void move(int x) {
        this.position = x;
    }

    @Override
    public void run() {
        int x = (int) (Math.random() * 3);
        System.out.println("Random int: "+x);
        if(x==0){
        //om 1 == move
        int rand = (int) (Math.random() * 100) + 1;
        System.out.println("-------------");
        System.out.println("random " + rand+ " " + this.name);
        System.out.println("before " + this.name + " " + this.position);
        if (rand < 50 && this.position != 0) {
            move(this.position--);
            System.out.println("After <50 " + this.name + " " + this.position);
            System.out.println("inventory " + this);
        }else if (rand > 50 && this.position != 3) {
            move(this.position++);
            System.out.println("After >50 " + this.name + " " + this.position);
            System.out.println("inventory " + this);
        }

        }else if(x==1){
            System.out.println("Fras " + this.name + " " + this.fras);
            //gui.setShowPersonsFras((Person)this);
        }else if (x==2){


            this.getInventory().addObject(o);
            System.out.println("IF random int==2 ");
        }else{
            System.out.println("IF random int==3 ");
        }


    }
}
