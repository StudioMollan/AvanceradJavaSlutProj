import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {
    private static Gui gui;
    private static Room personalRum, förstaKlass, resturangVagn, andraKlass;
    private static Room[] map;

    public Game() {

        //Skapa Player

        Player player = new Player("You");

        //Skapa rum
        personalRum = new Room("Tågets personalrum", "Personalens privata kläder på hängare. Ett pentry med ett litet bord och några stolar. Nåt är 'off' här inne...");
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
        GameObject piska = new GameObject("Piska", true);
        GameObject kvast = new GameObject("Kvast", true);
        GameObject dvd = new GameObject("DVD-film", true);
        Container skap = new Container("Skåp", false, true);
        personalRum.addObject(piska);
        personalRum.addObject(kvast);
        personalRum.addObject(skap);
        personalRum.addObject(dvd);
        Person kjellOlofFaeldt = new Person("Kjell-Olof Fäldt", 0, "Jag heter Kjell och Olof, samtidigt.");
        kjellOlofFaeldt.getInventory().addObject(tomCollins);
        personalRum.addNpc(kjellOlofFaeldt);
        System.out.println(kjellOlofFaeldt);
        System.out.println(kjellOlofFaeldt.position);

        //FörstaKlass
        GameObject pergament = new GameObject("Pergament", true);
        Container chest = new Container("Kista", false, true);
        GameObject di = new GameObject("Dagens Industri", true);
        chest.getInventory().addObject(pergament);
        förstaKlass.addObject(chest);
        förstaKlass.addObject(di);
        Person boLundgren = new Person("Bo Lundgren",1, "Högern bitches!");
        boLundgren.getInventory().addObject(tomCollins);
        förstaKlass.addNpc(boLundgren);
        System.out.println(boLundgren);
        System.out.println(boLundgren.position);

        //ResturangVagn
        GameObject termos = new GameObject("Termos", true);
        GameObject cava = new GameObject("Cava", true);
        GameObject tuting = new GameObject("Tuting", true);
        Container bar = new Container("Barskåp", false, false);
        bar.getInventory().addObject(cava);
        bar.getInventory().addObject(termos);
        bar.getInventory().addObject(tuting);
        resturangVagn.addObject(bar);
        Person birgitFriggebo = new Person("Birgit Friggebo", 2, "Friggebo bitches!");
        birgitFriggebo.getInventory().addObject(cava);
        resturangVagn.addNpc(birgitFriggebo);
        System.out.println(birgitFriggebo);
        System.out.println(birgitFriggebo.position);

        //AndraKlass
        Key nyckel = new Key("Nyckel", true, chest);
        GameObject gimp = new GameObject("Gimp", false);
        Container coffin = new Container("Likkista", false, false);
        GameObject tant = new GameObject("Gammal tant", true);
        GameObject katt = new GameObject("Tantens katt", true);
        andraKlass.addObject(coffin);
        coffin.getInventory().addObject(nyckel);
        coffin.getInventory().addObject(gimp);
        andraKlass.addObject(tant);
        andraKlass.addObject(katt);
        Person goranGreider = new Person("Göran Greider", 3, "Vänstern bitches!");
        goranGreider.getInventory().addObject(tuting);
        andraKlass.addNpc(goranGreider);
        System.out.println(goranGreider);
        System.out.println(goranGreider.position);

        //Start all threads
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(kjellOlofFaeldt, 6,9, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(boLundgren, 5,8, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(birgitFriggebo, 4,7, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(goranGreider, 3,6, TimeUnit.SECONDS);

        //Skapa NPC
        Npc bartender = new Npc("Bartender", 2, "Har någon sett min projektor?") {};
        GameObject diabild = new GameObject("diabild", true);
        bartender.getInventory().addObject(diabild);
        pool.scheduleAtFixedRate(bartender, 10,10, TimeUnit.SECONDS);


        Inventory inventory = new Inventory(5);
        GameObject projektor = new GameObject("projektor", true);
        System.out.println(inventory);
        inventory.addObject(projektor);
        inventory.addObject(tuting);
        System.out.println(inventory);

        //Starta guit!
        this.gui = new Gui();
        //System.out.println(map[1]);

        int position = 1;
        gui.setShowRoom(map[position].toString());
        //[r1, r2, r3]

        while (true) {
            String command = gui.getCommand();
            int newPosition = 0;
            if (command.equals("1")||command.equals("2")||command.equals("3")||command.equals("4")) {
                newPosition = Integer.parseInt(command) - 1;
            }
            if (command.equals("1") && isAdjacent(position, newPosition)) {
                position = 0;
            }
            if (command.equals("2") && isAdjacent(position, newPosition)) {
                position = 1;
            }
            if (command.equals("3") && isAdjacent(position, newPosition)) {
                position = 2;
            }
            if (command.equals("4") && isAdjacent(position, newPosition)) {
                position = 3;
            }
            gui.setShowRoom(map[position].toString());
            gui.setShowInventory(inventory);
            if (map[position].getPersons() != null) {
                gui.setPerson(map[position].getPersons());
            }
        }


    }

    private boolean isAdjacent(int position, int newPosition) {
        boolean b;
        if (Math.abs(position - newPosition) > 1) {
            b = false;
        } else {
            b = true;
        }
        return b;
    }


}
