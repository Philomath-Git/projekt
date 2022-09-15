package your.name.here;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


import com.aerospike.client.AerospikeClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import your.name.here.domain.Action;
import your.name.here.domain.Aggregate;
import your.name.here.domain.AggregatesQueryResult;
import your.name.here.domain.UserProfileResult;
import your.name.here.domain.UserTagEvent;

// Import the Aerospike client library and other modules
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.cdt.MapOrder;
import com.aerospike.client.cdt.MapPolicy;
import com.aerospike.client.cdt.MapOperation;
import com.aerospike.client.policy.RecordExistsAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.util.concurrent.TimeUnit;



@RestController
public class EchoClient {

    @Autowired
    private ObjectMapper objectMapper;
    //UserProfileResult userProfile = ...
    //String serializedUserProfile = objectMapper.writeValueAsString(userProfile);
    //userProfile = objectMapper.readValue(serializedUserProfile, UserProfileResult.class);



    private static final Logger log = LoggerFactory.getLogger(EchoClient.class);

    //

    private Map<String, UserProfile> memory = new HashMap<>();


    // Define base policy
    Policy policy = new Policy();

    AerospikeClient client = new AerospikeClient("127.0.0.1", 3000);

    // Initialize a write policy
    WritePolicy writePolicy = new WritePolicy(policy);

    // Create a key in namespace "sandbox" and set "ufodata"
    //Key key = new Key("test", "ufodata", 5001);

    public EchoClient(){
        //TimeUnit.SECONDS.sleep(1);

        Key key = new Key("test", null, "EoFG9TIruX4CXWTqfxrk");

        try{
            //Record record = client.get(null, key);
            Record record2 = client.get(null, key);

            if(record2 != null){



                //UserTagEvent values;
                //values = objectMapper.readValue(record, UserTagEvent.class);
                System.out.format("--------- \n");

                System.out.format("Key: %s", key.userKey );
                System.out.format(" Records: %s", record2);
                System.out.format("\n Records: %s", record2.bins);

            }
            else{
                System.out.format("Key: %s\nRecord not found", key.userKey);
            }
        }
        catch (AerospikeException ae) {

        }





        // Create the bins as Bin("binName", value)
        //Bin occurred = new Bin("occurred", 20220531);
        //Bin reported = new Bin("reported", 20220601);
        //Bin posted = new Bin("posted", 20220601);
        //Bin location = new Bin("location", Value.getAsGeoJSON(generatePoint(42.2808,83.7430)));
        //client.put(writePolicy, key, occurred, reported, posted, location);
    }




    @PostMapping("/user_tags")
    public ResponseEntity<Void> addUserTag(@RequestBody(required = false) UserTagEvent userTag) {
        //UserTagEvent userTag = new UserTagEvent(requestbody)
        try {
            String aktion = userTag.action().toString();
            Key key = new Key("test", null, userTag.cookie());
            //Record record = client.get(null, key);
            try{
                Record record = client.get(null, key);
                if(record != null){
                    //UserProfileResult userprofile = objectMapper.readValue(record, UserProfileResult.class);

                }
                else{
                    List<UserTagEvent> views = new ArrayList<UserTagEvent>();
                    List<UserTagEvent> buys = new ArrayList<UserTagEvent>();
                    String cookie = userTag.cookie();


                    if (aktion.equals("VIEW")){
                        //String meinJSON = objectMapper.writeValueAsString(userTag);
                        //List<UserTagEvent> views = null;

                        views.add(userTag);
                        buys.add(userTag);
                        //String meinJSON = objectMapper.writeValueAsString(views);
                        //Bin meinBIN = new Bin("Views", meinJSON);

                        //Bin meinBIN = new Bin("Views", meinJSON);
                        //
                        //client.put(writePolicy, key, meinBIN);
                    }

                    if (aktion.equals("BUY")){
                        //List<UserTagEvent> buys = null;
                        buys.add(userTag);
                        views.add(userTag);
                        //String meinJSON = objectMapper.writeValueAsString(buys);
                        //Bin meinBIN = new Bin("Buys", meinJSON);

                        //Key key = new Key("test", null, 123);
                        //client.put(writePolicy, key, meinBIN);

                        //String meinJSON = objectMapper.writeValueAsString(userTag);
                        //Bin meinBIN = new Bin("Buys", meinJSON);
                        //Key key = new Key("test", null, userTag.cookie());
                        //client.put(writePolicy, key, meinBIN);
                    }
                    UserProfileResult userprofile = new UserProfileResult(cookie, views, buys);
                    String meinJSON = objectMapper.writeValueAsString(userprofile);
                    Bin meinBIN = new Bin("userprofile", meinJSON);
                    client.put(writePolicy, key, meinBIN);
                }

            }
            catch (AerospikeException ae) {
                System.out.format("Error: %s", ae.getMessage());
            }






            //String meinJSON = objectMapper.writeValueAsString(userTag);
            //Bin meinBIN = new Bin("spalte", meinJSON);
            //Key key = new Key("test", null, userTag.cookie());
            //client.put(writePolicy, key, meinBIN);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String cookie = userTag.cookie();
        var userProfile = memory.get(cookie);

        if (userProfile == null) {
            userProfile = new UserProfile();
            memory.put(cookie, userProfile);
        }

        //userProfile.add(userTag);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/user_profiles/{cookie}")
    public ResponseEntity<UserProfileResult> getUserProfile(@PathVariable("cookie") String cookie,
            @RequestParam("time_range") String timeRangeStr,
            @RequestParam(defaultValue = "200") int limit,
            @RequestBody(required = false) UserProfileResult expectedResult) {

        return ResponseEntity.ok(expectedResult);
    }

    @PostMapping("/aggregates")
    public ResponseEntity<AggregatesQueryResult> getAggregates(@RequestParam("time_range") String timeRangeStr,
            @RequestParam("action") Action action,
            @RequestParam("aggregates") List<Aggregate> aggregates,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "brand_id", required = false) String brandId,
            @RequestParam(value = "category_id", required = false) String categoryId,
            @RequestBody(required = false) AggregatesQueryResult expectedResult) {

        return ResponseEntity.ok(expectedResult);
    }
}
