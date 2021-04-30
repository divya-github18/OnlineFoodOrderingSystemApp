package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BaseClass {
	
	@BeforeEach
	public void test() {
		MockitoAnnotations.initMocks(this);
		
	}

}
