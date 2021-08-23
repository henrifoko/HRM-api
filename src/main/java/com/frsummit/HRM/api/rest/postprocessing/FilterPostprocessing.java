package com.frsummit.HRM.api.rest.postprocessing;

import java.util.ArrayList;
import java.util.List;

public class FilterPostprocessing<T, V> implements Postprocessing<List<T>> {

    private String  filterAttribute;
    private List<V> filterValues;

    public FilterPostprocessing( String filterAttribute, List<V> filterValues ) {
        this.filterAttribute = filterAttribute;
        this.filterValues = filterValues;
    }

    public String getFilterAttribute() {
        return filterAttribute;
    }

    public List<V> getFilterValues() {
        return filterValues;
    }

    @Override
    public List<T> process( List<T> list ) {
        List<T> result = new ArrayList<T>();

        for ( T t : list ) {
            // :
        }
        return result;
    }

}
