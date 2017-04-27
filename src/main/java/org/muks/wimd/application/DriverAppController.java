package org.muks.wimd.application;

import org.muks.wimd.dao.geo.Location;
import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.dao.response.DriverLocationResponse;
import org.muks.wimd.dao.transportation.Car;
import org.muks.wimd.dao.transportation.Driver;
import org.muks.wimd.repository.GoJekDrivers;
import org.muks.wimd.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
public class DriverAppController {

    @RequestMapping(value = "/drivers/{id}/location", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity driversTracker(@PathVariable("id") int id,
                                         @RequestBody String inputRequestJson,
                                         @RequestHeader("car-make") String carMake,
                                         @RequestHeader("car-model") String carModel,
                                         @RequestHeader("color") String carColor,
                                         @RequestHeader("registration") String carRegistration,
                                         @RequestHeader("segment") String carSegment,
                                         @RequestHeader("name") String driverName) {

        DriverLocationResponse driverLocationResponse = new DriverLocationResponse();

        /** Check if the driver ID is valid, ranging between 1 - 50,000*/
        if (Utils.isValidDriver(id)) {
            System.out.println("Driver is valid.");

            try {
                RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(inputRequestJson));

                if (!requestJson.isRequestValid()) {
                    driverLocationResponse.pushResponseError("Latitude should be between +/- 90");
                    System.out.println("invalid json req");
                    return new ResponseEntity(
                            driverLocationResponse.getResponse().get("errors").toString(), HttpStatus.UNPROCESSABLE_ENTITY);

                } else {    /** all good, go ahead an note the location of the driver */
                    GoJekDrivers goJekDrivers = GoJekDrivers.getInstance();

                    // ToDo: Scope of enhancement here, updateDriverLocation, should be just by driver-id
                    Location updatedLocation =
                            new Location(requestJson.getLatitude(),
                                        requestJson.getLongitude(),
                                        requestJson.getAccuracy());
                    Driver driverUpdate =
                            new Driver(
                                    id,
                                    driverName,
                                    new Car(carMake, carModel, carColor, carRegistration, Utils.getSegment(carSegment)),
                                    updatedLocation
                            );

                    goJekDrivers.updateDriverLocation(driverUpdate);

                    return new ResponseEntity(
                            driverLocationResponse.getResponse().get("message").toString(), HttpStatus.OK);
                }

            } catch (Exception e) {
                /** if the request json is invalid, return error as Bad-Request */
                e.printStackTrace();
                return new ResponseEntity(
                        driverLocationResponse.getResponse().get("message").toString(), HttpStatus.BAD_REQUEST);

            }

        } else {
            System.out.println("Driver IS NOT valid");
            return new ResponseEntity(
                    driverLocationResponse.getResponse().get("message").toString(), HttpStatus.FORBIDDEN);
        }
    }


    /**
     * both the default values are being handled with the request params itself.
     */
    @RequestMapping(value = "/drivers", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity findDriver(@RequestParam("latitude") BigDecimal latitude,
                                     @RequestParam("longitude") BigDecimal longitude,
                                     @RequestParam(value = "radius", defaultValue = "500") int radius,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit) {

        System.out.println("Latitude: " + latitude
                + ", Longitude: " + longitude
                + ", Radius: " + radius
                + ", Limit: " + limit);

        StringBuilder inputRequestJson = new StringBuilder("{");
        inputRequestJson.append("\"latitude\": \"" + latitude.toString() + "\"");
        inputRequestJson.append(",");
        inputRequestJson.append("\"longitude\": \"" + longitude.toString() + "\"");
        inputRequestJson.append(",");
        inputRequestJson.append("\"radius\": \"" + String.valueOf(radius) + "\"");
        inputRequestJson.append(",");
        inputRequestJson.append("\"limit\": \"" + String.valueOf(limit) + "\"");
        inputRequestJson.append("}");


        System.out.println("Input Request: " + inputRequestJson.toString());

        /** {"errors": ["Latitude should be between +/- 90"]} */
        DriverLocationResponse driverLocationResponse = new DriverLocationResponse();
        try {
            RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(inputRequestJson.toString()));

            if (!requestJson.isRequestValid()) {
                driverLocationResponse.pushResponseError("Latitude should be between +/- 90");
                System.out.println("invalid json req");
                return new ResponseEntity(
                        driverLocationResponse.getResponse().get("errors").toString(), HttpStatus.UNPROCESSABLE_ENTITY);

            } else {    /** all good, go ahead an note the location of the driver */
                GoJekDrivers goJekDrivers = GoJekDrivers.getInstance();



                return new ResponseEntity(
                        driverLocationResponse.getResponse().get("message").toString(), HttpStatus.OK);
            }

        } catch (Exception e) {
            /** if the request json is invalid, return error as Bad-Request */
            e.printStackTrace();
            return new ResponseEntity(
                    driverLocationResponse.getResponse().get("message").toString(), HttpStatus.BAD_REQUEST);

        }


    }

}