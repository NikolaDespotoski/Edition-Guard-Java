package com.edition.guard.model;
public class EditionGuardError {

	private Error error;
	
	public Error getError() {
	return error;
	}
	
	public void setError(Error error) {
 	this.error = error;
	}
 	public boolean hasError(){
 		return getError() != null;
 	}

public class Error {

	private String data;	
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
//
//public String getXmlns() {
//return xmlns;
//}
//
//public void setXmlns(String xmlns) {
//this.xmlns = xmlns;
//}
}

}

