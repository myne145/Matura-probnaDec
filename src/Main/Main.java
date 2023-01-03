package Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    private static void zad1_1() throws Exception{
        String content = Files.readString(Path.of("mecz.txt"));
        System.out.println(content.length());
        int counter = 0;

        for(int i = 1; i < content.length(); i++) {
            if(content.charAt(i-1) != content.charAt(i)) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static void zad1_2() throws IOException {
        String content = Files.readString(Path.of("mecz.txt"));
        int amountOfA = 0;
        int amountOfB = 0;
        for(int i = 0; i < content.length(); i++) {
            if(content.charAt(i) == 'A')
                amountOfA++;
            if(content.charAt(i) == 'B')
                amountOfB++;
            if((amountOfA >= 1000 || amountOfB >= 1000) && (Math.max(amountOfA, amountOfB) >= Math.min(amountOfA, amountOfB) + 3)) {
                System.out.println("A: " + amountOfA + ", " + "B: " + amountOfB);
                break;
            }
        }
    }

    private static void zad1_3() throws IOException {
        String content = Files.readString(Path.of("mecz.txt"));
        int howLongIsTheASequence = 1;
        int howLongIsTheBSequence = 1;
        ArrayList<Integer> aTeamPassas = new ArrayList<>();
        ArrayList<Integer> bTeamPassas = new ArrayList<>();
        for(int i = 1; i < content.length(); i++) {
            if(content.charAt(i-1) == 'A' && content.charAt(i) == 'A') {
                howLongIsTheASequence++;
            } else {
                if(howLongIsTheASequence >= 10) {
                    aTeamPassas.add(howLongIsTheASequence);
                }
                howLongIsTheASequence = 1;
            }
            if(content.charAt(i-1) == 'B' && content.charAt(i) == 'B') {
                howLongIsTheBSequence++;
            } else {
                if(howLongIsTheBSequence >= 10) {
                    bTeamPassas.add(howLongIsTheBSequence);
                }
                howLongIsTheBSequence = 1;
            }
        }
        System.out.println("A:");
        for(Integer i : aTeamPassas)
            System.out.println(i);
        System.out.println("B:");
        for(Integer i : bTeamPassas)
            System.out.println(i);
    }

    public static void main(String[] args) throws IOException {
        zad1_3();
    }

//BAAB
    /*
    Powiemy, że drużyna ma dobrą passę, jeśli wygrywa rozgrywki co najmniej 10 razy z rzędu.
    Każda dobra passa rozpoczyna się albo na początku meczu, albo bezpośrednio po
    przegranej rozgrywce. Każda dobra passa kończy się albo z końcem meczu, albo
    bezpośrednio przed przegraną rozgrywką.
    Podaj łączną liczbę dobrych pass, które miały obie drużyny w meczu. Wyznacz długość
    najdłuższej dobrej passy i drużynę, która ją osiągnęła. Tylko jedna drużyna miała dobrą
    passę o tej długości.
    Przykład: w meczu BBBBBBBBBBAABBAAAAAAAAAAABA mamy łącznie 2 dobre passy.
    Najdłuższą dobrą passę, o długości 11, osiągnęła drużyna A
     */
}
