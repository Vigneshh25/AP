    package Design_Datastructure.circuitbreaker.versions;

public interface Command<T> {
    T execute() throws Exception;
}
