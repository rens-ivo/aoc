import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Day1 {

    PuzzleInputFetcher inputFetcher;

    public Day1(String cookie) {
        this.inputFetcher = new PuzzleInputFetcher(1, cookie);
        solve();
    }
    public void solve() {
        List<Integer> calories = new ArrayList<>();
        calories.add(0);

        inputFetcher.getStream()
                .forEachOrdered(value -> {
                    if (value.isBlank()) {
                        calories.add(0);
                    } else {
                        var cals = Integer.parseInt(value) + calories.get(calories.size() - 1);
                        calories.set(calories.size() - 1, cals);
                    }
                });

        System.out.println("part 1");
        System.out.println(calories.stream().mapToInt(s -> s).max());

        List<AbstractMap.SimpleEntry<Integer, Integer>> elvesWithCalories = new ArrayList<>();

        IntStream.range(0, calories.size()).forEachOrdered(index -> {
            elvesWithCalories.add(new AbstractMap.SimpleEntry<>(index, calories.get(index)));
        });

        elvesWithCalories.sort((x,y) -> {
            if (Objects.equals(x.getValue(), y.getValue())) return 0;
            return x.getValue() < y.getValue() ? 1 : -1;
        });

        System.out.println("part 2");
        System.out.println(elvesWithCalories.get(0).getValue() + elvesWithCalories.get(1).getValue() + elvesWithCalories.get(2).getValue());
    }


}
