package cg.rcksoft.test;

import cg.rcksoft.model.Personne;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Ricken BAZOLO
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Personne p = new Personne();
        p.setId(5);
        p.setNom("BAZOLO");
        p.setNum("00242066511786");
        
        Client client = Client.create(new DefaultClientConfig());
        
        ObjectMapper mapper = new ObjectMapper();
        
        URI uri = UriBuilder.fromUri("http://localhost:8080/app/rest/").build();
        
        
        ClientResponse response2 = client.resource(uri).path("personne")
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, mapper.writeValueAsString(p));
        
        
        ClientResponse response = client.resource(uri).path("personne")
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        
        String httpresponse = response.getEntity(String.class);
        
        System.out.println(httpresponse);
        
        
        Personne[] personnes = mapper.readValue(httpresponse, Personne[].class);
        
        for(Personne perso : personnes){
            System.out.println("ID: "+perso.getId());
            System.out.println("Nom: "+perso.getNom());
            System.out.println("Num: "+perso.getNum());
            System.out.println("Age: "+perso.getAge());
            System.out.println("==================================");
        }
        
        
    }
    
}
