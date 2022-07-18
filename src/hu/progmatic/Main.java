package hu.progmatic;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        try {
            List<Olimpia> results = loadOlimpiaResults("helsinki.txt");

            System.out.println("3.feladat: Pontszerző helyezések száma: " + results.size());

            System.out.println("4.feladat: ");
            Map<Integer, Integer> totalMedals = totalMedalsByPositionCounter(results);
            for (int places : totalMedals.keySet()) {
                System.out.println(places + ": " + totalMedals.get(places));
            }
            System.out.println("Összesen: " + sumMedals(results));

            for (Olimpia olimpia : results){
                if(olimpia.getNameOfSport().equals("kajakkenu")){
                    olimpia.setNameOfSport("kajak-kenu");
                }
            }
            fileWriter(results);

            System.out.println("5. feladat: Olimpiai pontok száma: " + sumPoints(results));

            System.out.println("6.feladat: ");
            Map<String, Integer> sum = getSum(results);
            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : sum.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    maxEntry = entry;
                }
            }
            System.out.println(maxEntry.getKey().toUpperCase() + " sportágban szereztek több érmet");

            System.out.println("8. feladat: " + mostMemberInTeam(results));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Olimpia> loadOlimpiaResults(String path) throws IOException {
        List<Olimpia> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    Olimpia olimpia = new Olimpia(line);
                    results.add(olimpia);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

    public static Map<Integer, Integer> totalMedalsByPositionCounter(List<Olimpia> results) {
        Map<Integer, Integer> totalMedals = new TreeMap<>();
        for (Olimpia olimpia : results) {
            if (olimpia.getPlace() <= 3) {
                int total = totalMedals.getOrDefault(olimpia.getPlace(), 0);
                totalMedals.put(olimpia.getPlace(), total + 1);
            }
        }
        return totalMedals;
    }

    public static int sumMedals(List<Olimpia> results) {
        int total = 0;

        for (Olimpia olimpia : results) {
            if (olimpia.getPlace() <= 3) {
                total++;
            }
        }
        return total;
    }

    public static List<Olimpia> fileWriter(List<Olimpia> results) throws IOException {
        List<Olimpia> newResults = new ArrayList<>();
        for (Olimpia olimpia : results) {
            newResults.add(olimpia);
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("helsinki2.txt")))) {
            for (Olimpia olimpia : newResults) {
                writer.println(olimpia.getPlace() + " " + olimpia.getMembersOfTeam() + " " + olimpia.getOlimpiaiPont() + " " + olimpia.getNameOfSport() + " " + olimpia.getTypeOfSport());
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return newResults;
    }

    public static int sumPoints(List<Olimpia> results){

        int sum = 0;
        for (Olimpia olimpia : results){
            sum += olimpia.getOlimpiaiPont();
        }

        return sum;
    }

    private static Map<String, Integer> getSum(List<Olimpia> results) {
        Map<String, Integer> sum = new TreeMap<>();
        for (Olimpia olimpia : results){
            int total = sum.getOrDefault(olimpia.getNameOfSport(), 0);
            sum.put(olimpia.getNameOfSport(), total + 1);
        }

        return sum;
    }


    public static Olimpia mostMemberInTeam(List<Olimpia> results) {
        Olimpia biggestTeam = new Olimpia();
        int max = Integer.MIN_VALUE;

        for (Olimpia olimpia : results){
            if(olimpia.getMembersOfTeam() > max){
                max = olimpia.getMembersOfTeam();
                biggestTeam = olimpia;
            }
        }
        return biggestTeam;
    }
}
