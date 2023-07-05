package stream;

import entity.Bear;
import entity.Species;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args) {
        List<Bear> bearList = getList();
        // * filter() --------
        List<Bear> polarBears = bearList.stream()
                .filter(bear -> bear.getSpecies().equals(Species.POLAR_BEAR))
                .collect(Collectors.toList());
        //polarBears.forEach(System.out::println); //Output: filtered by PolarBears

        // * sorted() --------
        List<Bear> sorted = bearList.stream()
                .sorted(Comparator.comparing(Bear::getAge))
                .collect(Collectors.toList());
        //sorted.forEach(System.out::println); //Output: from youngest to oldest
        // ? .thenComparing - reversed()

        // * allMatch() - anyMatch() - Returning boolean ----
        // ? noneMatch() ------
        // ! BOOLEAN ----
        boolean noneMatch = bearList.stream()
                .noneMatch(bear -> bear.getSpecies().equals(Species.PANDA));
        System.out.println(noneMatch); //Output: false (there is nothing that matches PANDA

        // * Max - Min ----
        // ! OPTIONAL ---
        bearList.stream()
                .max(Comparator.comparing(Bear::getAge))
                .ifPresent(System.out::println);

        // * Group by ---
        Map<Species, List<Bear>> groupBySpecies = bearList.stream()
                .collect(Collectors.groupingBy(Bear::getSpecies));

        groupBySpecies.forEach((species, bears) -> {
            System.out.println(species);
            bears.forEach(System.out::println);
        });
    }

    static List<Bear> getList(){
        return List.of(
                new Bear("Baloo", 5, Species.GRIZZLY),
                new Bear("Yogi", 8, Species.GRIZZLY),
                new Bear("Klondike", 10, Species.POLAR_BEAR),
            new Bear("Nanook", 4, Species.POLAR_BEAR),
            new Bear("Arctic", 6, Species.POLAR_BEAR),
            new Bear("Midnight", 7, Species.BLACK_BEAR)
        );
    }
}


