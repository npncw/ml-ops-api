package com.mlops.resource;

import com.mlops.exception.LinkedWorkspaceNotFoundException;
import com.mlops.model.*;
import com.mlops.store.DataStore;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

@Path("/models")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModelResource {

    @GET
    public Response getAll(@QueryParam("status") String status) {

        List<MachineLearningModel> list = new ArrayList<>(DataStore.models.values());

        if (status != null) {
            list = list.stream()
                    .filter(m -> m.getStatus() != null && m.getStatus().equalsIgnoreCase(status))
                    .collect(java.util.stream.Collectors.toList());
        }

        return Response.ok(list).build();
    }

    @POST
    public Response create(MachineLearningModel model) {

        if (!DataStore.workspaces.containsKey(model.getWorkspaceId())) {
            throw new LinkedWorkspaceNotFoundException();
        }

        model.setId("MOD-" + UUID.randomUUID());
        DataStore.models.put(model.getId(), model);

        DataStore.workspaces.get(model.getWorkspaceId())
                .getModelIds().add(model.getId());

        return Response.status(201).entity(model).build();
    }

    @Path("/{modelId}/metrics")
    public EvaluationMetricResource metrics(@PathParam("modelId") String modelId) {
        return new EvaluationMetricResource(modelId);
    }
}