package com.frsummit.HRM.api.postprocessing;

public interface PostProcessing<T> {
	
	public T process(T list) throws Exception;
}
