package project.domain.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static project.domain.utils.ArrowsUtils.getValidArrows;

@ExtendWith(MockitoExtension.class)
class ArrowsUtilsTest {

    @Test
    void testGetValidArrows_nominal() {
        List<String> validArrows = getValidArrows(">>--=~<--<");
        Assertions.assertEquals(3, validArrows.size());
        Assertions.assertEquals(">>--=~", validArrows.get(0));
        Assertions.assertEquals("<--", validArrows.get(1));
        Assertions.assertEquals("<", validArrows.get(2));
    }

    @Test
    void testGetValidArrows_bidirectionals() {
        List<String> validArrows = getValidArrows("<==>><<<~><<<-->>>");
        Assertions.assertEquals(0, validArrows.size());
    }

    @Test
    void testGetValidArrows_points() {
        List<String> validArrows = getValidArrows("...<=.=>><<.<~><<<-->>>.");
        Assertions.assertEquals(3, validArrows.size());
        Assertions.assertEquals("<=", validArrows.get(0));
        Assertions.assertEquals(">>", validArrows.get(1));
        Assertions.assertEquals("<<", validArrows.get(2));
    }
}
