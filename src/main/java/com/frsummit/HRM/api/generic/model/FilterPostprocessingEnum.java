package com.frsummit.HRM.api.generic.model;

/**
 * 
 * @author hfoko
 *
 */
public enum FilterPostprocessingEnum {
    /**
     * 
     */
    REGULAR( new FilterPostprocessingRegular() ),

    /**
     * 
     */
    LIST_POJO( new FilterPostprocessingPojoList() ),

    /**
     * 
     */
    FILE_DOWNLAOD( new FilterPostprocessingFileDownload() );

    private FilterPostprocessing filter;

    private FilterPostprocessingEnum( FilterPostprocessing filter ) {
        this.filter = filter;
    }

    /**
     * 
     * @return
     */
    public FilterPostprocessing getFilter() {
        return filter;
    }
}
