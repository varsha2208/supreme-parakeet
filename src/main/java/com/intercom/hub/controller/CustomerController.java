package com.intercom.hub.controller;

import com.google.gson.Gson;
import com.intercom.hub.model.Customer;
import com.intercom.hub.model.Invite;
import com.intercom.hub.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * @Author Varsha Chandrashekar on @CreatedOn 19/10/2020
 * Controller class responsible for processing the API Calls
 */
@RestController
@RequestMapping("/intercom")
public class CustomerController {

    //maximum distance allowed in kms between customer's location and the target location
    private static final Double MAX_DISTANCE = 100.00;

    @Autowired
    Gson gson;

    @Autowired
    private DistanceService distanceService;

    /**
     * @return List<Invite> with username and id
     *     List of customers who are invited.
     */
    @GetMapping(value = "/invite/customers", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCustomersListToInvite() {

        List<Customer> customerList = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new URL("https://s3.amazonaws.com/intercom-take-home-test/customers.txt").openStream()))) {

            String line;
            //reading the input file and processing the json
            while ((line = in.readLine()) != null) {
                //map json to Customer class
                Customer customer = gson.fromJson(line, Customer.class);
                customerList.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (customerList.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        //return the list of customers to be invited
        List<Invite> invitationList = distanceService.calculateDistance(customerList, MAX_DISTANCE);
        return ResponseEntity.ok().body(invitationList);
    }
}
