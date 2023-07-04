package pl.wieczorekp.mim.oop.elections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

// Głosuje na losowego kandydata, którego imię i nazwisko składa się z 3.0 segmentów
// Wpp. oddaje nieważny głos
public class StudentVoter extends Voter {
    public StudentVoter(String name) {
        super(name);
    }

    @Override
    public Optional<Candidate> selectCandidate(Collection<Candidate> candidates) {
        List<Candidate> ok = new ArrayList<>();

        for (Candidate c : candidates) {
            if (c.name().contains(" ")) {
                ok.add(c);
            }
        }

        return ok.isEmpty() ? Optional.empty() : Optional.of(selectRandom(ok));
    }
}
