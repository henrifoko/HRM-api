package com.frsummit.HRM.api.server.shared.file;

public class ApiFile {

    private String name;
    private String type;
    private byte[] content;

    public ApiFile( String name, String type, byte[] content ) {
        this.content = content;
        this.name = name;
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent( byte[] content ) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

}
