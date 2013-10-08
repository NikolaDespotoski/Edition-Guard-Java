package com.edition.guard.model;


public class StatusReportResponse implements Response {

private String resourceid;
private String transtime;
private String name;
private String title;
private String distributorid;
private String transactionid;

public String getResourceid() {
	return resourceid;
}

public void setResourceid(String resourceid) {
	this.resourceid = resourceid;
}

public String getTranstime() {
	return transtime;
}

public void setTranstime(String transtime) {
	this.transtime = transtime;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDistributorid() {
return distributorid;
}

public void setDistributorid(String distributorid) {
this.distributorid = distributorid;
}

public String getTransactionid() {
return transactionid;
}

public void setTransactionid(String transactionid) {
this.transactionid = transactionid;
}

@Override
public void setRawData(String s) {
	// TODO Auto-generated method stub
	
}

@Override
public String getRawData() {
	// TODO Auto-generated method stub
	return null;
}


}
