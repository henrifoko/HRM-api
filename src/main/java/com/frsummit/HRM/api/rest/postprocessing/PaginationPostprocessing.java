package com.frsummit.HRM.api.rest.postprocessing;

import java.util.List;

public class PaginationPostprocessing<T> implements Postprocessing<List<T>> {

    private int limit;
    private int page;

    public PaginationPostprocessing( int limit, int page ) {
        this.limit = limit;
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public int getPage() {
        return page;
    }

    @Override
    public List<T> process( List<T> list ) {
        List<T> result = list;
        int size = list.size();
        if ( page == 0 && limit == 0 ) {
            return result;
        } else {
            int fromIndex = Math.max( Math.min( page * limit, size - 1 ), 0 );
            int toIndex = Math.max( Math.min( ( page + 1 ) * limit, size - 1 ), 0 );

            return result.subList( fromIndex, toIndex );
        }
    }

}
