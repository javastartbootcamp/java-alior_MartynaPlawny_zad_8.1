package pl.javastart.task;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class TournamentStats {

    private final List<Player> players = new ArrayList<>();
    private int parameterToSort;
    private int ascentOrDescent;
    Pattern pattern = Pattern.compile("([\\S]{1,20} [\\S]{1,20} [0-9]{1,10})");

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości

        System.out.println("Podaj wynik kolejnego gracza (lub STOP):");
        String dataFromUser = scanner.nextLine();

        while (!dataFromUser.equals("STOP")) {
            Matcher matcher = pattern.matcher(dataFromUser);
            if (!matcher.find()) {
                System.out.println("Niepoprawny format danych gracza (imię nazwisko wynik). Spróbuj jeszcze raz:");
            } else {
                System.out.println("Podaj wynik kolejnego gracza (lub STOP):");
                String[] player = dataFromUser.split(" ");
                players.add(new Player(player[0], player[1], parseInt(player[2])));
            }
            dataFromUser = scanner.nextLine();
        }

        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        parameterToSort = scanner.nextInt();
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        ascentOrDescent = scanner.nextInt();

        sortAscentOrDescent();
        saveFile();
    }

    private void saveFile() {
        String fileName = "stats.csv";
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter)
        ) {
            for (Player player : players) {
                writer.write(player.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }

        System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
    }

    private void sortAscentOrDescent() {
        if (ascentOrDescent == 1) {
            sortByGivenCriteriaAscent();
        } else if (ascentOrDescent == 2) {
            sortByGivenCriteriaDescent();
        } else {
            System.out.println("Nieprawidłowa wartość parametru do sortowania");
        }
    }

    private void sortByGivenCriteriaAscent() {
        if (parameterToSort == 1) {
            //metoda z Comparator i klasą zagnieżdżoną
            players.sort(new Player.FirstNameAscentComparator());
        } else if (parameterToSort == 2) {
            //metoda z Comparator zewnętrzną klasą
            players.sort(new LastNameAscentComparator());
        } else if (parameterToSort == 3) {
            //metoda z Comparable
            Collections.sort(players);
        } else {
            System.out.println("Nieprawidłowa wartość parametru do sortowania");
        }
    }

    private void sortByGivenCriteriaDescent() {
        if (parameterToSort == 1) {
            //metoda z Comparator i lambda
            players.sort((p1, p2) -> p2.getFirstName().compareTo(p1.getFirstName()));
        } else if (parameterToSort == 2) {
            //metoda z Comparator zewnętrzną klasą
            players.sort(new LastNameDescentComparator());
        } else if (parameterToSort == 3) {
            //metoda z Comparable reverse
            Collections.sort(players);
            Collections.reverse(players);
        } else {
            System.out.println("Nieprawidłowa wartość parametru do sortowania");
        }
    }
}
