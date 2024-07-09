package roomallotment.strategy;


import roomallotment.model.*;
import roomallotment.model.Reservation;

import java.util.List;
import java.util.Map;

public interface AllocationStrategy {
    List<Reservation> allocateSeats(Exam exam, List<Student> students, Map<Integer, Hall> halls);
}
