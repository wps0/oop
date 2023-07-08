package pl.wieczorekp.mim.oop.elections;

import pl.wieczorekp.mim.oop.elections.voter.Voter;

public record Vote(Voter voter, Candidate candidate) {
}
