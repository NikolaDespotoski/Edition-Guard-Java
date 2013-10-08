package com.edition.guard.model;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.edition.guard.utils.EditionGuardParamUtil;

public class PackagingRequest extends AbstractRequestModel{

	private EditionGuardHash hash;
	private File file;
	private String publisher;
	private String author;
	private long resourceId=-1;
	private String title;
	private String nonce;
	@Override
	public void setHash(EditionGuardHash editionGuardHash) {
		this.hash = editionGuardHash;
		this.nonce = hash.getNonce();
	}

	@Override
	public void setResourceId(long resId) {
		this.resourceId = resId;
	}

	@Override
	public EditionGuardHash getHash() {
		// TODO Auto-generated method stub
		return hash;
	}

	@Override
	public long getResourceId() {
		// TODO Auto-generated method stub
		return resourceId;
	}

	public void setTitle(String t) {
		this.title = t;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public void setAuthor(String a) {
		this.author = a;
	}

	public String getAuthor() {
		// TODO Auto-generated method stub
		return author;
	}

	public void setPublisher(String p) {
		this.publisher = p;
	}

	public String getPublisher() {
		// TODO Auto-generated method stub
		return publisher;
	}

	public void setFile(File f) {
		this.file = f;
	}

	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}
	@Override
	public ArrayList<BasicNameValuePair> asNameValuePairs(){
//		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
//		pairs.add(new BasicNameValuePair("nonce", hash.getNonce()));
//		pairs.add(new BasicNameValuePair("email", EditionGuard.getEmail()));
//		pairs.add(new BasicNameValuePair("hash", hash.getHash()));
//		pairs.add(new BasicNameValuePair("title",title));
//		pairs.add(new BasicNameValuePair("publisher",publisher));
//		pairs.add(new BasicNameValuePair("file", file.getAbsolutePath()));
//		pairs.add(new BasicNameValuePair("author", author));
//		pairs.add(new BasicNameValuePair("file", file.getAbsolutePath()));
//		if(resourceId != -1 )
//			pairs.add(new BasicNameValuePair("resource_id", Long.toString(resourceId)));	

			
		
		return EditionGuardParamUtil.asNameValuePairList(this);
		
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getNonce(){
		return nonce;
	}
}
