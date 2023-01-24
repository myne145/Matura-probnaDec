package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main {

//    private static void zad1_1() throws Exception{
//        String content = Files.readString(Path.of("mecz.txt"));
//        System.out.println(content.length());
//        int counter = 0;
//
//        for(int i = 1; i < content.length(); i++) {
//            if(content.charAt(i-1) != content.charAt(i)) {
//                counter++;
//            }
//        }
//        System.out.println(counter);
//    }
//
//    private static void zad1_2() throws IOException {
//        //String content = Files.readString(Path.of("mecz.txt"));
//        int amountOfA = 0;
//        int amountOfB = 0;
//        for(int i = 0; i < content.length(); i++) {
//            if(content.charAt(i) == 'A')
//                amountOfA++;
//            if(content.charAt(i) == 'B')
//                amountOfB++;
//            if((amountOfA >= 1000 || amountOfB >= 1000) && (Math.max(amountOfA, amountOfB) >= Math.min(amountOfA, amountOfB) + 3)) {
//                System.out.println("A: " + amountOfA + ", " + "B: " + amountOfB);
//                break;
//            }
//        }
//    }
//
//    private static void zad1_3() throws IOException {
//        String content = Files.readString(Path.of("mecz.txt"));
//        int howLongIsTheASequence = 1;
//        int howLongIsTheBSequence = 1;
//        ArrayList<Integer> aTeamPassas = new ArrayList<>();
//        ArrayList<Integer> bTeamPassas = new ArrayList<>();
//        for(int i = 1; i < content.length(); i++) {
//            if(content.charAt(i-1) == 'A' && content.charAt(i) == 'A') {
//                howLongIsTheASequence++;
//            } else {
//                if(howLongIsTheASequence >= 10) {
//                    aTeamPassas.add(howLongIsTheASequence);
//                }
//                howLongIsTheASequence = 1;
//            }
//            if(content.charAt(i-1) == 'B' && content.charAt(i) == 'B') {
//                howLongIsTheBSequence++;
//            } else {
//                if(howLongIsTheBSequence >= 10) {
//                    bTeamPassas.add(howLongIsTheBSequence);
//                }
//                howLongIsTheBSequence = 1;
//            }
//        }
//        System.out.println("A:");
//        for(Integer i : aTeamPassas)
//            System.out.println(i);
//        System.out.println("B:");
//        for(Integer i : bTeamPassas)
//

    static ArrayList<Integer> sieveOfEratosthenes(int n)
    {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<=n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                arr.add(i);
        }
        return arr;
    }

    private static void zad3Part1() throws IOException {
        File liczby = new File("liczby_przyklad.txt");
        ArrayList<Integer> arr = new ArrayList<>();
        for(String s : Files.readAllLines(liczby.toPath())) {
            arr.add(Integer.parseInt(s));
        }

        //actual alorithm here
        ArrayList<Integer> theSameCounters = new ArrayList<>();
        ArrayList<Integer> notTheSameCounters = new ArrayList<>();
        for(Integer i : arr) {
            if(i % 2 == 0) {

                ArrayList<Integer> primeNumbers = new ArrayList<>(sieveOfEratosthenes(i));
                int counter = 0;
                int counterTheSame = 0;
                //System.out.println(primeNumbers);
                for(int y = primeNumbers.size() - 1; y >= 0; y--) {
                    int temp = i - primeNumbers.get(y);
                    if(primeNumbers.contains(temp) && primeNumbers.indexOf(temp) <= y)
                        counter++;
                    if(primeNumbers.contains(temp))
                        counterTheSame++;
                    }
                    theSameCounters.add(counterTheSame);
                    notTheSameCounters.add(counter);
                    //System.out.println("Counter NOT THE SAME for " + i + ": " + counter + ", Counter THE SAME: " + counterTheSame);

            }
        } //TODO: liczba dla jakiej to jest
        System.out.println(Collections.max(notTheSameCounters) + " " + Collections.max(theSameCounters) + "\n" + Collections.min(notTheSameCounters) + " " + Collections.min(theSameCounters));
    }

    public static void main(String[] args) throws IOException {
        zad3Part1();
    }

    /*
    Hipoteza Goldbacha głosi, że każda liczba parzysta większa od 2 jest sumą dwóch liczb
pierwszych. Nie wiemy, czy ta hipoteza jest prawdziwa dla wszystkich liczb parzystych
dodatnich, ale została potwierdzona dla wszystkich liczb „rozsądnej wielkości”,
zwłaszcza dla nie przekraczających 10^18. Oczywiście liczba może mieć więcej niż jeden
rozkład na sumę dwóch liczb pierwszych, np. 22 = 19 + 3 = 17 + 5 = 11 + 11.
Dla każdej z liczb z pliku liczby.txt rozstrzygnij, na ile różnych sposobów da się ją
przedstawić jako sumę dwóch liczb pierwszych.
Podaj:
• liczbę, która ma najwięcej różnych rozkładów na sumę dwóch liczb pierwszych, oraz
liczbę takich rozkładów
• liczbę, która ma najmniej różnych rozkładów na sumę dwóch liczb pierwszych, oraz liczbę
takich rozkładów.
Uwaga: przyjmujemy, że dwa rozkłady są różne, jeśli nie zawierają takiej samej pary
składników. Przykładowo: rozkłady 22 = 19 + 3 i 22 = 3 + 19 są takie same.
Dla pliku liczby_przyklad.txt odpowiedzią jest: 996 37 4 1
(liczba 996 ma 37 rozkładów, a 4 tylko jeden)
     */






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
