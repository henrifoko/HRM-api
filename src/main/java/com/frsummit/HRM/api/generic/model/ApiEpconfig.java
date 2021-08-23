package com.frsummit.HRM.api.generic.model;

import java.util.List;

/**
 * 
 * @author hfoko
 *
 */
public class ApiEpconfig {
    private List<ApiResource> ressources;

    /**
     * 
     */
    public ApiEpconfig() {
        super();
    }

    /**
     * 
     * @param ressources
     */
    public ApiEpconfig( List<ApiResource> ressources ) {
        this.ressources = ressources;
    }

    /**
     * 
     * @return
     */
    public List<ApiResource> getRessources() {
        return ressources;
    }

    /**
     * 
     * @param ressources
     */
    public void setRessources( List<ApiResource> ressources ) {
        this.ressources = ressources;
    }

}
