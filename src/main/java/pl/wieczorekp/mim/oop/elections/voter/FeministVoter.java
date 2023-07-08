package pl.wieczorekp.mim.oop.elections.voter;

import pl.wieczorekp.mim.oop.elections.Candidate;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FeministVoter extends Voter {
    public FeministVoter(String name) {
        super(name);
    }

    @Override
    public Optional<Candidate> selectCandidate(Collection<Candidate> candidates) {
        List<Candidate> women = candidates.stream().filter(c -> c.sex().equals(Candidate.Sex.FEMALE)).collect(Collectors.toList());;
        women.sort(Comparator.comparingInt(c -> c.surname().charAt(0)));
        return women.isEmpty() ? Optional.empty() : Optional.of(women.get(0));
    }
}
