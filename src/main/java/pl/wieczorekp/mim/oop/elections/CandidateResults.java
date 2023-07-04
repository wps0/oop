package pl.wieczorekp.mim.oop.elections;

public record CandidateResults(Candidate candidate, int votes) implements Comparable<CandidateResults> {
    @Override
    public int compareTo(CandidateResults candidateResults) {
        return Integer.compare(candidateResults.votes, votes);
    }
}
