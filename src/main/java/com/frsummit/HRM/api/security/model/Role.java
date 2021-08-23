package com.frsummit.HRM.api.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author hfoko
 *
 */
@Entity
@Table( name = "roles" )
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Enumerated( EnumType.STRING )
    @Column( length = 20 )
    private ERole   name;

    /**
     * 
     */
    public Role() {

    }

    /**
     * 
     * @param name
     */
    public Role( ERole name ) {
        this.name = name;
    }

    /**
     * 
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId( Integer id ) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public ERole getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName( ERole name ) {
        this.name = name;
    }
}
