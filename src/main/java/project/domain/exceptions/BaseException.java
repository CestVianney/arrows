package project.domain.exceptions;

import lombok.Getter;
import project.domain.enums.ErrorEnum;

@Getter
public class BaseException extends RuntimeException {

    private ErrorEnum error;


    public BaseException(ErrorEnum error) {
        super(error.getCode());
        this.error = error;
    }
}
