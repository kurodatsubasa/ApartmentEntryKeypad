/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * The purpose of this program is to simulate a keypad at apartment entrance.
 */
package program3;

import java.util.ArrayList;

public class ApartmentRecords {

    ApartmentRecords() {
        tenants = new ArrayList<>();
    }

    public void add(String line) {
        try {
            String[] tokens = line.split(" ");
            tenants.add(new Tenant(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Data format error! The following tenant entry "
                    + "could not be added to the records:\n" + line + "\n");
        }
    }

    public String findTenant(String input) {
        try {
            String[] tokens = input.split("#");
            Tenant tenant = findByApt(Integer.parseInt(tokens[0]));
            if (tenant != null && tenant.getPin() == Integer.parseInt(tokens[1])) {
                return "Welcome home, " + tenant.getName();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
           System.out.println(e);
        }
            return "Invalid apartment or PIN number!";
        }

    

    private Tenant findByApt(int apt) {
        for (Tenant tenant : tenants) {
            if (tenant.getAptNo() == apt) {
                return tenant;
            }
        }
        return null;
    }

    public ArrayList<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(ArrayList<Tenant> tenants) {
        this.tenants = tenants;
    }
    private ArrayList<Tenant> tenants;

}
