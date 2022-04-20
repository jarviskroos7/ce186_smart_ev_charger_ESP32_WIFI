package queue_sys;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    /***
     * Creates the queue and the chargers.
     * Should only be run once
     * @throws Exception
     */
    public static void main() throws Exception {
        Queue<User> q = new LinkedList<>();
        ArrayList<Charger> chargers = new ArrayList<>();

        chargers.add(new Charger());
        chargers.add(new Charger());

    }

    /***
     * Create user object from the parameters and add to queue.
     * Checks if there is available charger and spot.
     * @param name - string name of user
     * @param time - time that user will be using charger
     * @param current - current that user wants to charge at
     */
    public static void addUser(String name, int time, int current){
        User user = new User(name, time, current);
        q.add(user);
        checkChargers();
    }

    public static void checkChargers() throws Exception {
        if (!(q.isEmpty())) {
            for (Charger c : chargers) {
                if (c.checkAvailability()) {
                    User user = startCharging(c);
                    chargeTimer(c, user.getSpot(), user.getTime());
                }
            }
        }
    }
    /***
     * User is able to start charging. Requires setting charger and spot for user.
     * Charger and spot status must also be updated.
     * @param charger
     */
    public static User startCharging(Charger charger) throws Exception {
        Spot spot = charger.availableSpot();
        User user = q.remove();

        user.setCharger(charger);
        user.setSpot(spot);

        charger.setState(ChargerState.ACTIVE);
        charger.setTime(user.getTime());
        spot.setAvailable(false);

        return user;
    }
    public static void chargeTimer(Charger charger, Spot spot, int time){
        Timer timer = new Timer();
        TimerTask chargingTasks = new TimerTask() {
            public void run() {
                endCharging(charger, spot);
            }
        };
        timer.schedule(chargingTasks, time);
    }


    public static void endCharging(Charger charger, Spot spot) throws Exception {
        charger.setState(ChargerState.INACTIVE);
        checkChargers();
        Timer delayTimer = new Timer();
        TimerTask delayTask = new TimerTask(){
            public void run(){
                spot.setAvailable(true);
            }
        };
        delayTimer.schedule(delayTask, 30);
    }

    public static Queue<User> q;
    public static ArrayList<Charger> chargers;
}
