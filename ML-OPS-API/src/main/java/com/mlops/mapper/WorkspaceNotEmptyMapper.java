package com.mlops.mapper;

import com.mlops.exception.WorkspaceNotEmptyException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

import java.util.HashMap;
import java.util.Map;

@Provider
public class WorkspaceNotEmptyMapper implements ExceptionMapper<WorkspaceNotEmptyException> {

    @Override
    public Response toResponse(WorkspaceNotEmptyException e) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "Workspace not empty");

        return Response.status(409)
                .entity(error)
                .build();
    }
}