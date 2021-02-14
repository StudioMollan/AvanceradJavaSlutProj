import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {
    private Gui gui;
    private Room personalRum, förstaKlass, resturangVagn, andraKlass;
    private Room[] map;

    public Game() {


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
        Person kjellOlofFaeldt = new Person("Kjell-Olof Fäldt", 0, "Jag heter Kjell och Olof, samtidigt.", this.map, this.gui);
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
        Person boLundgren = new Person("Bo Lundgren", 1, "Högern bitches!", this.map, this.gui);
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
        Person birgitFriggebo = new Person("Birgit Friggebo", 2, "Friggebo bitches!", this.map, this.gui);
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
        Person goranGreider = new Person("Göran Greider", 3, "Vänstern bitches!", this.map, this.gui);
        goranGreider.getInventory().addObject(tuting);
        andraKlass.addNpc(goranGreider);
        System.out.println(goranGreider);
        System.out.println(goranGreider.position);

        //Start all threads
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(kjellOlofFaeldt, 6021, 9085, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(boLundgren, 5503, 8500, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(birgitFriggebo, 4303, 7802, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(goranGreider, 3689, 6723, TimeUnit.MILLISECONDS);

        //Skapa NPC
        Npc bartender = new Npc("Bartender", 2, "Har någon sett min projektor?", this.map, this.gui) {
        };
        GameObject diabild = new GameObject("diabild", true);
        bartender.getInventory().addObject(diabild);
        pool.scheduleAtFixedRate(bartender, 10, 10, TimeUnit.SECONDS);


        //Skapa Player
        GameObject projektor = new GameObject("projektor", true);
        Player player1 = new Player("Player 1", 2, this.map, this.gui);
        System.out.println(player1);
        player1.getInventory().addObject(tuting);
        player1.getInventory().addObject(projektor);
        System.out.println(player1);


        //Starta guit!
        this.gui = new Gui();
        gui.setShowRoom(map[player1.position].toString());

        while (true) {
            String command = gui.getCommand();
            int newPosition = 0;
            if (command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4")) {
                newPosition = Integer.parseInt(command) - 1;
            }
            if (command.equals("1") && isAdjacent(player1.position, newPosition)) {
                player1.position = 0;
            }
            if (command.equals("2") && isAdjacent(player1.position, newPosition)) {
                player1.position = 1;
            }
            if (command.equals("3") && isAdjacent(player1.position, newPosition)) {
                player1.position = 2;
            }
            if (command.equals("4") && isAdjacent(player1.position, newPosition)) {
                player1.position = 3;
            }
            if (command.equals("pick up")) {
                gui.setInput("Vad vill du plocka upp? Skriv index för saken där den ligger i rummets inventory");
//                Scanner in = new Scanner(System.in);
//                System.out.println("Room before Pickup: "+map[player1.position].getInventory().toString());
//                System.out.println("Player before Pickup: "+player1.getInventory().toString());
                map[player1.position].getInventory().playerpickUp(player1.getInventory(), map[player1.position].getInventory().getRandomObject());
//                in.close();
//                System.out.println("Room after Pickup: "+map[player1.position].getInventory().toString());
//                System.out.println("Player before Pickup: "+player1.getInventory().toString());
            }
            if (command.equals("drop")) {

            }
            gui.setShowRoom(map[player1.position].toString());
            gui.setShowInventory(player1.getInventory());
            if (map[player1.position].getPersons() != null) {
                gui.setPerson(map[player1.position].getPersons());
            }

        }


    }

    private synchronized boolean isAdjacent(int position, int newPosition) {
        boolean b;
        if (Math.abs(position - newPosition) > 1) {
            b = false;
        } else {
            b = true;
        }
        return b;
    }


}
