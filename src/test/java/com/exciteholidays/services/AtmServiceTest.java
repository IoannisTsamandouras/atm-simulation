package com.exciteholidays.services;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.exciteholidays.common.Attributes.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmServiceTest {

    @Autowired
    AtmService atmService;

    @Test
    public void AtmAllocates() {      
        atmService.initialize(10L, 10L);
  
        Map<String, Long> result = atmService.allocate(120L);
    
        assertNotNull(result);
        assertNotNull(result.get(twenty));
        assertNotNull(result.get(fifty));
        assertTrue(result.get(twenty) == 1L);
        assertTrue(result.get(fifty) == 2L);
        assertTrue(result.get(error) == null || result.get(error) == 0L);
    }

    @Test
    public void AtmDelivers() {
        atmService.initialize(10L, 10L);
        Map<String, Long> result1 = atmService.allocate(50L);
        Map<String, Long> result2 = atmService.allocate(150L);
        Map<String, Long> result3 = atmService.allocate(120L);
        Map<String, Long> result4 = atmService.allocate(180L);

        assertNotNull(result1);
        assertNotNull(result1.get(twenty));
        assertNotNull(result1.get(fifty));
        assertTrue(result1.get(twenty) == 0L);
        assertTrue(result1.get(fifty) == 1L);
        assertTrue(result1.get(error) == null || result1.get(error) == 0L);
    
        assertNotNull(result2);
        assertNotNull(result2.get(twenty));
        assertNotNull(result2.get(fifty));
        assertTrue(result2.get(twenty) == 0L);
        assertTrue(result2.get(fifty) == 3L);
        assertTrue(result2.get(error) == null || result2.get(error) == 0L);
      
        assertNotNull(result3);
        assertNotNull(result3.get(twenty));
        assertNotNull(result3.get(fifty));
        assertTrue(result3.get(twenty) == 1L);
        assertTrue(result3.get(fifty) == 2L);
        assertTrue(result3.get(error) == null || result3.get(error) == 0L);
      
        assertNotNull(result4);
        assertNotNull(result4.get(twenty));
        assertNotNull(result4.get(fifty));
        assertTrue(result4.get(twenty) == 4L);
        assertTrue(result4.get(fifty) == 2L);
        assertTrue(result4.get(error) == null || result4.get(error) == 0L);
    }

    @Test
    public void AtmEmptyError() {       
        atmService.initialize(10L, 10L);
      
        Map<String, Long> result = null;
        for (int i=0; i <=7; i++) {
            result = atmService.allocate(100L);
        }      
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }

    @Test
    public void AtmInvalid101Error() {       
        atmService.initialize(10L, 10L);       
        Map<String, Long> result = result = atmService.allocate(101L);      
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }

    @Test
    public void AtmInvalidMinus20Error() {
        atmService.initialize(10L, 10L);    
        Map<String, Long> result = result = atmService.allocate(-20L);
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }

    @Test
    public void AtmInvalid10Error() {   
    	atmService.initialize(10L, 10L);
        Map<String, Long> result = result = atmService.allocate(10L);
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }
}