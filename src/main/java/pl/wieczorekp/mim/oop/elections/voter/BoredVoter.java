package pl.wieczorekp.mim.oop.elections.voter;

import pl.wieczorekp.mim.oop.elections.Candidate;

import java.util.Collection;
import java.util.Optional;

public class BoredVoter extends Voter {
    public BoredVoter(String name) {
        super(name);
    }

    @Override
    public Optional<Candidate> selectCandidate(Collection<Candidate> candidates) {
        return Optional.empty();
    }
}
