package com.intercom.hub.ServiceUnitTests;

import com.intercom.hub.model.Customer;
import com.intercom.hub.model.Invite;
import com.intercom.hub.service.DistanceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class DistanceServiceTest {

    private static final Double MAX_DISTANCE = 100.00;

    @MockBean
    private
    DistanceService distanceService;

    private Customer customer1 = new Customer("46.123", "2", "Aaron Keogh", "-56.788");
    private Customer customer2 = new Customer("29.123", "5", "Karen McKara", "-66.788");
    private Customer customer3 = new Customer("41.123", "6", "Sony Albert", "-48.788");
    private Customer customer4 = new Customer("21.123", "6", "Sony Albert", "-88.788");

    private Invite invite1 = new Invite(2, "Aaron Keogh");
    private Invite invite2 = new Invite(6, "Sony Albert");
    private Invite invite3 = new Invite(5, "Karen McKara");


    @Test
    public void testForCustomersWithinHundredKmsProximity() {
        List<Invite> list = new ArrayList<>();
        list.add(invite1);
        list.add(invite2);
        when(distanceService.calculateDistance(Arrays.asList(customer1, customer2, customer3), MAX_DISTANCE)).thenReturn(list);
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(2), invite1.getUser_id());
        assertEquals("Sony Albert", invite2.getName());

        //ordering of userId
        assertTrue(list.get(0).getUser_id() < list.get(1).getUser_id());
        //case where a customer is not invited
        assertFalse(list.contains(invite3));
	}

    @Test
    public void testForCustomersNotWithinHundredKmsProximity() {
        List<Invite> list = new ArrayList<>();
        when(distanceService.calculateDistance(Arrays.asList(customer4, customer2), MAX_DISTANCE)).thenReturn(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testForEmptyCustomerList() {
        List<Invite> inviteList = new ArrayList<>();
        when(distanceService.calculateDistance(Collections.emptyList(), MAX_DISTANCE)).thenReturn(inviteList);
        assertEquals(0, inviteList.size());
    }

}
