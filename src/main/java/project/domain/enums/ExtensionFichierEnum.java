package project.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExtensionFichierEnum {
    CSV(".csv"),
    TXT(".txt");
    private final String extension;
}
