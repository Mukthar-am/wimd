package org.muks.wimd.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.dao.transportation.Segments;

import java.io.IOException;

/**
 * Created by 300000511 on 26/04/17
 *  Generic utilities
 */
public class Utils {

    public static JsonNode convertToJsonNode(String inputString) throws Exception {
        try {
            isValidJson(inputString);
        } catch (Exception e) {
            throw new Exception(e);
        }

        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, true);
        mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);

        try {
            jsonNode = mapper.readValue(inputString, JsonNode.class);
        } catch (Exception e) {
            throw new Exception(e);
        }

        return jsonNode;
    }


    /**
     *
     * @param jsonInString
     * @return
     * @throws IOException
     */
    public static boolean isValidJson(String jsonInString) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        boolean isValid = true;

        try {
            mapper.readTree(jsonInString);

        } catch (JsonProcessingException e) {
            isValid = false;
            throw new Exception(e);

        }
        return isValid;
    }


    public static Segments getSegment(String segment) {
        //LUXURY, SEDAN, MINI, MICRO, AUTO
        if (segment.equalsIgnoreCase("luxury")) {
            return Segments.LUXURY;
        }
        else if (segment.equalsIgnoreCase("sedan")) {
            return Segments.SEDAN;
        }
        else if (segment.equalsIgnoreCase("mini")) {
            return Segments.MINI;
        }
        else if (segment.equalsIgnoreCase("micro")) {
            return Segments.MICRO;
        }
        else if (segment.equalsIgnoreCase("auto")) {
            return Segments.AUTO;
        }

        return null;

    }


    /**
     * Find if the driver is valid based on the driver ID range check between 1 (inclusive) and 50000 (inclusive)
     * @param driverId
     * @return
     */
    public static boolean isValidDriver(int driverId) {
        /** Check if the driver ID is valid, ranging between 1 - 50,000*/
        if (driverId >= 1 && driverId <= 50000) {
            return true;
        }

        return false;
    }


    /**
     * Find if the incoming request is valid, based on its raging beging between +/-90 (inclusive)
     * @param inputRequestJson   - input json string
     * @return boolean
     */
    public static boolean isValidRequest(String inputRequestJson) {



        return true;
    }
}
