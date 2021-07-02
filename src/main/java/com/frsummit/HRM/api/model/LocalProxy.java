package com.frsummit.HRM.api.model;

import java.rmi.RemoteException;
import java.util.stream.Stream;

import com.frsummit.HRM.api.rmi.RemoteServiceInterface;

public class LocalProxy {
	private RemoteCallee callee;
	private RemoteServiceInterface stub;

	public LocalProxy(RemoteServiceInterface stub, RemoteCallee callee) {
		this.stub = stub;
		this.callee = callee;
	}
	
	@SuppressWarnings("rawtypes")
	public Object callWith(Object[] params) throws RemoteException {
		String[] paramTypeNames = new String[params.length];
		Class[] paramTypes = callee.getParamTypes();
		for (int i = 0; i<params.length; i++) {
			paramTypeNames[i] = paramTypes[i].getName();
		}
		return this.stub.exec(callee.getPackageName() + "." + callee.getClassName(), callee.getMethod(),
				paramTypeNames, params, callee.getReturnType());
	}

	public RemoteServiceInterface getStub() {
		return stub;
	}

	public void setStub(RemoteServiceInterface stub) {
		this.stub = stub;
	}

	public RemoteCallee getCallee() {
		return callee;
	}

	public void setCallee(RemoteCallee callee) {
		this.callee = callee;
	}

}
