package Main;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static Main.Algorithms.*;

public class Main {
    private static void zad1_1() throws Exception{
        String content = readFileString(new File("mecz.txt"));
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
        String content = readFileString(new File("mecz.txt"));
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
    private static void zad1_3() throws IOException {
        String content = readFileString(new File("mecz.txt"));
        int howLongIsTheASequence = 1;
        int howLongIsTheBSequence = 1;
        ArrayList<Integer> aTeamPassas = new ArrayList<>();
        ArrayList<Integer> bTeamPassas = new ArrayList<>();
        for (int i = 1; i < content.length(); i++) {
            if (content.charAt(i - 1) == 'A' && content.charAt(i) == 'A') {
                howLongIsTheASequence++;
            } else {
                if (howLongIsTheASequence >= 10) {
                    aTeamPassas.add(howLongIsTheASequence);
                }
                howLongIsTheASequence = 1;
            }
            if (content.charAt(i - 1) == 'B' && content.charAt(i) == 'B') {
                howLongIsTheBSequence++;
            } else {
                if (howLongIsTheBSequence >= 10) {
                    bTeamPassas.add(howLongIsTheBSequence);
                }
                howLongIsTheBSequence = 1;
            }
        }
        System.out.println("A:");
        for (Integer i : aTeamPassas)
            System.out.println(i);
        System.out.println("B:");
        for (Integer i : bTeamPassas)
            System.out.println(i);
    }

    /*
    Dla każdej liczby x z pliku liczby.txt sprawdź, czy liczba x – 1 jest liczbą pierwszą.
    Podaj, ile liczb z pliku liczby.txt po pomniejszeniu o 1 daje liczbę pierwszą.
    Dla pliku liczby_przyklad.txt odpowiedzią jest 94.
     */
    private static void zad3Part2() throws IOException {
        ArrayList<Integer> arr = readFileArr(new File("liczby.txt"));
        int counter = 0;
        for(Integer i : arr) {
            if (isPrime(i - 1)) {
                counter++;
            }
        }
        System.out.println("W pliku liczby.txt znajduje sie " + counter + " pierwszych liczb pomniejszonych o 1.");
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
    private static void zad3Part3() throws IOException {
        File liczby = new File("liczby_przyklad.txt");
        //actual alorithm here
        ArrayList<Integer> arr = readFileArr(liczby);
        ArrayList<Integer> theSameCounters = new ArrayList<>();
        ArrayList<Integer> notTheSameCounters = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
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
                    numbers.add(i);
                    //System.out.println("Counter NOT THE SAME for " + i + ": " + counter + ", Counter THE SAME: " + counterTheSame);

            }
        }
        int indexMax = notTheSameCounters.indexOf(Collections.max(notTheSameCounters));
        int indexMin = notTheSameCounters.indexOf(Collections.min(notTheSameCounters));
        System.out.println("Najwiecej rozkladow (nie te same, te same): " + numbers.get(indexMax) + ", " + Collections.max(notTheSameCounters) + " " + Collections.max(theSameCounters) + "\n" + "najmniej rozkladow (nie te same, te same): "+ numbers.get(indexMin) + ", " + Collections.min(notTheSameCounters) + " " + Collections.min(theSameCounters));
    }

    /*
    Dla każdej liczby z pliku liczby.txt znajdź jej reprezentację w systemie szesnastkowym.
    Dla każdej cyfry szesnastkowej podaj, ile razy występuje ona łącznie w zapisach
    szesnastkowych wszystkich liczb z pliku liczby.txt.
    Dla pliku liczby_przyklad.txt odpowiedzią jest
    0:2
    1:3
    2:5
    3:2
    4:94
    5:0
    6:1
    7:0
    8:2
    9:2
    A:0
    B:0
    C:1
    D:1
    E:3
    F:0
     */
    private static void zad3Part4() throws IOException {
        ArrayList<Integer> liczby = readFileArr(new File("liczby.txt"));
        final LinkedHashMap<Character, Integer> result = new LinkedHashMap<Character, Integer>() {{
            put('0', 0);
            put('1', 0);
            put('2', 0);
            put('3', 0);
            put('4', 0);
            put('5', 0);
            put('6', 0);
            put('7', 0);
            put('8', 0);
            put('9', 0);
            put('A', 0);
            put('B', 0);
            put('C', 0);
            put('D', 0);
            put('E', 0);
            put('F', 0);
        }};
        StringBuilder liczbyHex = new StringBuilder();
        for (Integer decimalNum : liczby)
            liczbyHex.append(decimalToHex(decimalNum));
        for(int i = 0; i < liczbyHex.length(); i++) {
            result.put(liczbyHex.charAt(i), result.get(liczbyHex.charAt(i)) + 1);
        }
        System.out.println(result);
        appendFile(new File("wyniki3.txt"), "Odpowiedź do zadania 3.4: " + result.toString());
    }

