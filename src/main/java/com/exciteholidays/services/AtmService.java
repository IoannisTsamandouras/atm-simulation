package com.exciteholidays.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.exciteholidays.common.Errors;

import static com.exciteholidays.common.Attributes.*;
import static com.exciteholidays.common.Errors.*;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AtmService {
	Logger LOGGER = LoggerFactory.getLogger(AtmService.class);
	private Long twenties = 0L;
	private Long fifties = 0L;
	
	 @SuppressWarnings("serial")
	private static final HashMap<Long, Map<String, Long>> combination = new HashMap<Long, Map<String, Long>>() {{
	        put(20L, new HashMap<String, Long>(){{put(twenty, 1L);put(fifty, 0L);}});
	        put(40L, new HashMap<String, Long>(){{put(twenty, 2L);put(fifty, 0L);}});
	        put(50L, new HashMap<String, Long>(){{put(twenty, 0L);put(fifty, 1L);}});
	        put(60L, new HashMap<String, Long>(){{put(twenty, 3L);put(fifty, 0L);}});
	        put(70L, new HashMap<String, Long>(){{put(twenty, 1L);put(fifty, 1L);}});
	        put(80L, new HashMap<String, Long>(){{put(twenty, 4L);put(fifty, 0L);}});
	        put(90L, new HashMap<String, Long>(){{put(twenty, 2L);put(fifty, 1L);}});
	        put(100L, new HashMap<String, Long>(){{put(twenty, 0L);put(fifty, 2L);}});
	        put(110L, new HashMap<String, Long>(){{put(twenty, 3L);put(fifty, 1L);}});
	    }};

	    public boolean initialize(Long twenties, Long fifties) {
	        this.twenties = twenties;
	        this.fifties = fifties;
	        return true;
	    }

	    public Long getTotalAmount() {
	        return (TWENTY * twenties) + (FIFTY * fifties);
	    }

	    public Long getFifties() {
	        return fifties;
	    }

	    public Long getTwenties() {
	        return twenties;
	    }

	    public synchronized Map<String, Long> allocate(Long amount) {
	        Map<String, Long> result = new HashMap<>();
	        Errors validation = validAmount(amount);
	        if (validation == VALID) {
	            result = solution(amount, twenties, fifties);
	            validation = errors(result.get(error));
	        }
	        if (validation != null && validation != VALID) {
	    
	            return result(null, null, validation);
	        }	        
	        twenties -= result.get(twenty);
	        fifties -= result.get(fifty);
	        return result;
	    }

	    public static final Map<String, Long> solution(Long amount, Long twentiesAvailable, Long fiftiesAvailable) {
	        Map<String, Long> result;
	        if (combination.containsKey(amount)) {
	            result = combination.get(amount);
	            if (result.get(twenty) <= twentiesAvailable && result.get(fifty) <= fiftiesAvailable) {
	                return result;
	            }
	        }
	        
	        Long twenties = 0L;
	        Long fifties = amount / FIFTY;
	        
	        if (fifties > fiftiesAvailable) {
	            fifties = fiftiesAvailable;
	        }
	        while(fifties >= 0) {	           
	            twenties = (amount - FIFTY * fifties) / FIFTY;
	            if (twenties > twentiesAvailable) {
	                return result(null, null, NOT_AVAILABLE);
	            }	            
	            if (amount == (fifties * FIFTY + twenties * FIFTY)) {
	                return result(twenties, fifties, null);
	            }	            
	            fifties--;
	        }
	        return result(null, null, NOT_AVAILABLE);
	    }

	    protected Errors validAmount(Long amount) {
	        if (amount % 10 != 0) {
	            return INVALID;
	        }	        
	        if (amount > getTotalAmount()) {
	            return HIGH;
	        }	        
	        if (amount < FIFTY) {
	            return LOW;
	        }
	        return VALID;
	    }

	    protected static final Map<String, Long> result(Long twenties, Long fifties, Errors err) {
	        if (error == null) {
	            Map<String, Long> result = new HashMap<>();
	            result.put(twenty, twenties);
	            result.put(fifty, fifties);
	            return result;
	        }
	        return Collections.singletonMap(error, err.getCode());
	    }

	    public static final boolean hasError(Map<String, Long> result) {
	        return result.get(error) != null;
	    }

}
