package com.mlops.mapper;

import com.mlops.exception.ModelDeprecatedException;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

import java.util.HashMap;
import java.util.Map;

@Provider
public class ModelDeprecatedMapper implements ExceptionMapper<ModelDeprecatedException> {

    @Override
    public Response toResponse(ModelDeprecatedException e) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "Model deprecated");

        return Response.status(403)
                .entity(error)
                .build();
    }
}