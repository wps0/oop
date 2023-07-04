package pl.wieczorekp.mim.oop.elections;

import java.util.Collection;
import java.util.Optional;

public class AnarchistVoter extends Voter {
    public AnarchistVoter(String name) {
        super(name);
    }

    @Override
    public Optional<Candidate> selectCandidate(Collection<Candidate> candidates) {
        if (Math.random() > 0.5)
            return Optional.empty();
        return Optional.ofNullable(selectRandom(candidates.stream()
                .filter(c -> c.party().isEmpty())
                .toList()));
    }
}
