import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*Extremt enkelt Gui för att kunna komma igång.
Snygga gärna till/gör ett eget. Men tänk på att gör GUI:s INTE är ett kursmoment - så fastna inte här!
 */


public class Gui extends JFrame {

    private JPanel panel;
    private JTextArea showRoom;
    private JTextArea showPersons;
    private JTextField input;
    private JTextArea talk;
    private JTextArea inventory;
    private String command;
    private boolean gotCommand;
    private JButton button;

    public Gui() {
        this.gotCommand = false;
        this.command = "";
        this.setTitle("Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpElements();
        setUpPanel();
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
    }

    //Returnera det senaste commitade kommandot
    public String getCommand() {
        if (this.gotCommand) {
            //System.out.println(this.command);
            return this.command;
        }
        return "-1";

    }

    //Här kan man updatera respektive fält:
    public synchronized void setShowRoom(String roomDescription) {
        this.showRoom.setText(roomDescription);
    }

    public synchronized void setInput(String newInput) {
        this.input.setText(newInput);
    }

    public synchronized void setTalk(String newInput) {
        this.talk.setText(newInput);
    }

    public synchronized void setShowPersonsFras(Person person) {
        this.showPersons.setText(person.fras);
    }

    public synchronized void setShowInventory(Inventory i) {
        this.inventory.setText("Player Inventory: " + i.toString());
    }

    public synchronized void setShowPersons(Person person) {
        this.showPersons.setText("Person inventory: " + person.toString());
    }

    //Add person to room
    public synchronized void setPerson(Person p) {
        this.showPersons.setText("Person: " + p.toString());
    }

//Nedantåenda spaghetti är inte vacker...


//    public void gotCommand() {
//        this.gotCommand = false;
//    }
//

    private void setUpPanel() {
        this.panel.add(showPersons);
        this.panel.add(showRoom);
        this.panel.add(input);
        this.panel.add(talk);
        this.panel.add(inventory);
        this.panel.add(button);

    }

    private void setUpElements() {
        this.panel = new JPanel(new GridLayout(4, 3));
        this.showRoom = new JTextArea("Room: ");
        this.showRoom.setLineWrap(true);
        this.showRoom.setWrapStyleWord(true);
        this.showPersons = new JTextArea("Persons");
        this.showPersons.setLineWrap(true);
        this.showPersons.setWrapStyleWord(true);
        this.inventory = new JTextArea("Inventory");
        this.inventory.setLineWrap(true);
        this.inventory.setWrapStyleWord(true);
        this.input = new JTextField("Give command");
        this.talk = new JTextArea("talk");
        this.talk.setLineWrap(true);
        this.talk.setWrapStyleWord(true);
        this.showPersons.setEditable(false);
        this.showRoom.setEditable(false);
        this.inventory.setEditable(false);
        this.talk.setEditable(false);

        ActionListener inputListener = e -> {
            this.command = input.getText();
            System.out.println("click");
            this.gotCommand = true;
        };

        input.addActionListener(inputListener);

        this.button = new JButton("commit");
        this.button.addActionListener(inputListener);

    }

}









