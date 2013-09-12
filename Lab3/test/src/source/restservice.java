package source;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import com.sun.jersey.api.json.JSONWithPadding;

@Path("mypath")
public class restservice {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public restservice() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of restservice
     * @return an instance of String
     */
    
   
/*    @GET
    @Produces("application/json")
    public String getJson() {
    	JSONObject json = new JSONObject();
    	json.put("city", "KC");
    	json.put("State", "MO");
    	json.put("Country", "USA");
    	String output = json.toString();
    	return output;
    	 //return "Hell Json";
    }*/
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("greeting/{username}")
    public JSONWithPadding sayHello (@QueryParam("callback") String callback,
                                 @PathParam("username") String username) {
       
    	JSONObject json = new JSONObject();
    	json.put("name",username);
    	json.put("city", "KC");
    	json.put("State", "MO");
    	json.put("Country", "USA");
    	String output = json.toString();
    
        return new JSONWithPadding(output,callback);
    }
    

    /**
     * PUT method for updating or creating an instance of restservice
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    
   /* public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, java.io.IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // This should be added in response to both the preflight and the actual request
        response.addHeader("Access-Control-Allow-Origin", "*");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Credentials", "true");
        }

        chain.doFilter(req, resp);
    }*/

}



