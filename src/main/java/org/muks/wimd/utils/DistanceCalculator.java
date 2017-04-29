package org.muks.wimd.utils;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 28/04/17
 *  -   Lat-Long diff to meters converter class
 */

public class DistanceCalculator {

    /**
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @param unit
     * @return
     */
    public double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;

        double dist = Math.sin( deg2rad(lat1) ) * Math.sin( deg2rad(lat2) )
                + Math.cos( deg2rad(lat1) )
                * Math.cos( deg2rad(lat2) )
                * Math.cos( deg2rad(theta) );

        dist = Math.acos(dist);

        dist = rad2deg(dist);

        dist = dist * 60 * 1.1515;

        if (unit.equalsIgnoreCase("kilometers")) {
            dist = (dist * 1.609344);

        } else if (unit.equalsIgnoreCase("nautical miles")) {
            dist = dist * 0.8684;

        } else if (unit.equalsIgnoreCase("meters")) {
            dist = (dist * 1.609344) * 100;
        }

        return (dist);
    }


    /**
     * converts decimal degrees to radians
     * @param deg
     * @return
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    /**
     * This function converts radians to decimal degrees
     * @param rad
     * @return
     */
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    public void calculate() {
        System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "miles") + " Miles\n");
        System.out.println(distance(11.97161923, 77.59463452, 29.46786, 75.59463452, "kilometers") + " Kilometers\n");
        System.out.println(distance(12.97161923, 77.590000, 12.97161923, 77.59463452, "meters") + " Meters\n");
        System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "nautical miles") + " Nautical Miles\n");
    }

    public double calculate(BigDecimal lat1, BigDecimal longi1, BigDecimal lat2, BigDecimal longi2, String units) {
        return distance(lat1.doubleValue(), longi1.doubleValue(), lat2.doubleValue(), longi2.doubleValue(), "meters");
    }

    public static void main(String[] args) {
        new DistanceCalculator().calculate();
    }
}
