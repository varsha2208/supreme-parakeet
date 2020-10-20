package com.intercom.hub.service;

import com.intercom.hub.model.Customer;
import com.intercom.hub.model.Invite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @Author Varsha Chandrashekar on @CreatedOn 19/10/2020
 * Service layer providing the business logic
 */
@Service
public class DistanceService {

    private static final Double DUBLIN_LATITUDE = 53.339428;
    private static final Double DUBLIN_LONGITUDE = -6.257664;
    private static final Double EARTH_RADIUS_KMS = 6371.009;

    /**
     * Method to calculate the proximity for the customers within the given range,
     * customers with distance <= 100.00 kms will be invited
     * @param customers - list of customers
     * @param maxDistance - the max distance allowed
     * @return
     *  List<Invite>, lists the username and id of the customers who are invited
     */
    public List<Invite> calculateDistance(List<Customer> customers, Double maxDistance) {

        ArrayList<Invite> inviteList = new ArrayList<Invite>();
        for (Customer c : customers) {
            Double latitude = Double.valueOf(c.getLatitude());
            Double longitude = Double.valueOf(c.getLongitude());
            //calculate the distance based on latitudes and longitudes
            Double distance = distanceFormula(latitude, longitude);
            if (distance <= maxDistance) {
                inviteList.add(new Invite(Integer.valueOf(c.getUser_id()), c.getName()));
            }
        }

        if (!inviteList.isEmpty()) {
            sortInvitation(inviteList);
        }
        return inviteList;
    }

    /**
     * Method to sort the customers based on their ids
     * @param inviteList, list of customers with username and id
     */
    private void sortInvitation(ArrayList<Invite> inviteList) {
        Collections.sort(inviteList, new Comparator<Invite>() {
            @Override
            public int compare(Invite c1, Invite c2) {
                return Integer.compare(c1.getUser_id(), c2.getUser_id());
            }
        });
    }

    /**
     * @param lat2 , latitude of the customer
     * @param long2, longitude of the customer
     * @return distance between the customer's location and the target
     */
    private Double distanceFormula(Double lat2, Double long2) {

        Double lat1 = DUBLIN_LATITUDE;
        Double long1 = DUBLIN_LONGITUDE;

        if ((lat1 == lat2) && (long1 == long2)) {
            return 0.0;
        } else {
            Double theta = long1 - long2;
            Double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = dist * EARTH_RADIUS_KMS;
            return dist;
        }
    }
}
