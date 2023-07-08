package pl.wieczorekp.mim.oop.elections.voter;

import org.junit.jupiter.api.Test;
import pl.wieczorekp.mim.oop.elections.Candidate;
import pl.wieczorekp.mim.oop.elections.Party;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BoredVoterTest {
    @Test
    void shouldNotSelectACandidate() {
        // given
        BoredVoter voter = new BoredVoter("voter");
        List<Candidate> cand = List.of(
                new Candidate("John", "Doe", Candidate.Sex.MALE, Optional.empty()),
                new Candidate("Janie-Erick", "Marx", Candidate.Sex.FEMALE, Optional.of(new Party("CP")))
        );

        // when
        Optional<Candidate> actual = voter.selectCandidate(cand);

        // then
        assertTrue(actual.isEmpty());
    }
}