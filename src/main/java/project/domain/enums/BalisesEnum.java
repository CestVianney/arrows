package project.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BalisesEnum {
    BALISE_OUVRANTE('<', -1.0),
    BALISE_FERMANTE('>', 1.0);

    private final char balise;
    private final double multiplicateur;

    public static BalisesEnum getEnumValue(char value) {
        return Arrays.stream(BalisesEnum.values()).filter(e -> e.balise == value).findFirst().orElse(null);
    }
}
