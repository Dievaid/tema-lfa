import java.util.*;
import java.util.stream.IntStream;

public class SyncProblem implements Problem {
    @Override
    public void solve() {
        List<Integer> states = DataParser.get().startStates().isEmpty() ?
                IntStream.rangeClosed(0, DataParser.get().stateTransitions().size() - 1).boxed().toList() :
                DataParser.get().startStates();

        List<Integer> syncSequence = new ArrayList<>();
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        HashSet<Map.Entry<Integer, Integer>> visited = new HashSet<>();

        Random random = new Random();

        int s = states.get(random.nextInt(states.size()));
        int t = states.get(random.nextInt(states.size()));

        while (s >= t) {
            t = states.get(random.nextInt(states.size()));
        }

        AbstractMap.SimpleEntry<Integer, Integer> transition = new AbstractMap.SimpleEntry<>(s, t);

        stack.push(transition);
        visited.add(transition);

        var transitionTable = DataParser.get().stateTransitions();

        syncSequence.forEach(System.out::println);
    }
}
