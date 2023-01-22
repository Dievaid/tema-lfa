import java.util.*;

public class SynchronizationProblem implements Problem {
    private static class Pair<K> {
        private final K first;
        private final K second;

        public Pair(K first, K second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        public boolean isPairEqual() {
                return first.equals(second);
        }
    }

    private final HashSet<Pair<Integer>> visited = new HashSet<>();
    private final HashSet<List<Integer>> visitedStates = new HashSet<>();
    private ArrayList<Integer> mergeSequence = new ArrayList<>();

    private boolean dfs(Pair<Integer> state) {
        visited.add(state);

        if (state.isPairEqual()) {
            return true;
        }

        int m = DataParser.get().stateTransitions().get(0).size();
        List<List<Integer>> stateTransitions = DataParser.get().stateTransitions();

        for (int i = 0; i < m; i++) {
            int s = stateTransitions.get(state.first).get(i);
            int t = stateTransitions.get(state.second).get(i);

            if (s > t) {
                int aux = s;
                s = t;
                t = aux;
            }

            Pair<Integer> toExplore = new Pair<>(s, t);
            if (!visited.contains(toExplore)) {
                mergeSequence.add(i);
                if (dfs(toExplore)) {
                    return true;
                }
                mergeSequence.remove(mergeSequence.size() - 1);
            }
        }

        return false;
    }

    private void getMergeSequence(int s, int t) {
        if (s > t) {
            int aux = s;
            s = t;
            t = aux;
        }
        dfs(new Pair<>(s, t));
        visited.clear();
    }

    private ArrayList<Integer> updateStates(List<Integer> states) {
        HashSet<Integer> newStates = new HashSet<>();
        for (int currentState: states) {
            for (Integer step: mergeSequence) {
                currentState = DataParser.get().stateTransitions().get(currentState).get(step);
            }
            newStates.add(currentState);
        }
        mergeSequence = new ArrayList<>();
        return new ArrayList<>(newStates);
    }

    @Override
    public void solve() {
        List<Integer> states = DataParser.get().startStates();
        if (states.isEmpty()) {
            for (int i = 0; i < DataParser.get().stateTransitions().size(); i++) {
                states.add(i);
            }
        }

        visitedStates.add(states);

        List<Integer> syncSequence = new ArrayList<>();
        Random random = new Random(-748133826);

        while (states.size() > 1) {
            int s = random.nextInt(states.size());
            int t = random.nextInt(states.size());

            while (s == t) {
                t = random.nextInt(states.size());
            }

            getMergeSequence(s, t);
            syncSequence.addAll(mergeSequence);
            states = updateStates(states);

            if (visitedStates.contains(states)) {
                break;
            }

            visitedStates.add(states);
        }

        syncSequence.forEach(System.out::println);
    }
}
