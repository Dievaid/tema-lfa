import java.util.*;

public final class DataParser {
    private final List<Integer> startStates;
    private final List<List<Integer>> stateTransitions;
    private final List<Set<Integer>> reversedTransitions;

    private final List<Integer> finalStates = new ArrayList<>();
    private DataParser() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();

        reversedTransitions = new ArrayList<>();
        stateTransitions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reversedTransitions.add(new HashSet<>());
            stateTransitions.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                stateTransitions.get(i).add(0);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int transitionTo = scanner.nextInt();
                reversedTransitions.get(transitionTo).add(i);
                stateTransitions.get(i).set(j, transitionTo);
            }
        }

        startStates = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            startStates.add(scanner.nextInt());
        }
    };

    private static final DataParser dataParser = new DataParser();

    public static DataParser get() {
        return dataParser;
    }

    public List<Integer> startStates() {
        return startStates;
    }


    public List<List<Integer>> stateTransitions() {
        return stateTransitions;
    }

    public List<Set<Integer>> reversedTransitions() {
        return reversedTransitions;
    }
}
