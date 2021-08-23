package com.frsummit.HRM.api.rest.postprocessing;

public interface Postprocessing<T> {

    public T process( T list ) throws Exception;
}
