package com.mlops.store;

import com.mlops.model.*;

import java.util.*;

public class DataStore {
    public static Map<String, MLWorkspace> workspaces = new HashMap<>();
    public static Map<String, MachineLearningModel> models = new HashMap<>();
    public static Map<String, List<EvaluationMetric>> metrics = new HashMap<>();
}