package cenidet.cc.publictransit.ws;

import java.sql.SQLException;
import java.util.ArrayList;

import org.cenidet.cc.publictransit.dto.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.cenidet.cc.publictransit.dao.StopDAO;
import org.cenidet.cc.publictransit.dto.Stop;
//import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
//import org.json.JSONException;

// http://localhost:8085/PublicTransitWS/db/paradas/

@Path("paradas")
public class PublicTransitWS {
	
	private StopDAO stopDAO/* = StopDAO.getInstance()*/;
	private Gson gson;
	
	public  PublicTransitWS() {
		// TODO Auto-generated constructor stub
		stopDAO = StopDAO.getInstance();
		gson = new Gson();
	}
	
    @Path("paradasByIdViaje/{id}")
	@GET
	@Produces("application/json")
    public Response getStopsByJourneyId(@PathParam("id") int id) {
        ArrayList<Stop> stops = null;
        try {
            stops = stopDAO.getStopsByJourneyId(id);
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        JSONArray jsonStops = new JSONArray(stops);
        return Response.status(200).entity(jsonStops.toString()).build();
    }
    
    @Path("paradaById/{id}")
    @GET
    @Produces("application/json")
    public Response getStopById(@PathParam("id") int id){
    	Stop parada = null;
    	try{
    		parada = stopDAO.getStopById(id);
    	}catch(SQLException e){
    		System.out.println("Exception: " + e.getMessage());
    	}    	
    	String jsonStop = gson.toJson(parada); 
    	return Response.status(200).entity(jsonStop).build();
    }
    
    @Path("grafo")
	@GET
	@Produces("application/json")
    public Response getVerticesGrafo() {
        ArrayList<Stop> stops = null;
        try {
            stops = stopDAO.getVerticesGrafo();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        JSONArray jsonStops = new JSONArray(stops);
        return Response.status(200).entity(jsonStops.toString()).build();
    }
    
}