package com.mlops.mapper;



import com.mlops.exception.*;

import jakarta.ws.rs.core.*;

import jakarta.ws.rs.ext.*;

import java.util.HashMap;
import java.util.Map;

@Provider

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {

        e.printStackTrace();
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal server error");

        return Response.status(500)
                .entity(error)
                .build();
    }

}
