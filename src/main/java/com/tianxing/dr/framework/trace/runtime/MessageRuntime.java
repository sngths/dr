package com.tianxing.dr.framework.trace.runtime;

import java.util.Optional;

/**
 * @author tianxing
 */
public class MessageRuntime {


    public static void setErrorMessage(String message){
        Runtime.getContext().put("errorMessage",message);
    }

    public static Optional<String> getErrorMessage(){
        return Runtime.getContext().get("errorMessage",String.class);
    }
}
