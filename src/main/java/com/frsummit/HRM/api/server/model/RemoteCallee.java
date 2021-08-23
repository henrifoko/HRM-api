package com.frsummit.HRM.api.server.model;

@SuppressWarnings("rawtypes")
public class RemoteCallee {

	private String packageName;
	private String className;
	private String method;
	private Class[] paramTypes;
	private Class returnType;

	RemoteCallee() {
	}

	public RemoteCallee(String packageName, String className, String method, Class[] paramTypes, Class returnType) {
		super();
		this.packageName = packageName;
		this.className = className;
		this.method = method;
		this.paramTypes = paramTypes;
		this.returnType = returnType;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Class[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Class getReturnType() {
		return returnType;
	}

	public void setReturnType(Class returnType) {
		this.returnType = returnType;
	}
	
	
}
