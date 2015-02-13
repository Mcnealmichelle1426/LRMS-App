package org.vetech.lrms.core.exception;

/**
 * Created by alex on 1/24/15.
 */
public class CoreException extends java.lang.Exception {
	public CoreException(){
		super("Core exception occurred due to business logic failure");
	}

	public CoreException(String message){
		super(message);
	}

	public CoreException(String message, java.lang.Exception exception){
		super(message, exception);
	}
}
