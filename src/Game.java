public class Game {
    private Gui gui;
    private Room personalRum, förstaKlass, resturangVagn, andraKlass;
    private Room[] map ;

    public Game(){
        //Skapa rum!
        personalRum = new Room("Tågets personalrum","Personalens privata kläder på hängare. Ett pentry med ett litet bord och några stolar.");
        förstaKlass = new Room("Vagnen för Första klass", "Flådigat inrett med röd sammet och takkronor. Gamla snobbar spelar bridge, röker och dricker Pastisse.");
        resturangVagn = new Room("Resturangvagnen", "En bardisk samt ett antal bås med bord och soffor. Några resanden äter frallor och andra röker cigaretter och dricker pilsner.");
        andraKlass = new Room("Vagnen för Andra klass", "En vagn för andra klassens medborgare. Dassiga tapeter och nedsuttna säten. En gumma rensar fisk i ett hörn.");
        map = new Room[4];
        map[0] = personalRum;
        map[1] = förstaKlass;
        map[2] = resturangVagn;
        map[3] = andraKlass;

        //Gameobjects
        //Personalrum
        GameObject tomCollins = new GameObject("Tom Collins", true);
        GameObject piska = new GameObject("Piska",true);
        Container coffin = new Container("Likkista", false, true);
        Container chest = new Container("Kista", false, true);
        personalRum.addObject(piska);
        personalRum.addObject(coffin);
        personalRum.addObject(tomCollins);
        förstaKlass.addObject(chest);
        Person KjellOlofFäldt = new Person("Kjell-Olof Fäldt",0);
        personalRum.addNpc(KjellOlofFäldt);
        KjellOlofFäldt.getInventory().addObject(tomCollins);

        System.out.println(KjellOlofFäldt);
        Inventory inventory = new Inventory(5);
        System.out.println(inventory);
        inventory.addObject(piska);
        inventory.addObject(tomCollins);
        inventory.addObject(tomCollins);
        inventory.addObject(tomCollins);
        inventory.addObject(tomCollins);
        inventory.addObject(tomCollins);
        System.out.println(inventory);

        /*
        System.out.println(tomCollins);
        System.out.println(tomCollins.isMoveable());
        System.out.println(piska);
        System.out.println(piska.isMoveable());
*/
        //[room1, room2]
        //Starta guit!
        this.gui = new Gui();
        //System.out.println(map[1]);
        //gui.setShowRoom(map[1].toString());
        int position = 0;
        gui.setShowRoom(map[position].toString());
//[r1, r2, r3]
        while (true) {

            String command = gui.getCommand();
            if (!command.equals("-1")) {

                if (command.equals("1")) {
                    position = 0;

                }
                if (command.equals("2")) {
                    position = 1;
                }

            }

            gui.setShowRoom(map[position].toString());
            gui.setShowInventory(inventory);
            if (map[position].getPersons() != null) {
                gui.setPerson(map[position].getPersons());
            }

        }

        /*
        while (true) {
            String command = gui.getCommand();
            System.out.println(command);
        }*/
    }



}