    private static void zad4Part1() throws IOException {
        ArrayList<String> arr = readFileArrString(new File("ekodom.txt"));
        LinkedHashMap<Date, Integer> data = new LinkedHashMap<>();
        arr.remove(0);
        for(String s : arr) {
//            System.out.println(s.split("\t")[0] + " " + s.split("\t")[1]);
            String[] date = s.split("\t")[0].split("\\.");
            data.put(new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0])), Integer.valueOf(s.split("\t")[1]));
        }
        Set<Date> set = data.keySet();
        ArrayList<Integer> counters = new ArrayList<>();
        int counter = 1;
        for(int i = 1; i < set.size(); i++) {
//            System.out.println(date + " ------------- " + data.get(date));
            if(data.get(set.toArray()[i-1]).equals(data.get(set.toArray()[i]))) {
                counter++;
                counters.add(counter);
            } else{
                counter = 1;
                counters.add(1);
            }
//            System.out.println(set.toArray()[i-1] + " ----- " + counter);
        }
        ArrayList<Integer> result = getHighestNumAndItsIndexInArr(counters);
        System.out.println("Najwiecej dni bez opadów to: " + result.get(0) + ", od dnia " + set.toArray()[result.get(1)] + " do dnia " + set.toArray()[result.get(1)]);
        //Problem był z podpunktem b, gdzie trzeba było sprawdzić ile razy podlano ogródek od 1.04 do 30.09, nie zgadzały się liczby i nie miało to sensu
    }


    private static void zad4Part3() throws IOException {
        ArrayList<String> arr = readFileArrString(new File("ekodom.txt"));
        LinkedHashMap<Date, Integer> data = new LinkedHashMap<>();
        arr.remove(0);
        for (String s : arr) {
//            System.out.println(s.split("\t")[0] + " " + s.split("\t")[1]);
            String[] date = s.split("\t")[0].split("\\.");
            data.put(new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0])), Integer.valueOf(s.split("\t")[1]));
        }
        ArrayList<Integer> waterAmount = new ArrayList<>();
        waterAmount.add(5000);
        Set<Date> keySet = data.keySet();
        List<Date> keys = new ArrayList<>(keySet);
        int counter = 0;
        int dayCounter = 1;
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i).getDay() != 3) {
                if(waterAmount.get(i) - 190 + data.get(keys.get(i)) <= 0) {
                    counter++;
                    waterAmount.add(0);
                }
                else {
                    waterAmount.add(waterAmount.get(i) - 190 + data.get(keys.get(i)));
                }
            } else {
                if (waterAmount.get(i) - 260 + data.get(keys.get(i)) <= 0) {
                    counter++;
                    waterAmount.add(0);

                } else {
                    waterAmount.add(waterAmount.get(i) - 260 + data.get(keys.get(i)));
                }
            }

            if(keys.get(i).getMonth() >= 3 && keys.get(i).getMonth() <= 8) {
                Date date = keys.get(i);
                if(data.get(date) == 0) {
                    dayCounter++;
                } else {
                    if(dayCounter >= 5) {
                        int howManyTimesWatering = dayCounter / 5;
                        if(waterAmount.get(i) - (howManyTimesWatering * 300) <= 0) {
                            waterAmount.set(i, 0);
                            counter++;
                        } else
                            waterAmount.set(i, waterAmount.get(i) - (howManyTimesWatering * 300));
                    }
                    dayCounter = 1;
                }
            }
        }
        System.out.println(waterAmount);
        System.out.println(counter);
    }

    public static void main(String[] args) throws IOException {
        zad4Part3();
    }
}
