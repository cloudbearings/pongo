package com.googlecode.pongo.tests.blog.model;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;

import java.util.List;
import org.googlecode.pongo.runtime.Pongo;
import org.googlecode.pongo.runtime.PongoList;
import org.googlecode.pongo.runtime.PrimitiveList;

public class Author extends Person {
	
	
	public Author() { 
		super();
	}
	
	public Author(DBObject dbObject) {
		super(dbObject);
	}
	
	
	
	
	
}