package pl.wieczorekp.mim.oop.elections.voter;

import pl.wieczorekp.mim.oop.elections.Candidate;
import pl.wieczorekp.mim.oop.elections.Party;

import java.util.Collection;
import java.util.Optional;

public class PartyVoter extends Voter {
    private final Party favouriteParty;

    public PartyVoter(String name, Party favouriteParty) {
        super(name);
        this.favouriteParty = favouriteParty;
    }

    @Override
    public Optional<Candidate> selectCandidate(Collection<Candidate> candidates) {
        return Optional.ofNullable(selectRandom(candidates.stream()
                .filter(c -> c.party().isPresent())
                .filter(c -> c.party().get().equals(favouriteParty))
                .toList()));
    }
}
