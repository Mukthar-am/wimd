package org.muks.wimd.application;

import org.muks.wimd.dao.response.DriverResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DriverAppController {

    @RequestMapping( value = "/drivers/{id}/location", method = RequestMethod.PUT, headers = "Accept=application/json" )
    public ResponseEntity driversTracker(@PathVariable("id") int id, @RequestBody String json) {

        System.out.println("ID: " + id);
        System.out.println("# Driver app: " + json.toString());

        return new ResponseEntity(new DriverResponse().getResponse(), HttpStatus.OK);
    }



}