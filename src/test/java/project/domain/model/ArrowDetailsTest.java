package project.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import project.domain.enums.BalisesEnum;
import project.domain.enums.TiretsEnum;

@ExtendWith(MockitoExtension.class)
class ArrowDetailsTest {

    @Test
    void testCreationObjet_sansTirets() {
        ArrowDetails arrowDetails = new ArrowDetails(">>>");
        Assertions.assertEquals(3, arrowDetails.getNombreBalises());
        Assertions.assertEquals(0, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.SANS_TIRETS, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_FERMANTE, arrowDetails.getTypeBalise());
    }

    @Test
    void testCreationObjet_avecTirets() {
        ArrowDetails arrowDetails = new ArrowDetails(">~~~~");
        Assertions.assertEquals(1, arrowDetails.getNombreBalises());
        Assertions.assertEquals(4, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.TILDE, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_FERMANTE, arrowDetails.getTypeBalise());
    }

    @Test
    void testCreationObjet_avecTiretsDifferents() {
        ArrowDetails arrowDetails = new ArrowDetails("<<===~--");
        Assertions.assertEquals(2, arrowDetails.getNombreBalises());
        Assertions.assertEquals(3, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.EQUAL, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_OUVRANTE, arrowDetails.getTypeBalise());
    }

    @Test
    void testCalculateTiretModifier_dash() {
        ArrowDetails arrowDetails = new ArrowDetails(">----");
        Assertions.assertEquals(1, arrowDetails.getNombreBalises());
        Assertions.assertEquals(4, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.DASH, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_FERMANTE, arrowDetails.getTypeBalise());
        Assertions.assertEquals(5, arrowDetails.calculateScore());
    }

    @Test
    void testCalculateTiretModifier_equal() {
        ArrowDetails arrowDetails = new ArrowDetails(">====");
        Assertions.assertEquals(1, arrowDetails.getNombreBalises());
        Assertions.assertEquals(4, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.EQUAL, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_FERMANTE, arrowDetails.getTypeBalise());
        Assertions.assertEquals(10, arrowDetails.calculateScore());
    }

    @Test
    void testCalculateTiretModifier_tilde() {
        ArrowDetails arrowDetails = new ArrowDetails(">~~~~~");
        Assertions.assertEquals(1, arrowDetails.getNombreBalises());
        Assertions.assertEquals(5, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.TILDE, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_FERMANTE, arrowDetails.getTypeBalise());
        Assertions.assertEquals(3, arrowDetails.calculateScore());
    }

    @Test
    void testCalculateBaliseModifier_puissance() {
        ArrowDetails arrowDetails = new ArrowDetails(">>--------");
        Assertions.assertEquals(2, arrowDetails.getNombreBalises());
        Assertions.assertEquals(8, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.DASH, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_FERMANTE, arrowDetails.getTypeBalise());
        Assertions.assertEquals(100, arrowDetails.calculateScore());
    }

    @Test
    void testCalculateBaliseModifier_positif() {
        ArrowDetails arrowDetails = new ArrowDetails(">--------");
        Assertions.assertEquals(1, arrowDetails.getNombreBalises());
        Assertions.assertEquals(8, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.DASH, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_FERMANTE, arrowDetails.getTypeBalise());
        Assertions.assertEquals(9, arrowDetails.calculateScore());
    }

    @Test
    void testCalculateBaliseModifier_negatif() {
        ArrowDetails arrowDetails = new ArrowDetails("<--------");
        Assertions.assertEquals(1, arrowDetails.getNombreBalises());
        Assertions.assertEquals(8, arrowDetails.getNombreTirets());
        Assertions.assertEquals(TiretsEnum.DASH, arrowDetails.getTypeTiret());
        Assertions.assertEquals(BalisesEnum.BALISE_OUVRANTE, arrowDetails.getTypeBalise());
        Assertions.assertEquals(-9, arrowDetails.calculateScore());
    }
}
