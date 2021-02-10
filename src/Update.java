public class Update implements Runnable{
    private Gui gui;
    public Update(Gui gui) {
        this.gui = gui;
    }
    public Gui getGui() {
        return gui;
    }
    public void setGui(Gui gui) {
        this.gui = gui;
    }
//    public void setGuiPerson(Person person) {
//        this.gui.setShowPersons(person);
//    }
    @Override
    public void run() {
    }
}
