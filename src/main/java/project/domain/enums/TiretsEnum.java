package project.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TiretsEnum {

    DASH('-'), //pas de modifier
    EQUAL('='), //multiply *2
    TILDE('~'), // divide /2
    SANS_TIRETS(' '); // pas de tiret dans la fleche

    private final char symbole;

    public static TiretsEnum getEnumValue(char value) {
        return Arrays.stream(TiretsEnum.values()).filter(e -> e.symbole == value).findFirst().orElse(SANS_TIRETS);
    }
}
