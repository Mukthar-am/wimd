package org.muks.wimd.application;

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

        /** Check if the driver ID is valid, ranging between 1 - 50,000*/
        if (Utils.isValidDriver(id)) {
            System.out.println("Driver is valid.");

            try {
                RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(inputRequestJson));

                if (!requestJson.isRequestValid()) {
                    return new ResponseEntity(
                            new DriverLocationResponse("{\"errors\": [\"Latitude should be between +/- 90\"]}").getResponse(),
                            HttpStatus.UNPROCESSABLE_ENTITY);

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
            System.out.println("Driver IS NOT valid");
            return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.FORBIDDEN);
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
        if (Utils.isValidRequest(inputRequestJson.toString())) {   /** all good, go ahead an note the location of the driver */
            GoJekDrivers goJekDrivers = GoJekDrivers.getInstance();

            /*
                Response: json array
                [
                    {id: 42, latitude: 12.97161923, longitude: 77.59463452, distance: 123},
                    {id: 84, latitude: 12.97161923, longitude: 77.59463452, distance: 123}
                ]

             */

            return new ResponseEntity(new DriverLocationResponse("").getResponse(), HttpStatus.OK);

        } else {    /** if the request json is invalid, return error as Bad-Request */
            return new ResponseEntity(new DriverLocationResponse().getResponse(), HttpStatus.BAD_REQUEST);
        }

    }

}