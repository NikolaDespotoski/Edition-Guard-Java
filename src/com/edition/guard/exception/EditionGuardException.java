package com.edition.guard.exception;

import org.apache.commons.lang.StringUtils;

public class EditionGuardException extends Exception {

	private static final Character DELIMITER =  ',';
	public enum EditionGuardExceptionReason { MISSING_PARAM, AUTH_FAILED, OTHER};
	private static final long serialVersionUID = -5762202316185755944L;
	private EditionGuardExceptionReason mReason;
	private Object[] mParams;
	public EditionGuardException(EditionGuardExceptionReason reason, Object ...params){
		this.mReason = reason;
		this.mParams = params;
	}
	public EditionGuardException(){
		
	}
	public void setReason(EditionGuardExceptionReason reason){
		this.mReason = reason;
	}
	public void setObjects(Object ...params){
		this.mParams = params;
	}
	@Override
	public String getMessage() {
		switch(mReason){
		case MISSING_PARAM:
			if(mParams != null)
				return "Missing parameter"+(mParams.length > 1? "s ":" ") +"in your request body. Missing: "+ StringUtils.join(mParams,DELIMITER.charValue());
			
			break;
		case AUTH_FAILED:
			if(mParams!=null)
				return StringUtils.join(mParams);
			break;
		case OTHER:
			return StringUtils.join(mParams);
		}
		return super.getMessage();
	}
	
}
