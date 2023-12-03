package pl.javastart.task;

import java.util.Comparator;

public class Player implements Comparable<Player> {
    private String firstName;
    private String lastName;
    private Integer result;

    public Player(String firstName, String lastName, int result) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.result = result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    //użycie Comparable - .sort z podaniem samej listy
    @Override
    public int compareTo(Player p) {
        if (getResult() > p.getResult()) {
            return 1;
        } else if (getResult() < p.getResult()) {
            return -1;
        }
        return 0;
    }

    public static class FirstNameAscentComparator implements Comparator<Player> {

        @Override
        public int compare(Player p1, Player p2) {
            return p1.getFirstName().compareTo(p2.getFirstName());
        }
    }

    @Override
    public String toString() {
        String playerData = firstName + " " + lastName + ";" + result;
        System.out.println(playerData);
        return playerData;
    }
}
