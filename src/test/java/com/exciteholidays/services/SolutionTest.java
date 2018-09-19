package com.exciteholidays.services;

import org.junit.Test;

import java.util.Map;

import static com.exciteholidays.services.AtmService.*;
import static com.exciteholidays.common.Attributes.*;
import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void AtmAllocates() {
        Long twenties = 10L;
        Long fifties = 10L;
 
        Map<String, Long> result = solution(20L, twenties, fifties);
        assertNotNull(result);
        assertNotNull(result.get(twenty));
        assertNotNull(result.get(fifty));
        assertTrue(result.get(twenty) == 1L);
        assertTrue(result.get(fifty) == 0L);
        assertTrue(result.get(error) == null || result.get(error) == 0L);
    }

    @Test
    public void AtmErrorEmpty() {
        Long twenties = 0L;
        Long fifties = 0L;

        Map<String, Long> result = solution(20L, twenties, fifties);
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }

    @Test
    public void AtmNoFifties() {
        Long twenties = 10L;
        Long fifties = 0L;

        Map<String, Long> result = solution(100L, twenties, fifties);
        assertNotNull(result);
        assertNotNull(result.get(twenty));
        assertNotNull(result.get(fifty));
        assertTrue(result.get(twenty) == 5L);
        assertTrue(result.get(fifty) == 0L);
        assertTrue(result.get(error) == null || result.get(error) == 0L);
    }

    @Test
    public void AtmNoTwenties() {
        Long twenties = 0L;
        Long fifties = 10L;

        Map<String, Long> result = solution(100L, twenties, fifties);
        assertNotNull(result);
        assertNotNull(result.get(twenty));
        assertNotNull(result.get(fifty));
        assertTrue(result.get(twenty) == 0L);
        assertTrue(result.get(fifty) == 2L);
        assertTrue(result.get(error) == null || result.get(error) == 0L);
    }

    @Test
    public void AtmNoSolutionError() {
        Long twenties = 10L;
        Long fifties = 10L;

        Map<String, Long> result = solution(30L, twenties, fifties);
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }

    @Test
    public void AtmNoTwentiesNoSolutionError() {
        Long twenties = 0L;
        Long fifties = 10L;

        Map<String, Long> result = solution(120L, twenties, fifties);
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }

    @Test
    public void AtmNoFiftiesNoSolutionError() {
        Long twenties = 10L;
        Long fifties = 0L;

        Map<String, Long> result = solution(90L, twenties, fifties);
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }

    @Test
    public void AtmInvalidAmountError() {
        Long twenties = 10L;
        Long fifties = 10L;

        Map<String, Long> result = solution(55L, twenties, fifties);
        assertTrue(result.get(error) != null && result.get(error) != 0L);
    }
}