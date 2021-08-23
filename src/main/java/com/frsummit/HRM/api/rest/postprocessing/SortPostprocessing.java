package com.frsummit.HRM.api.rest.postprocessing;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.frsummit.HRM.api.exception.ApiError;
import com.frsummit.HRM.api.exception.ApiErrorCode;
import com.frsummit.HRM.api.exception.ApiSortPostprocessingException;

public class SortPostprocessing<T> implements Postprocessing<List<T>> {

    private boolean sort;
    private String  sortAttribute;
    private String  sortOrder;

    public SortPostprocessing( boolean sort, String sortAttribute, String sortOrder ) {
        this.sort = sort;
        this.sortAttribute = sortAttribute;
        this.sortOrder = sortOrder;
    }

    public boolean isSort() {
        return sort;
    }

    public String getSortAttribute() {
        return sortAttribute;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Override
    public List<T> process( List<T> list ) throws ApiSortPostprocessingException {
        List<T> result = list;
        if ( sort ) {
            if ( list.size() > 0 ) {
                T temp = list.get( 0 );
                boolean hasAttribute = Arrays.stream( temp.getClass().getDeclaredFields() ).anyMatch( f -> {
                    return f.getName().equals( sortAttribute );
                } );
                System.out.println( hasAttribute );
                if ( hasAttribute ) {
                    result.sort( ( a, b ) -> {
                        try {
                            Comparable attributeValueA = (Comparable) new PropertyDescriptor( sortAttribute,
                                    a.getClass() ).getReadMethod().invoke( a );
                            Comparable attributeValueB = (Comparable) new PropertyDescriptor( sortAttribute,
                                    b.getClass() ).getReadMethod().invoke( b );
                            if ( sortOrder.equals( "asc" ) ) {
                                return attributeValueA.compareTo( attributeValueB );
                            } else if ( sortOrder.equals( "desc" ) ) {
                                return -attributeValueA.compareTo( attributeValueB );
                            } else {
                                // TODO create a custom exception here
                                throw new Exception(
                                        "Invalid request ! The parameter sort_order can only take two values : asc, desc." );
                            }
                        } catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException
                                | IntrospectionException e1 ) {
                            e1.printStackTrace();
                        } catch ( Exception e ) {
                            e.printStackTrace();
                        }
                        return 0;
                    } );
                } else {
                    // TODO create a custom exception here
                    throw new ApiSortPostprocessingException(
                            new ApiError(
                                    ApiErrorCode.REQUEST_ERROR,
                                    HttpStatus.BAD_REQUEST,
                                    "Invalid request",
                                    "Please refer to documentation to see more about sorting query parameters",
                                    new ArrayList() ) );
                }
            }
        }

        return result;
    }

}
