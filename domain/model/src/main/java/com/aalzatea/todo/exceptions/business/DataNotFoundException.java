package com.aalzatea.todo.exceptions.business;

import com.aalzatea.todo.exceptions.ApplicationException;
import lombok.Getter;

import java.io.Serial;

@Getter
public final class DataNotFoundException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = -5944305367401264464L;

    public enum Type {
        DATA_NOT_FOUND_EXCEPTION("404", "domain.msg.data_not_found.general"),
        DATA_FOR_SOMETHING_NOT_FOUND_EXCEPTION("404", "domain.msg.data_not_found.something_not_found"),
        DATA_FOR_COLLECTION_NOT_FOUND_EXCEPTION("404", "domain.msg.data_not_found.collection_not_found"),
        DATA_WITH_ID_NOT_FOUND_EXCEPTION("404", "domain.msg.data_not_found.id_not_found");

        private final String code;

        private final String message;

        Type(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public DataNotFoundException build() {
            return new DataNotFoundException(this, (Object[]) null);
        }

        public DataNotFoundException buildWithMsgParameters(Object... messageParameters) {
            return new DataNotFoundException(this, messageParameters);
        }
    }

    private final Type type;

    private DataNotFoundException(Type type, Object... messageParameters) {
        super(type.code, type.message, null, messageParameters);
        this.type = type;
    }
}
