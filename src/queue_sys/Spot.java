package queue_sys;

public class Spot {

    public Spot(Charger charger){
        this.charger = charger;
        this.time = charger.getTime()+30;
        this.available = true;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    private Charger charger;
    private int time;
    private boolean available;
}
