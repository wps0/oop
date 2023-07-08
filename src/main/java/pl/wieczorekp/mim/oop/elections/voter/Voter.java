package pl.wieczorekp.mim.oop.elections.voter;

import pl.wieczorekp.mim.oop.elections.Candidate;
import pl.wieczorekp.mim.oop.elections.Vote;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class Voter {
    private static final Random RNG = new Random();
    private String name;
    private Vote vote;

    public Voter(String name) {
        this.name = name;
        this.vote = null;
    }

    public abstract Optional<Candidate> selectCandidate(Collection<Candidate> candidates);

    public String name() {
        return name;
    }

    public Vote vote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    protected Candidate selectRandom(List<Candidate> candidates) {
        return candidates.isEmpty() ? null : candidates.get(RNG.nextInt(candidates.size()));
    }
}
