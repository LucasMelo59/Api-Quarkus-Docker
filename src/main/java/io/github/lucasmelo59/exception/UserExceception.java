package io.github.lucasmelo59.exception;

import javax.ws.rs.core.Response;

public class UserExceception extends RuntimeException {
    private String error_Message;
    private Response.Status status;

    public UserExceception(String error_Message, Response.Status status) {
        super(error_Message);
        this.status = status;
    }


}
