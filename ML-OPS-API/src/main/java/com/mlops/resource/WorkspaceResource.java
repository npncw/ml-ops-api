package com.mlops.resource;

import com.mlops.exception.WorkspaceNotEmptyException;
import com.mlops.model.MLWorkspace;
import com.mlops.store.DataStore;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.UUID;

@Path("/workspaces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkspaceResource {

    @GET
    public Response getAll() {
        return Response.ok(DataStore.workspaces.values()).build();
    }

    @POST
    public Response create(MLWorkspace ws) {
        ws.setId("WS-" + UUID.randomUUID());
        DataStore.workspaces.put(ws.getId(), ws);
        return Response.status(201).entity(ws).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        System.out.println("ID Fetched:" + id);
        MLWorkspace ws = DataStore.workspaces.get(id);
        if (ws == null) return Response.status(404).build();
        return Response.ok(ws).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {

        MLWorkspace ws = DataStore.workspaces.get(id);

        if (ws == null) return Response.status(404).build();

        if (!ws.getModelIds().isEmpty()) {
            throw new WorkspaceNotEmptyException();
        }

        DataStore.workspaces.remove(id);
        return Response.noContent().build();
    }
}