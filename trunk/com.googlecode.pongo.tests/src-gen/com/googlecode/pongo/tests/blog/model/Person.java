package com.googlecode.pongo.tests.blog.model;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;

import java.util.List;
import com.googlecode.pongo.runtime.Pongo;
import com.googlecode.pongo.runtime.PongoList;
import com.googlecode.pongo.runtime.PrimitiveList;
import com.googlecode.pongo.runtime.PongoFactory;

public class Person extends Pongo {
	
	
	public Person() { 
		super();
	}
	
	public Person(DBObject dbObject) {
		super(dbObject);
	}
	
	public String getName() {
		return parseString(dbObject.get("name")+"", "");
	}
	
	public Person setName(String name) {
		dbObject.put("name", name + "");
		return this;
	}
	
	
	
	
}