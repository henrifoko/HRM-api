package com.frsummit.HRM.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author hfoko
 *
 */
public interface RemoteServiceInterface extends Remote {

	/**
	 * @author hfoko
	 * 
	 *         This reflective method is the entry door of the application. All
	 *         customer calls are performed in a reflective way through this method.
	 * @param serviceClassName{String} - Class name of the bean at the server side
	 * @param remoteMethodName{String} - The name of the remote method
	 * @param paramsType{String[]}     - Array of parameter types of the called
	 *                                 method
	 * @param params{Object[]}         - Array of parameters of the called method
	 * @param returnType{Class}        - Return type of the method invoked
	 * @return This is the object returned by the called method in the case it
	 *         returns an object
	 * @throws RemoteException
	 */
	public Object exec(String daoClassName, String methodName, String[] paramsType, Object[] params, Class returnType)
			throws RemoteException;

}
