package project.domain.service.impl;

import org.springframework.stereotype.Service;
import project.domain.model.ArrowDetails;
import project.domain.model.InputStreamElement;
import project.domain.service.ArrowsService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static project.domain.utils.ArrowsUtils.getValidArrows;

@Service
public class ArrowsServiceImpl implements ArrowsService {

    @Override
    public InputStreamElement countFinalScore(String input) {
        InputStreamElement inputStreamElement = new InputStreamElement();
        inputStreamElement.setInputValid(isInputValid(input));
        inputStreamElement.setInput(input);
        inputStreamElement.setScore(calculateScore(getValidArrows(input)));
        return inputStreamElement;
    }

    private boolean isInputValid(String input) {
        return input.matches("[<>\\-.=~]+");
    }

    private double calculateScore(List<String> arrows) {
        AtomicReference<Double> scoreTotal = new AtomicReference<>(0.0);
        arrows.forEach(arrow -> {
            ArrowDetails arrowDetails = new ArrowDetails(arrow);
            scoreTotal.updateAndGet(v -> v + arrowDetails.calculateScore());
        });
        return scoreTotal.get();
    }
}
