package com.aalzatea.todo.exceptions.messages;

import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Value
public class ErrorMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -5995088615882516471L;

    String code;

    String message;

    Date errorDate;
}
