package project.domain.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import project.domain.model.InputStreamElement;

@ExtendWith(MockitoExtension.class)
class ArrowsServiceImplTest {

    @InjectMocks
    private ArrowsServiceImpl arrowsService;

    @Test
    void testIsInputValid_OK_firstSample() {
        InputStreamElement inputStreamElement = arrowsService.countFinalScore("<----~=.");
        Assertions.assertTrue(inputStreamElement.isInputValid());
    }

    @Test
    void testIsInputValid_OK_secondSample() {
        InputStreamElement inputStreamElement = arrowsService.countFinalScore("<<==~...~>>");
        Assertions.assertTrue(inputStreamElement.isInputValid());
    }

    @Test
    void testIsInputValid_KO() {
        InputStreamElement inputStreamElementA = arrowsService.countFinalScore("<--a~=.");
        InputStreamElement inputStreamElementSpacebar = arrowsService.countFinalScore("<--~=. ");
        InputStreamElement inputStreamElementWtf = arrowsService.countFinalScore("(┛ಠ_ಠ)┛彡┻━┻");

        Assertions.assertFalse(inputStreamElementA.isInputValid());
        Assertions.assertFalse(inputStreamElementSpacebar.isInputValid());
        Assertions.assertFalse(inputStreamElementWtf.isInputValid());
    }

    @Test
    void testCalculateScore_plusieursResultatsPositifs() {
        InputStreamElement inputStreamElement = arrowsService.countFinalScore(">-->==>~");
        Assertions.assertEquals(10, inputStreamElement.getScore());
    }

    @Test
    void testCalculateScore_plusieursResultatsPositifsEtNegatifs() {
        InputStreamElement inputStreamElement = arrowsService.countFinalScore(".>-=-.<=.=>~");
        Assertions.assertEquals(-1, inputStreamElement.getScore());
    }
}
