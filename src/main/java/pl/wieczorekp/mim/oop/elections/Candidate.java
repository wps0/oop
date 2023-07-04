package pl.wieczorekp.mim.oop.elections;

import java.util.Optional;

public record Candidate(String name, String surname, Sex sex, Optional<Party> party) {
    public enum Sex {
        MALE, FEMALE;
    }
}
