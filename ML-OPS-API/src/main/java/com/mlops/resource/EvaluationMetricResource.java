package com.mlops.resource;

import com.mlops.exception.ModelDeprecatedException;
import com.mlops.model.*;
import com.mlops.store.DataStore;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

public class EvaluationMetricResource {

    private String modelId;

    public EvaluationMetricResource(String modelId) {
        this.modelId = modelId;
    }

    @GET
    public Response getAll() {
        return Response.ok(
                DataStore.metrics.getOrDefault(modelId, new ArrayList<>())
        ).build();
    }

    @POST
    public Response add(EvaluationMetric metric) {

        MachineLearningModel model = DataStore.models.get(modelId);

        if (model == null) return Response.status(404).build();

        if ("DEPRECATED".equalsIgnoreCase(model.getStatus())) {
            throw new ModelDeprecatedException();
        }

        metric.setId(UUID.randomUUID().toString());
        metric.setTimestamp(System.currentTimeMillis());

        DataStore.metrics
                .computeIfAbsent(modelId, k -> new ArrayList<>())
                .add(metric);

        model.setLatestAccuracy(metric.getAccuracyScore());

        return Response.status(201).entity(metric).build();
    }
}