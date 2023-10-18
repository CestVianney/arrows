package project.domain.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrowsUtils {
    private ArrowsUtils(){}
    public static List<String> getValidArrows(String input) {
        List<String> validArrows = new ArrayList<>();
        while (input.length() != 0) {
            char c = input.charAt(0);
            boolean isActualCharABalise = c == '<' || c == '>';
            if (isActualCharABalise) {

                int j = 1;
                boolean isPremiereBaliseOuvrante = input.charAt(0) == '<';
                boolean isContinue = true;

                while (isContinue && j < input.length()) {
                    boolean isActualCharBaliseFermante = input.charAt(j) == '>';
                    boolean isActualCharBaliseOuvrante = input.charAt(j) == '<';
                    boolean isActualCharPoint = input.charAt(j) == '.';
                    boolean isPreviousBaliseFermante = input.charAt(j - 1) == '>';
                    boolean isPreviousBaliseOuvrante = input.charAt(j - 1) == '<';
                    boolean isNextBaliseFermante = j+1 < input.length() && input.charAt(j+1) == '>';
                    boolean isFinishedBidirectional = isPremiereBaliseOuvrante && !isNextBaliseFermante;
                    boolean isStillEndingBidirectional = isPremiereBaliseOuvrante && isNextBaliseFermante;

                    if (isActualCharBaliseFermante) {
                        if (isPreviousBaliseFermante){
                            j++;
                        } else if (isFinishedBidirectional) {
                            j++;
                            isContinue = false;
                        } else if (isStillEndingBidirectional) {
                            j++;
                        } else {
                            isContinue = false;
                        }
                    } else if (isActualCharBaliseOuvrante && !isPreviousBaliseOuvrante) {
                        isContinue = false;
                    } else if (isActualCharPoint) {
                        isContinue = false;
                    } else {
                        j++;
                    }
                }
                String identifiedArrow = input.substring(0, j);
                boolean isFlecheBidirectionnelle = isPremiereBaliseOuvrante && identifiedArrow.charAt(identifiedArrow.length()-1) == '>';
                if (!isFlecheBidirectionnelle) {
                    validArrows.add(identifiedArrow);
                }
                input = input.substring(j);
            } else {
                input = input.substring(1);
            }
        }
        return validArrows;
    }
}
