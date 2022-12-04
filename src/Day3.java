import java.util.ArrayList;
import java.util.List;

public class Day3 {

    PuzzleInputFetcher inputFetcher;

    public Day3(String cookie) {
        this.inputFetcher = new PuzzleInputFetcher(3, cookie);
        solve();
        solve2();
    }

    public void solve() {
        var sumOfPriorities = inputFetcher.getStream()
                .map(line -> {
                    var charArray = line.toCharArray();
                    List<Character> left = new ArrayList<>();
                    List<Character> right = new ArrayList<>();

                    for (int i = 0; i < charArray.length; i++) {
                        var currChar = charArray[i];
                        if (i < charArray.length / 2) {
                            left.add(currChar);
                        } else {
                            right.add(currChar);
                        }
                    }

                    return left.stream()
                            .distinct().filter(right::contains)
                            .map(this::itemToPriority)
                            .reduce(0, Integer::sum);
                }).reduce(0, Integer::sum);

        System.out.println(sumOfPriorities);
    }

    public void solve2() {
        var bags = inputFetcher.getStream()
                .map(line -> line.chars().mapToObj(c -> (char) c).toList())
                .toList();

        var totalPriorities = 0;
        for (int i = 0; i < bags.size(); i = i + 3) {
            var bag1 = bags.get(i);
            var bag2 = bags.get(i+1);
            var bag3 = bags.get(i+2);

            var item = bag1.stream().filter(bag2::contains)
                    .filter(bag3::contains)
                    .findFirst();

            if (item.isEmpty()) throw new IllegalStateException();

            totalPriorities = totalPriorities + this.itemToPriority(item.get());
        }
        System.out.println(totalPriorities);
    }

    public int itemToPriority(char item) {
        var asciiInt = (int) item;
        if (asciiInt < 97) {
            return asciiInt - 65  + 27;
        } else {
            return asciiInt - 96;
        }
    }

}

