package com.mlops.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

//@Path("/")
//@Produces(MediaType.APPLICATION_JSON)
//public class DiscoveryResource {

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public class DiscoveryResource {

        @GET
        public Response getInfo() {

            Map<String,Object> map = new HashMap<>();

            map.put("version", "v1");
            map.put("admin-name", "Nisali Nawarathne");
            map.put("admin_mail", "nisali@mlops.com");
            map.put("contact", "0777123456");
            map.put("country", "Sri-Lanka");

            return Response.ok(map).build();
        }
    }
//}