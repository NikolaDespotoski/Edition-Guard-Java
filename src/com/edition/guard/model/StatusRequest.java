package com.edition.guard.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.http.message.BasicNameValuePair;

import com.edition.guard.init.EditionGuard;

public class StatusRequest extends AbstractRequestModel {

	private EditionGuardHash mHash;
	private long resource_id = -1;
	private Date start_date;
	private Date end_date;
 
	@Override
	public void setHash(EditionGuardHash s) {
		this.mHash = s;
		
	}

	@Override
	public EditionGuardHash getHash() {
		// TODO Auto-generated method stub
		return mHash;
	}

	@Override
	public void setResourceId(long resId) {
		this.resource_id = resId;
	}

	@Override
	public long getResourceId() {
		// TODO Auto-generated method stub
		return resource_id;
	}

	@Override
	public ArrayList<BasicNameValuePair> asNameValuePairs() {
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		pairs.add(new BasicNameValuePair("nonce", mHash.getNonce()));
		pairs.add(new BasicNameValuePair("email", EditionGuard.getEmail()));
		pairs.add(new BasicNameValuePair("hash", mHash.getHash()));
		SimpleDateFormat sdf = new SimpleDateFormat();
		pairs.add(new BasicNameValuePair("start_date", sdf.format(start_date)));
		pairs.add(new BasicNameValuePair("end_date", sdf.format(end_date)));
		return pairs;
	}
	public void setStartDate(Date d){
		this.start_date = d;
	}
	public void setEndDate(Date endDate){
		this.end_date = endDate;
	}
	public Date getStartDate(){
		return start_date;
	}
	public Date getEndDate(){
		return end_date;
	}
}
