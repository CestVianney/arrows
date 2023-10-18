package project.domain.model;

import lombok.Getter;
import lombok.Setter;
import project.domain.enums.BalisesEnum;
import project.domain.enums.TiretsEnum;

@Getter
@Setter
public class ArrowDetails {
    private double nombreBalises;
    private double nombreTirets;
    private TiretsEnum typeTiret;
    private BalisesEnum typeBalise;

    public ArrowDetails(String arrow) {
        int i = 1;
        this.nombreBalises = 1;
        this.nombreTirets = 0;
        boolean isContinue = true;
        while (isContinue && i < arrow.length()) {
            if (arrow.charAt(i) == arrow.charAt(0)) {
                this.nombreBalises++;
            } else if (arrow.charAt(i) == arrow.charAt((int) nombreBalises)) {
                this.nombreTirets++;
            } else {
                isContinue = false;
            }
            i++;
        }

        this.typeBalise = BalisesEnum.getEnumValue(arrow.charAt(0));
        this.typeTiret = 0 == this.nombreTirets ? TiretsEnum.SANS_TIRETS : TiretsEnum.getEnumValue(arrow.charAt((int) this.nombreBalises));
    }

    public double calculateScore() {
        double scoreBrut = nombreBalises + nombreTirets;
        return this.calculateBaliseModifier(this.calculateTiretModifier(scoreBrut));
    }

    private double calculateTiretModifier(double scoreBrut) {
        return switch (typeTiret) {
            case DASH, SANS_TIRETS -> scoreBrut;
            case EQUAL -> scoreBrut * 2.0;
            case TILDE -> scoreBrut / 2.0;
        };
    }

    private double calculateBaliseModifier(double score) {
        return typeBalise.getMultiplicateur() * Math.pow(score, this.nombreBalises);
    }
}