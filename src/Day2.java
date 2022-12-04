public class Day2 {

    PuzzleInputFetcher inputFetcher;

    public Day2(String cookie) {
        this.inputFetcher = new PuzzleInputFetcher(2, cookie);
        solve();
    }

    public void solve() {
        var totalPoints = inputFetcher.getStream()
                .map(line -> {
                    char mine = line.charAt(2);
                    char opponent = line.charAt(0);

                    return getResult(opponent, mine) + getPointsForWeapon(mine);
                }).reduce(0, Integer::sum);

        System.out.println(totalPoints);

        var totalPoints2 = inputFetcher.getStream()
                .map(line -> {
                    char result = line.charAt(2);
                    char opponent = line.charAt(0);

                    return getResultPoints2(result) + getWeaponPoints2(opponent, result);
                }).reduce(0, Integer::sum);

        System.out.println(totalPoints2);

    }

    public int getResult(char opponent, char mine) {
        // Rock
        if (opponent == 'A') {
            return switch (mine) {
                case 'X': yield 3; // Rock
                case 'Y': yield 6; // Paper
                case 'Z': yield 0; // Scissor
                default: throw new IllegalStateException();
            };
        }

        // Paper
        if (opponent == 'B') {
            return switch (mine) {
                case 'X': yield 0; // Rock
                case 'Y': yield 3; // Paper
                case 'Z': yield 6; // Scissor
                default: throw new IllegalStateException();
            };
        }

        // Scissors
        if (opponent == 'C'){
            return switch (mine) {
                case 'X': yield 6; // Rock
                case 'Y': yield 0; // Paper
                case 'Z': yield 3; // Scissor
                default: throw new IllegalStateException();
            };
        }
        throw new IllegalStateException();
    }

    public int getPointsForWeapon(char mine) {
        return switch (mine) {
            case 'X': yield 1;
            case 'Y': yield 2;
            case 'Z': yield 3;
            default: throw new IllegalStateException();
        };
    }

    public char getWeaponPoints2(char opponent, char result) {
        // Rock
        if (opponent == 'A') {
            return switch (result) {
                case 'X': yield 3; // Lose
                case 'Y': yield 1; // Draw
                case 'Z': yield 2; // Win
                default: throw new IllegalStateException();
            };
        }

        // Paper
        if (opponent == 'B') {
            return switch (result) {
                case 'X': yield 1; // Lose
                case 'Y': yield 2; // Draw
                case 'Z': yield 3; // Win
                default: throw new IllegalStateException();
            };
        }

        // Scissors
        if (opponent == 'C'){
            return switch (result) {
                case 'X': yield 2; // Lose
                case 'Y': yield 3; // Draw
                case 'Z': yield 1; // Win
                default: throw new IllegalStateException();
            };
        }
        throw new IllegalStateException();
    }

    public int getResultPoints2(char result) {
        return switch (result) {
            case 'X': yield 0; // loss
            case 'Y': yield 3; // draw
            case 'Z': yield 6; // win
            default: throw new IllegalStateException();
        };
    }


}

