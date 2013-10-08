package com.edition.guard.model;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;


public abstract class AbstractRequestModel {


	public abstract void setHash(EditionGuardHash s);
	public abstract EditionGuardHash getHash();
	public abstract void setResourceId(long resId);
	public abstract long getResourceId();
	public abstract ArrayList<BasicNameValuePair> asNameValuePairs();

}
