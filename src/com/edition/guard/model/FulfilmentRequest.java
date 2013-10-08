package com.edition.guard.model;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;

public class FulfilmentRequest extends AbstractRequestModel{

	private long transactionId;
	private String resourceId;
	private Date validUntil;
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public String getResourceIdUrn() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	@Override
	public void setHash(EditionGuardHash s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public EditionGuardHash getHash() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setResourceId(long resId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<BasicNameValuePair> asNameValuePairs() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getResourceId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
