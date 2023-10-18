package project.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {
    FORBIDDEN_CHARACTERS_TXT("forbidden.characters.txt");

    private final String code;
}