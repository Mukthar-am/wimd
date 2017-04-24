package com.muks.loadtests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



//@RequestMapping("drivers")
@RestController
public class DriverAppController {

    @RequestMapping(value = "drivers", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpStatus driversTracker(@RequestBody String json) {
        System.out.println("# Driver app: " + json.toString());

        return (HttpStatus.OK);
    }

}