import java.util.*;

public class AccessibleProblem implements Problem {
    @Override
    public void solve() {
        ArrayList<Integer> solution = new ArrayList<>();

        List<Integer> startStates = DataParser.get().startStates();

        int[] visited = new int[DataParser.get().stateTransitions().size()];

        Queue<Integer> queue = new LinkedList<>();

        startStates.forEach(state -> {
            visited[state] = 1;
            queue.add(state);
        });

        while (!queue.isEmpty()) {
            int state = queue.poll();

            solution.add(state);
            DataParser.get().stateTransitions().get(state).forEach(neighbor -> {
                if (visited[neighbor] == 0) {
                    visited[neighbor] = 1;
                    queue.add(neighbor);
                }
            });
        }

        Collections.sort(solution);
        solution.forEach(System.out::println);
    }
}
