public final class ProblemFactory {
    public static final String ACCESSIBLE = "accessible";
    public static final String SYNCHRONIZE = "synchronize";
    public static Problem get(String type) {
        return switch (type) {
            case ACCESSIBLE -> new AccessibleProblem();
            case SYNCHRONIZE -> new SynchronizationProblem();
            default -> throw new IllegalStateException(type);
        };
    }
}
