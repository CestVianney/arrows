package project.domain.exceptions;

import project.domain.enums.ErrorEnum;

public class BadRequestException extends BaseException{
    public BadRequestException(ErrorEnum error) {
        super(error);
    }
}
