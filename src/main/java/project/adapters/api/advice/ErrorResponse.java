package project.adapters.api.advice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ErrorResponse")
@RequiredArgsConstructor
public class ErrorResponse {

    @ApiModelProperty("Message d'erreur")
    private final String message;
}