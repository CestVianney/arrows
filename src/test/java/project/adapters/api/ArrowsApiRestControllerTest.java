package project.adapters.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import project.adapters.api.dto.JsonArrow;
import project.adapters.api.dto.JsonResponse;
import project.domain.exceptions.BadRequestException;
import project.domain.model.InputStreamElement;
import project.domain.service.ArrowsService;


import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArrowsApiRestControllerTest {

    @InjectMocks
    private ArrowsApiRestController controller;

    @Mock
    private ArrowsService arrowsService;

    @Test
    void calculateScoreFromJson_KO() {
        JsonArrow jsonArrow = new JsonArrow();
        jsonArrow.setArrowSentence("<==a-");
        InputStreamElement objectReturned = new InputStreamElement();
        objectReturned.setInputValid(false);

        when(arrowsService.countFinalScore(any())).thenReturn(objectReturned);

        assertThrows(BadRequestException.class, () -> controller.calculateScoreFromJson(jsonArrow));
    }

    @Test
    void calculateScoreFromJson_OK() {
        InputStreamElement objectReturned = new InputStreamElement();
        objectReturned.setInput(">----");
        objectReturned.setScore(5);
        objectReturned.setInputValid(true);

        when(arrowsService.countFinalScore(any())).thenReturn(objectReturned);
        ResponseEntity<JsonResponse> response = controller.calculateScoreFromJson(new JsonArrow());

        Assertions.assertEquals(objectReturned.getScore(), response.getBody().getScore());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void calculateScoreFromTxtToCsv_EmptyFile() throws IOException {
//        MultipartFile mpf = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);
//        ResponseEntity<byte[]> response = controller.calculateScoreFromTxtToCsv(mpf);
//
//        assertNotNull(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(MediaType.parseMediaType("text/csv"), response.getHeaders().getContentType());
//        assertEquals(1, new String(response.getBody()).split("\n").length);
    }

    @Test
    void calculateScoreFromTxtToCsv_OK() throws IOException {
//        MultipartFile mpf = new MockMultipartFile("file", "nominal.txt", "text/plain", ">----\n>>---".getBytes());
//        InputStreamElement inputStreamElementFirst = new InputStreamElement();
//        inputStreamElementFirst.setScore(5);
//        inputStreamElementFirst.setInput(">----");
//        inputStreamElementFirst.setInputValid(true);
//        InputStreamElement inputStreamElementSecond = new InputStreamElement();
//        inputStreamElementSecond.setScore(25);
//        inputStreamElementSecond.setInput(">>---");
//        inputStreamElementSecond.setInputValid(true);
//
//        when(arrowsService.countFinalScore(">----")).thenReturn(inputStreamElementFirst);
//        when(arrowsService.countFinalScore(">>---")).thenReturn(inputStreamElementSecond);
//        ResponseEntity<byte[]> response = controller.calculateScoreFromTxtToCsv(mpf);
//
//        assertNotNull(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(MediaType.parseMediaType("text/csv"), response.getHeaders().getContentType());
//        assertEquals(2, new String(response.getBody()).split("\n").length);
//        assertEquals(">----,5\r\n>>---,25\r\n", new String(response.getBody()));
    }

}
