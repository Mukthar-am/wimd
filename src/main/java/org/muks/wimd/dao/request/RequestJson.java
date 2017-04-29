package org.muks.wimd.dao.request;

import com.fasterxml.jackson.databind.JsonNode;
import org.muks.wimd.dao.geo.Location;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 26/04/17
 *
 *  - Request pojo with some of the validations done
 */
public class RequestJson {
    private BigDecimal rangeMin = new BigDecimal(-90);
    private BigDecimal rangeMax = new BigDecimal(90);

//    private BigDecimal latitude = new BigDecimal(0);
//    private BigDecimal longitude = new BigDecimal(0);
//    private double accuracy = 0.7;
    private boolean isLatitudeInRange = false;
    private boolean isLongitudeInRange = false;
    private boolean isValidRequest = false;

    private int radius = 500;
    private int limit = 10;

    private Location location;

    /**
     * Having parse method instead of overloading class constructor and is a bad practise to implement too much of logic within constructor
     * @param requestJsonNode
     * @return
     */
    public RequestJson parse(JsonNode requestJsonNode) {

//        this.latitude = requestJsonNode.get("latitude").decimalValue();
//        this.longitude = requestJsonNode.get("longitude").decimalValue();
//        this.accuracy = requestJsonNode.get("accuracy").asDouble();


        this.location
                = new Location(requestJsonNode.get("latitude").decimalValue(),
                requestJsonNode.get("longitude").decimalValue() );


        /** Check on the range being within +/- 90 (inclusive) */

        if (this.location.getLatitude().compareTo(this.rangeMin) == 1
                && this.location.getLatitude().compareTo(this.rangeMax) == -1) {
            this.isLatitudeInRange = true;
        }

        /** Check on the range being within +/- 90 (inclusive) */

        if (this.location.getLongitude().compareTo(this.rangeMin) == 1
                && this.location.getLongitude().compareTo(this.rangeMax) == -1) {
            this.isLongitudeInRange = true;
        }


        if ( (this.isLatitudeInRange) && (this.isLongitudeInRange) ) {
            this.isValidRequest = true;
        }

        if (requestJsonNode.has("radius")) {
            this.radius = requestJsonNode.get("radius").asInt();
        }

        if (requestJsonNode.has("limit")) {
            this.limit = requestJsonNode.get("limit").asInt();
        }

        return this;
    }


    public boolean getIsLatitudeInRange() { return this.isLatitudeInRange; }

    public boolean getIsLongitudeInRange() { return this.isLongitudeInRange; }

    public boolean isRequestValid() { return this.isValidRequest; }

    public BigDecimal getLatitude() { return this.location.getLatitude(); }

    public BigDecimal getLongitude() { return this.location.getLongitude(); }

    public double getAccuracy() { return this.location.getAccuracy(); }

    public int getRadius() { return this.radius; }

    public int getLimit() { return this.limit; }

    public Location getLocation() { return this.location; }

    public String toString() {
        return "\"latitude\":" + this.location.getLatitude()
                + ", longitude\"" + this.location.getLongitude()
                + "\", \"accuracy\": " + this.location.getAccuracy();
    }
}
