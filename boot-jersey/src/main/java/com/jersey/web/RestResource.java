package com.jersey.web;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by Z先生 on 2016/12/12.
 */
@Path("/")
@Component
public class RestResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public String hello() {
        return "hello world";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRes(){
        Response.ResponseBuilder response=Response.ok();
        response.header("Access-Control-Allow-Origin","");

        Date date=new Date(System.currentTimeMillis()+3000);
        response.expires(date);
        return response.build();
    }

}
