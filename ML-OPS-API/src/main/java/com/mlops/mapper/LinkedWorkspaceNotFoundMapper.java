package com.mlops.mapper;

import com.mlops.exception.*;

import jakarta.ws.rs.core.*;

import jakarta.ws.rs.ext.*;

import java.util.HashMap;
import java.util.Map;

@Provider
public class LinkedWorkspaceNotFoundMapper implements ExceptionMapper<LinkedWorkspaceNotFoundException> {

    @Override
    public Response toResponse(LinkedWorkspaceNotFoundException e) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "Workspace not found");

        return Response.status(422)
                .entity(error)
                .build();
    }
}