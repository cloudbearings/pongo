package com.googlecode.pongo.tests.ossmeter.model;

import com.mongodb.*;
import java.util.*;
import com.googlecode.pongo.runtime.*;


public class SizeFact extends Pongo {
	
	protected MonthDownload downloadPerMonth = null;
	
	
	public SizeFact() { 
		super();
	}
	
	public int getDownloads() {
		return parseInteger(dbObject.get("downloads")+"", 0);
	}
	
	public SizeFact setDownloads(int downloads) {
		dbObject.put("downloads", downloads + "");
		notifyChanged();
		return this;
	}
	
	
	
	
	public MonthDownload getDownloadPerMonth() {
		if (downloadPerMonth == null && dbObject.containsField("downloadPerMonth")) {
			downloadPerMonth = (MonthDownload) PongoFactory.getInstance().createPongo((DBObject) dbObject.get("downloadPerMonth"));
		}
		return downloadPerMonth;
	}
	
	public SizeFact setDownloadPerMonth(MonthDownload downloadPerMonth) {
		if (this.downloadPerMonth != downloadPerMonth) {
			if (downloadPerMonth == null) {
				dbObject.removeField("downloadPerMonth");
			}
			else {
				dbObject.put("downloadPerMonth", downloadPerMonth.getDbObject());
			}
			this.downloadPerMonth = downloadPerMonth;
			notifyChanged();
		}
		return this;
	}
}