package com.frsummit.HRM.api.generic.model;

/**
 * 
 * @author hfoko
 *
 */
public enum FilterPreprocessingEnum {

    /**
     * 
     */
    REGULAR( new FilterPreprocessingRegular() );

    private FilterPreprocessing filter;

    private FilterPreprocessingEnum( FilterPreprocessing filter ) {
        this.filter = filter;
    }

    /**
     * 
     * @return
     */
    public FilterPreprocessing getFilter() {
        return filter;
    }
}
