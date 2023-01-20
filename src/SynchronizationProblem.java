import java.util.*;
import java.util.stream.IntStream;

public class SynchronizationProblem implements Problem {
    @Override
    public void solve() {
        List<Integer> states = DataParser.get().startStates().isEmpty() ?
                IntStream.rangeClosed(0, DataParser.get().stateTransitions().size() - 1).boxed().toList() :
                DataParser.get().startStates();

        List<Integer> syncSequence = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.push(0);
        visited.add(0);

        var transitionTable = DataParser.get().stateTransitions();

        while (!stack.isEmpty()) {
            int currentState = stack.pop();

            for (int i = 0; i < transitionTable.get(currentState).size(); i++) {
                int nextState = transitionTable.get(currentState).get(i);
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    stack.push(nextState);
                    syncSequence.add(i);
                }
            }
        }
        Collections.reverse(syncSequence);
        syncSequence.forEach(System.out::println);
    }
}
