package com.googlecode.pongo.tests.ossmeter.model;

import com.mongodb.*;
import java.util.*;
import com.googlecode.pongo.runtime.*;


public class GoogleForgeProject extends Project {
	
	protected List<GoogleIssue> issues = null;
	protected List<GoogleUser> owners = null;
	protected List<GoogleUser> committers = null;
	protected List<GoogleUser> contributors = null;
	protected List<GoogleDownload> downloads = null;
	protected GoogleWiki wiki = null;
	protected License license = null;
	
	
	public GoogleForgeProject() { 
		super();
		dbObject.put("issues", new BasicDBList());
		dbObject.put("owners", new BasicDBList());
		dbObject.put("committers", new BasicDBList());
		dbObject.put("contributors", new BasicDBList());
		dbObject.put("downloads", new BasicDBList());
	}
	
	public int getStars() {
		return parseInteger(dbObject.get("stars")+"", 0);
	}
	
	public GoogleForgeProject setStars(int stars) {
		dbObject.put("stars", stars + "");
		notifyChanged();
		return this;
	}
	
	
	public List<GoogleIssue> getIssues() {
		if (issues == null) {
			issues = new PongoList<GoogleIssue>(this, "issues", true);
		}
		return issues;
	}
	public List<GoogleUser> getOwners() {
		if (owners == null) {
			owners = new PongoList<GoogleUser>(this, "owners", true);
		}
		return owners;
	}
	public List<GoogleUser> getCommitters() {
		if (committers == null) {
			committers = new PongoList<GoogleUser>(this, "committers", true);
		}
		return committers;
	}
	public List<GoogleUser> getContributors() {
		if (contributors == null) {
			contributors = new PongoList<GoogleUser>(this, "contributors", true);
		}
		return contributors;
	}
	public List<GoogleDownload> getDownloads() {
		if (downloads == null) {
			downloads = new PongoList<GoogleDownload>(this, "downloads", true);
		}
		return downloads;
	}
	
	
	public GoogleWiki getWiki() {
		if (wiki == null && dbObject.containsField("wiki")) {
			wiki = (GoogleWiki) PongoFactory.getInstance().createPongo((DBObject) dbObject.get("wiki"));
		}
		return wiki;
	}
	
	public GoogleForgeProject setWiki(GoogleWiki wiki) {
		if (this.wiki != wiki) {
			if (wiki == null) {
				dbObject.removeField("wiki");
			}
			else {
				dbObject.put("wiki", wiki.getDbObject());
			}
			this.wiki = wiki;
			notifyChanged();
		}
		return this;
	}
	public License getLicense() {
		if (license == null && dbObject.containsField("license")) {
			license = (License) PongoFactory.getInstance().createPongo((DBObject) dbObject.get("license"));
		}
		return license;
	}
	
	public GoogleForgeProject setLicense(License license) {
		if (this.license != license) {
			if (license == null) {
				dbObject.removeField("license");
			}
			else {
				dbObject.put("license", license.getDbObject());
			}
			this.license = license;
			notifyChanged();
		}
		return this;
	}
}