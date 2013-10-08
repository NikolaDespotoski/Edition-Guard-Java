package com.edition.guard.model;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.edition.guard.init.EditionGuard;
import com.edition.guard.utils.EditionGuardParamUtil;

public class DeleteRequest extends AbstractRequestModel {

	private long resource_id;
	private EditionGuardHash mHash;

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
//			ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
//			pairs.add(new BasicNameValuePair("nonce", mHash.getNonce()));
//			pairs.add(new BasicNameValuePair("email", EditionGuard.getEmail()));
//			pairs.add(new BasicNameValuePair("hash", mHash.getHash()));
//			if(resource_id != -1 )
//				pairs.add(new BasicNameValuePair("resource_id", Long.toString(resource_id)));	
			
			return EditionGuardParamUtil.asNameValuePairList(this);
	}


}
