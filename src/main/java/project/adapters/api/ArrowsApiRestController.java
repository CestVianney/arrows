package project.adapters.api;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.adapters.api.dto.JsonArrow;
import project.adapters.api.dto.JsonResponse;
import project.domain.enums.ErrorEnum;
import project.domain.exceptions.BadRequestException;
import project.domain.model.InputStreamElement;
import project.domain.service.ArrowsService;

@RestController
@RequestMapping(path = "arrows")
@Api(value = "arrows", tags = "arrows")
@RequiredArgsConstructor
public class ArrowsApiRestController {
    private final ArrowsService arrowsService;

    @ApiOperation("Calcul du score d'une flèche au format json")
    @PostMapping(value = "/arrows", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> calculateScoreFromJson(@RequestBody JsonArrow inputData) {
        InputStreamElement inputStreamElement = arrowsService.countFinalScore(inputData.getArrowSentence());
        if(!inputStreamElement.isInputValid()) {
            throw new BadRequestException(ErrorEnum.FORBIDDEN_CHARACTERS_TXT);
        }
        JsonResponse outputData = new JsonResponse();
        outputData.setScore((int)inputStreamElement.getScore());
        return new ResponseEntity<>(outputData, HttpStatus.OK);
    }
}