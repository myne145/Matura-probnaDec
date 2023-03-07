package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Algorithms {
    public static void appendFile(File destination, String content) throws IOException {
        if(!destination.exists())
            destination.createNewFile();
        String finalContent = readFileString(destination) + "\n" + content;
        FileWriter writer = new FileWriter(destination);
        writer.write(finalContent);
        writer.close();
    }

    public static String decimalToHex(int decimal) { //zasady z google'a ale sama funkcja jest moja
        final ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('A','B','C','D','E','F'));
        final ArrayList<Integer> powersOf16 = new ArrayList<>(Arrays.asList(65536,4096,256,16,1));
        ArrayList<Integer> counters = new ArrayList<>(Arrays.asList(0,0,0,0,0));
        int temp = decimal;
        for(int i = 0; i < powersOf16.size(); i++) {
            while(temp >= powersOf16.get(i)) {
                temp -= powersOf16.get(i);
                counters.set(i, counters.get(i) + 1);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Integer counter : counters) {
            if (counter > 9) {
                result.append(alphabet.get(counter - 10));
            } else if (counter > 0) {
                result.append(counter);
            } else { //if counter == 0
                boolean keepThisZero = false;
                for(int i = 0; i < result.length(); i++) {
                    if(result.charAt(i) != 0)
                        keepThisZero = true;
                }
                if(keepThisZero)
                    result.append(counter);
            }
        }
        return result.toString();
    }

    public static boolean isPrime(int n) {
        boolean flag = true;
        if(n == 1)
            return false;

        for(int i = 2; i < n; i++) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static ArrayList<Integer> sieveOfEratosthenes(int n) { //TODO replace copied code from the internet
        boolean[] prime = new boolean[n+1];
        for(int i=0;i<=n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++) {
            if(prime[p]) {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 2; i <= n; i++) {
            if(prime[i])
                arr.add(i);
        }
        return arr;
    }

    public static ArrayList<Integer> readFileArr(File f) throws IOException {
        ArrayList<Integer> arr = new ArrayList<>();
        for(String s : Files.readAllLines(f.toPath())) {
            arr.add(Integer.parseInt(s));
        }
        return arr;
    }

    public static ArrayList<String> readFileArrString(File f) throws IOException {
        return new ArrayList<>(Files.readAllLines(f.toPath()));
    }

    public static String readFileString(File f) throws IOException {
        StringBuilder bobTheBuilder = new StringBuilder();
        for(String s : Files.readAllLines(f.toPath())) {
            //arr.add(Integer.parseInt(s));
            bobTheBuilder.append(s);
        }
        return bobTheBuilder.toString();
    }

    /**
     *
     * @param arr
     * @return arraylist: index 0 is the highest number, and index 1 is index of highest number
     */
    public static ArrayList<Integer> getHighestNumAndItsIndexInArr(ArrayList<Integer> arr) {
        int temp = 0;
        int tempIndex = 0;
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i) > temp) {
                temp = arr.get(i);
                tempIndex = i;
            }
        }

        return new ArrayList<>(Arrays.asList(temp, tempIndex));
    }

    public static ArrayList<Long> getHighestNumAndItsIndexInArrLong(List<Long> arr) {
        long temp = 0L;
        int tempIndex = 0;
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i) > temp) {
                temp = arr.get(i);
                tempIndex = i;
            }
        }

        return new ArrayList(Arrays.asList(temp, tempIndex));
    }
}
