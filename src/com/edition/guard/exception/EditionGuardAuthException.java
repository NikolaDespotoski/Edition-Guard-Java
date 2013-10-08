package com.edition.guard.exception;


public class EditionGuardAuthException extends EditionGuardException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5267908013629743540L;


	public EditionGuardAuthException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EditionGuardAuthException(EditionGuardExceptionReason reason,
			Object... params) {
		super(reason, params);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void setObjects(Object... params) {
		// TODO Auto-generated method stub
		super.setObjects(params);
	}

	@Override
	public void setReason(EditionGuardExceptionReason reason) {
		// TODO Auto-generated method stub
		super.setReason(reason);
	}



}
