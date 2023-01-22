public final class ProblemFactory {
    public static final String ACCESSIBLE = "accessible";
    public static final String SYNCHRONIZE = "synchronize";
    public static Problem get(String type) {
        switch (type) {
            case ACCESSIBLE: return new AccessibleProblem();
            case SYNCHRONIZE: return new SynchronizationProblem();
            default: throw new IllegalStateException(type);
        }
    }
}
