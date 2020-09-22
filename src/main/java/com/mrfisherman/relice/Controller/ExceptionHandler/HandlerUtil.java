package com.mrfisherman.relice.Controller.ExceptionHandler;

import java.util.HashMap;

public class HandlerUtil {

    public static HashMap<String, String> createResponseWithMessageAndError(String message, Exception error) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("error", error.getClass().getSimpleName());
        return response;
    }
}
