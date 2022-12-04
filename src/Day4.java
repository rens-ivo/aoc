import java.util.AbstractMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {

    PuzzleInputFetcher inputFetcher;

    public Day4(String cookie) {
        this.inputFetcher = new PuzzleInputFetcher(4, cookie);
        this.solve();
        this.solve2();
    }

    public void solve() {
        var fullyContainedAmount = inputFetcher.getStream()
                .map(s -> {
                    var stringArray = s.split("," );

                    var left = stringArray[0];
                    var right = stringArray[1];

                    var leftArray = left.split("-");
                    var leftPair = new AbstractMap.SimpleEntry<>(Integer.parseInt(leftArray[0]), Integer.parseInt(leftArray[1]));

                    var rightArray = right.split("-");
                    var rightPair = new AbstractMap.SimpleEntry<>(Integer.parseInt(rightArray[0]), Integer.parseInt(rightArray[1]));

                    // left inside right
                    if (leftPair.getKey() >= rightPair.getKey() && leftPair.getValue() <= rightPair.getValue()) {
                        return 1;
                    }

                    // right inside left
                    if (rightPair.getKey() >= leftPair.getKey() && rightPair.getValue() <= leftPair.getValue()) {
                        return 1;
                    }

                    return 0;
                }).reduce(0, Integer::sum);

        System.out.println(fullyContainedAmount);
    }

    public void solve2() {
        var overlapsAmount = inputFetcher.getStream()
                .map(s -> {
                    var stringArray = s.split("," );

                    var left = stringArray[0];
                    var right = stringArray[1];

                    var leftArray = left.split("-");
                    Set<Integer> leftNumbers = IntStream.rangeClosed(
                            Integer.parseInt(leftArray[0]), Integer.parseInt(leftArray[1]))
                            .boxed()
                            .collect(Collectors.toSet());

                    var rightArray = right.split("-");
                    Set<Integer> rightNumbers = IntStream.rangeClosed(
                            Integer.parseInt(rightArray[0]), Integer.parseInt(rightArray[1]))
                            .boxed()
                            .collect(Collectors.toSet());

                    var anyOverlaps = leftNumbers.stream().anyMatch(rightNumbers::contains);

                    return anyOverlaps ? 1 : 0;
                }).reduce(0, Integer::sum);

        System.out.println(overlapsAmount);

    }
}

