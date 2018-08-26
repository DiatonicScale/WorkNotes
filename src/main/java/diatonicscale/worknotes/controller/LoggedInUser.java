/**
 * User: DiatonicScale
 * Date: 26.08.2018
 */

package diatonicscale.worknotes.controller;

public class LoggedInUser {
    private static int id = 0;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        LoggedInUser.id = id;
    }
}
