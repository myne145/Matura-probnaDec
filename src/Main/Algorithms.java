package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public abstract class Algorithms {

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
    public static String readFileString(File f) throws IOException {
        StringBuilder bobTheBuilder = new StringBuilder();
        for(String s : Files.readAllLines(f.toPath())) {
            //arr.add(Integer.parseInt(s));
            bobTheBuilder.append(s);
        }
        return bobTheBuilder.toString();
    }
}
