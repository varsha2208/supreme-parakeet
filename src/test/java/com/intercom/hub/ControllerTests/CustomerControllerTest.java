package com.intercom.hub.ControllerTests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Author Varsha Chandrashekar on @CreatedOn 19/10/2020
 * Controller/ Integration tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getSuccessfulCustomerInviteListSize()
            throws Exception {

        mvc.perform(get("/intercom/invite/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(16)));
    }

    @Test
    public void getSuccessfulCustomerInviteList()
            throws Exception {

        mvc.perform(get("/intercom/invite/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(16)))
                .andExpect(jsonPath("$[0].user_id", is(4)))
                .andExpect(jsonPath("$[0].name", is("Ian Kehoe")))
                .andExpect(jsonPath("$[15].user_id", is(39)))
                .andExpect(jsonPath("$[15].name", is("Lisa Ahearn")));
    }

    @Test
    public void verifyCustomersNotInTheInviteList()
            throws Exception {

        MockHttpServletResponse response = mvc.perform(get("/intercom/invite/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertFalse(response.getContentAsString().contains("Alice Cahill"));
        assertFalse(response.getContentAsString().contains("Jack Dempsey"));

    }

}
