/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * The purpose of this program is to simulate a keypad at apartment entrance.
 */
package program3;

public class Tenant {
    private int aptNo;
    private int pin;
    private String name;
    
    public Tenant() {
        aptNo = -1;
        pin = -1;
        name = "";
    }
    
    public Tenant(int apt, int pin, String name) {
        aptNo = apt;
        this.pin = pin;
        this.name = name;
    }

    public int getAptNo() {
        return aptNo;
    }

    public void setAptNo(int aptNo) {
        this.aptNo = aptNo;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
