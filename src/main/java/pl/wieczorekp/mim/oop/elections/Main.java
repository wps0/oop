package pl.wieczorekp.mim.oop.elections;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Party> parties = new ArrayList<>();
        parties.add(new Party("Prawo i Sprawiedliwość"));
        parties.add(new Party("Platforma Obywatelska"));
        parties.add(new Party("Konfederacja"));
        parties.add(new Party("Wiosna"));
        parties.add(new Party("Polskie Stronnictwo Ludowe"));

        List<Voter> voters = new ArrayList<>();
        voters.add(new AnarchistVoter("A1"));
        voters.add(new AnarchistVoter("A2"));
        voters.add(new BoredVoter("B1"));
        voters.add(new BoredVoter("B2"));
        voters.add(new StudentVoter("S1"));
        voters.add(new FeministVoter("F1"));
        voters.addAll(generateVoters(8000000, parties));

        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate("Andrzej Sebastian", "DUDA", Candidate.Sex.MALE, Optional.of(parties.get(0))));
        candidates.add(new Candidate("Rafał Kazimierz", "TRZASKOWSKI", Candidate.Sex.MALE, Optional.of(parties.get(1))));
        candidates.add(new Candidate("Szymon Franciszek", "HOŁOWNIA", Candidate.Sex.MALE, Optional.empty()));
        candidates.add(new Candidate("Krzysztof", "BOSAK", Candidate.Sex.MALE, Optional.of(parties.get(2))));
        candidates.add(new Candidate("Władysław Marcin", "KOSINIAK-KAMYSZ", Candidate.Sex.MALE, Optional.of(parties.get(4))));
        candidates.add(new Candidate("Robert", "BIEDROŃ", Candidate.Sex.MALE, Optional.of(parties.get(3))));
        candidates.add(new Candidate("Stanisław Józef", "ŻÓŁTEK", Candidate.Sex.MALE, Optional.empty()));
        candidates.add(new Candidate("Marek", "JAKUBIAK", Candidate.Sex.MALE, Optional.of(parties.get(  0))));
        candidates.add(new Candidate("Paweł Jan", "TANAJNO", Candidate.Sex.MALE, Optional.empty()));
        candidates.add(new Candidate("Waldemar Włodzimierz", "WITKOWSKI", Candidate.Sex.MALE, Optional.of(parties.get(0))));
        candidates.add(new Candidate("Mirosław Mariusz", "PIOTROWSKI", Candidate.Sex.MALE, Optional.of(parties.get(0))));

        Collections.shuffle(voters);
        Collections.shuffle(parties);
        Collections.shuffle(candidates);

        Elections elections = new Elections(candidates, voters);
        elections.holdElections();
        System.out.println(elections.giveResults());
    }

    private static List<Voter> generateVoters(int amount, List<Party> parties) {
        Random rng = new Random();
        List<Voter> voters = new ArrayList<>(amount);

        while (amount-- > 0) {
            switch (rng.nextInt(7)) {
                case 0 -> voters.add(new StudentVoter("S" + amount));
                case 1 -> voters.add(new AnarchistVoter("A" + amount));
                case 2 -> voters.add(new FeministVoter("F" + amount));
                case 3 -> voters.add(new BoredVoter("B" + amount));
                case 4, 5, 6 -> voters.add(new PartyVoter("P" + amount, parties.get(rng.nextInt(parties.size()))));
                default -> throw new IllegalStateException("Unexpected value: " + rng.nextInt(6));
            }
        }

        return voters;
    }
}
