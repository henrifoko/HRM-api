package com.frsummit.HRM.api.controller.generic;

public interface ICRUDController<T, S> {
	
	/**
	 * @author hfoko
	 * @param id
	 * @return
	 */
	public T getUnique(S id);
	
	public T getMany();
	
	public T post();
	
	public T put();
	
	public T patch();
}
