package pl.wieczorekp.mim.oop.elections;

import java.util.*;

public class Elections {
    private final List<Vote> votes;
    private final Collection<Candidate> candidates;
    private final List<Voter> voters;

    public Elections(Collection<Candidate> candidates, List<Voter> voters) {
        this.candidates = candidates;
        this.voters = voters;
        this.votes = new ArrayList<>();
    }

    public void holdElections() {
        for (Voter v : voters) {
            Optional<Candidate> selected = v.selectCandidate(candidates);

            Vote vote = new Vote(v, selected.orElse(null));
            v.setVote(vote);
            votes.add(vote);
        }
    }

    public String giveResults() {
        List<CandidateResults> results = countVotes();
        Collections.sort(results);

        int longestCandidateName = getLongestCandidateNameAndSurname();
        int longestVotesCount = String.valueOf(results.stream().map(CandidateResults::votes).max(Integer::compareTo).orElse(0)).length();
        int totalVotesCount = results.stream().map(CandidateResults::votes).reduce(Integer::sum).orElse(0);
        StringBuilder table = new StringBuilder();
        for (CandidateResults r : results) {
            Candidate c = r.candidate();
            if (c != null)
                table.append(formatTableRow(longestCandidateName, longestVotesCount, c.surname().toUpperCase() + " " + c.name(), r.votes(), totalVotesCount));
            else
                table.append(formatTableRow(longestCandidateName, longestVotesCount, "Nieważne", r.votes(), totalVotesCount));
        }

        return table.toString();
    }

    private List<CandidateResults> countVotes() {
        Map<Candidate, Integer> totalVotes = new HashMap<>();
        for (Vote v : votes) {
            totalVotes.put(v.candidate(), totalVotes.getOrDefault(v.candidate(), 0) + 1);
        }

        List<CandidateResults> results = new ArrayList<>();
        for (Map.Entry<Candidate, Integer> e : totalVotes.entrySet()) {
            results.add(new CandidateResults(e.getKey(), e.getValue()));
        }
        return results;
    }

    private String formatTableRow(int longestCandidateName, int longestVotesCount, String name, int votes, int totalVotesCount) {
        return String.format("| %-" + longestCandidateName + "s | %-" + longestVotesCount + "s | %5.2f%% |%n",
                name,
                votes,
                (float) votes*100/totalVotesCount);
    }

    private int getLongestCandidateNameAndSurname() {
        int ans = 0;
        for (Candidate c : candidates) {
            ans = Integer.max(ans, c.name().length() + c.surname().length() + 1);
        }
        return ans;
    }
}
