package queue_sys;

public class Charger {

    public Charger(){
        this.state = ChargerState.INACTIVE;
        this.leftSpot = new Spot(this);
        this.rightSpot = new Spot(this);

    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    /***
     * Check if charger is available for charging based on charger state and spot availability.
     * @return boolean
     */
    public boolean checkAvailability(){
        boolean spotAvailable = leftSpot.isAvailable() || rightSpot.isAvailable();
        if (state.equals(ChargerState.INACTIVE) && spotAvailable){
            return true;
        }
        else{
            return false;
        }
    }

    /***
     * Return the first available spot for this charger.
     * @return Spot
     */
    public Spot availableSpot(){
        if (leftSpot.isAvailable()){
            return this.leftSpot;
        } else {
            return this.rightSpot;
        }
    }

    public ChargerState getState() {
        return state;
    }

    public void setState(ChargerState state) {
        this.state = state;
    }

    public Spot getLeftSpot() {
        return leftSpot;
    }

    public Spot getRightSpot() {
        return rightSpot;
    }

    /***
     * state - whether the charger is available.
     * time - time until the charger is available again.
     * leftSpot - left parking spot for this charger
     * rightSpot - right parking spot for this charger
     */
    private ChargerState state;
    private int time;
    private Spot leftSpot;
    private Spot rightSpot;
}
