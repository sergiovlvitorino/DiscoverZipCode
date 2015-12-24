package com.github.sergiovlvitorino.discoverzipcode.test;

import com.github.sergiovlvitorino.discoverzipcode.DiscoverZipCode;

import junit.framework.TestCase;

public class DiscoverZipCodeTest extends TestCase{
	
	private DiscoverZipCode discoverZipCode;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		discoverZipCode = null;
	}
	
	public void testIfDiscoverZipCodeIsNull(){
		assertNull(discoverZipCode);
	}

}
