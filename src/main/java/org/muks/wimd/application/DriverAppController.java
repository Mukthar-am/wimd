package org.muks.wimd.application;

import com.fasterxml.jackson.databind.JsonNode;
import org.muks.wimd.dao.response.DriverLocationResponse;
import org.muks.wimd.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DriverAppController {

    @RequestMapping( value = "/drivers/{id}/location", method = RequestMethod.PUT, headers = "Accept=application/json" )
    public ResponseEntity driversTracker(
                                        @PathVariable("id") int id, @RequestBody String json) {

        /** Check if the driver ID is valid, ranging between 1 - 50,000*/
        if (id >= 1 && id <= 50000){
            System.out.printf("ID: ", id);
            System.out.println("Request Json: " + json);

            try {
                JsonNode requestJsonNode = Utils.convertToJsonNode(json);


//                if () {
//                    return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.UNPROCESSABLE_ENTITY);
//                }
            } catch (Exception e) {
                /** if the request json is invalid, return error as Bad-Request */
                e.printStackTrace();
                return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.OK);

        } else {
            return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.FORBIDDEN);
        }
    }



}