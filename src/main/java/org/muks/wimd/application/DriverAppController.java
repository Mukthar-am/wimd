package org.muks.wimd.application;

import com.fasterxml.jackson.databind.JsonNode;
import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.dao.response.DriverLocationResponse;
import org.muks.wimd.dao.transportation.Car;
import org.muks.wimd.dao.transportation.Driver;
import org.muks.wimd.dao.transportation.Segments;
import org.muks.wimd.dao.transportation.Vehicle;
import org.muks.wimd.repository.GoJekDrivers;
import org.muks.wimd.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DriverAppController {

    @RequestMapping( value = "/drivers/{id}/location", method = RequestMethod.PUT, headers = "Accept=application/json" )
    public ResponseEntity driversTracker(
                                        @PathVariable("id") int id,
                                        @RequestBody String json,
                                        @RequestHeader("car-make") String carMake,
                                        @RequestHeader("car-model") String carModel,
                                        @RequestHeader("color") String carColor,
                                        @RequestHeader("registration") String carRegistration,
                                        @RequestHeader("segment") String carSegment,
                                        @RequestHeader("name") String driverName) {

        /** Check if the driver ID is valid, ranging between 1 - 50,000*/
        if (id >= 1 && id <= 50000){
            System.out.printf("ID: ", id);
            System.out.println("Request Json: " + json);

            try {
                RequestJson requestJson = new RequestJson( Utils.convertToJsonNode(json) );

                if (!requestJson.isLatitudeInRange()
                        || !requestJson.isLongitudeInRange()) {
                    return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.UNPROCESSABLE_ENTITY);

                }
                else {    /** all good, go ahead an note the location of the driver */
                    GoJekDrivers goJekDrivers = GoJekDrivers.getInstance();

                    Driver driverUpdate =
                            new Driver(
                                        id,
                                        driverName,
                                        new Car(carMake, carModel, carColor, carRegistration, Utils.getSegment(carSegment))
                    );
                    goJekDrivers.updateDriverLocation(driverUpdate);

                    return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.OK);
                }

            } catch (Exception e) {
                /** if the request json is invalid, return error as Bad-Request */
                e.printStackTrace();
                return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.BAD_REQUEST);
            }


        } else {
            return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.FORBIDDEN);
        }
    }




}