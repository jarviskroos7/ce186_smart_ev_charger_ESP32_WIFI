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
     */
    public static void main() {
        Queue<User> q = new LinkedList<>();
        ArrayList<Charger> chargers = new ArrayList<>();
        int queueTime = 0;

        chargers.add(new Charger());
        chargers.add(new Charger());

    }

    /***
     * Create user object from the parameters and add to queue.
     * Checks if there is available charger and spot.
     * @param name - string name of user
     * @param time - time (in minutes) that user will be using charger
     * @param current - current that user wants to charge at
     */
    public static void addUser(String name, int time, int current){
        User user = new User(name, time, current);
        q.add(user);
        checkChargers();
    }

    /***
     * Checks for available chargers and spots.
     * If there is, user is popped off queue and charging is started.
     * Charging timer for this user is created.
     */
    public static void checkChargers() {
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
    public static User startCharging(Charger charger) {
        Spot spot = charger.availableSpot();
        User user = q.remove();

        user.setCharger(charger);
        user.setSpot(spot);

        charger.setState(ChargerState.ACTIVE);
        spot.setAvailable(false);

        return user;
    }

    /***
     * Timer is created for current charging session.
     * Once timer ends, charging is ended and triggers function to set up availability for next user.
     * @param charger
     * @param spot
     * @param time
     */
    public static void chargeTimer(Charger charger, Spot spot, int time){
        Timer timer = new Timer();
        TimerTask chargingTasks = new TimerTask() {
            public void run() {
                endCharging(charger, spot);
            }
        };
        timer.schedule(chargingTasks, time*60*1000);
    }

    /***
     * Ends current charging session for given charger.
     * Delays availability of spot, during which next user can be popped off queue and pulled into other spot.
     * Note: This function loops back to checkChargers to ensure users can be popped off queue.
     * @param charger
     * @param spot
     */
    public static void endCharging(Charger charger, Spot spot) {
        charger.setState(ChargerState.INACTIVE);
        checkChargers();
        Timer delayTimer = new Timer();
        TimerTask delayTask = new TimerTask(){
            public void run(){
                spot.setAvailable(true);
            }
        };
        delayTimer.schedule(delayTask, 30*1000);
    }

    /***
     * Returns how long the queue will take.
     * @return
     */
    public static int queueTime(){
        int time = 0;
        for (User user: q) {
            time += user.getTime();
        }
        return time;
    }

    /***
     * Returns how many people are in the queue.
     * @return
     */
    public static int getQueueLength(){
        return q.size();
    }


    public static Queue<User> q;
    public static ArrayList<Charger> chargers;
    public static int queueTime;
}
