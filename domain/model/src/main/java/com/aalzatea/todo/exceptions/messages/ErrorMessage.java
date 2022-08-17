package com.aalzatea.todo.exceptions.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@RequiredArgsConstructor
@Getter
public final class ErrorMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -5995088615882516471L;

    private final String code;

    private final String message;

    private final Date errorDate;
}
