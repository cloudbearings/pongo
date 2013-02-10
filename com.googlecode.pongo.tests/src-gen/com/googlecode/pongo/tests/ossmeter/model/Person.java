package com.googlecode.pongo.tests.ossmeter.model;

import com.mongodb.*;
import java.util.*;
import com.googlecode.pongo.runtime.*;


public class Person extends NamedElement {
	
	
	
	public Person() { 
		super();
	}
	
	public String getHomePage() {
		return parseString(dbObject.get("homePage")+"", "");
	}
	
	public Person setHomePage(String homePage) {
		dbObject.put("homePage", homePage + "");
		notifyChanged();
		return this;
	}
	
	
	
	
}