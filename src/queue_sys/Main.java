package queue_sys;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {
        Queue<User> q = new LinkedList<>();
        ArrayList<Charger> chargers = new ArrayList<>();

        chargers.add(new Charger());
        chargers.add(new Charger());

        //check for when there is available charger
        //run for set time of an hour
        while (true) {
            for (Charger c : chargers) {
                if (c.checkAvailability()){
                    User user = startCharging(c);
                    int countdown = user.getTime();
                    // start a timer for charging, especially for each charger
                }
            }
        }
    }
    public static void addUser(String name, int time, int current){
        User user = new User(name, time, current);
        q.add(user);
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

    public static Queue<User> q;
    public static ArrayList<Charger> chargers;
}
