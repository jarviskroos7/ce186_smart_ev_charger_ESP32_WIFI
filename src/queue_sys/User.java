package queue_sys;

public class User {

    public User(String name, int time, int current){
        this.name = name;
        this.time = time;
        this.current = current;
        this.power = current*current*240;
        this.charger = null;
        this.spot = null;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Charger getCharger() {
        return charger;
    }

    /***
     * Once it has been determined that a spot and charger is available, set the charger for this user.
     * This would also require the charger state be changed.
     * @param charger
     */
    public void setCharger(Charger charger) {
        this.charger = charger;
        this.charger.setState(ChargerState.ACTIVE);
    }

    public Spot getSpot() {-
        return spot;
    }

    /***
     * Once it has been determined that a user can pull into a spot, set the spot for this user.
     * This would require spot to also be changed.
     * @param spot
     */
    public void setSpot(Spot spot) {
        this.spot = spot;
        this.spot.setAvailable(false);

    }

    private String name;
    private int time;
    private int current;
    private int power;
    private Charger charger;
    private Spot spot;
}
