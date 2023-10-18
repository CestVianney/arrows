package project.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputStreamElement {
    private String input;
    private double score;
    private boolean isInputValid;
}
