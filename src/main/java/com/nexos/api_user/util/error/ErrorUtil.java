package com.nexos.api_user.util.error;

import com.nexos.api_user.util.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ErrorUtil {

    private final MessageService messageService;

    public EntityError businessError(String errorKey, Class<?> entityClass) {
        return new EntityError(
                ErrorStatus.BAD_REQUEST,
                messageService.getMessage(errorKey),
                ErrorCode.NO_VALID_BUSINESS_RULE,
                entityClass.getSimpleName());
    }

    public EntityError notFoundError(String errorKey, Class<?> entityClass) {
        return new EntityError(
                ErrorStatus.NOT_FOUND,
                messageService.getMessage(errorKey),
                ErrorCode.NOT_FOUND,
                entityClass.getSimpleName()
        );
    }

    public EntityError internalError(String errorKey, Class<?> entityClass) {
        return new EntityError(
                ErrorStatus.INTERNAL_SERVER_ERROR,
                messageService.getMessage(errorKey),
                ErrorCode.INTERNAL_SERVER_ERROR,
                entityClass.getSimpleName());
    }
}
