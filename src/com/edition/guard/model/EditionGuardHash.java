package com.edition.guard.model;

public class EditionGuardHash {

	private String nonce;
	private String hash;
	public EditionGuardHash(String nonce, String hmacSha1) {
		this.nonce = nonce;
		this.hash = hmacSha1;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getHash() {
		return hash;
	}
	public void setSHA1Hash(String hash) {
		this.hash = hash;
	}
	
}
