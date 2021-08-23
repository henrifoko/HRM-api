package com.frsummit.HRM.api.generic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.frsummit.HRM.api.exception.ApiServerException;
import com.frsummit.HRM.api.rest.service.FileService;
import com.frsummit.HRM.api.server.shared.file.ApiFile;

public class FilterPostprocessingFileDownload implements FilterPostprocessing {

    @Autowired
    private FileService fileService;

    @Override
    public ResponseEntity<?> process( Object result, Map<String, String> queryParams, Map<String, String> pathVariables,
            MultipartFile requestFile, RequestEntity<String> requestEntity, HttpServletRequest servletRequest )
            throws ApiServerException {

        // 1. Get the byte array from the server
        ApiFile file = (ApiFile) result;

        // 2. Save the byte array in a file into the RMI client
        String fileName = fileService.saveTempFile( file.getContent() );

        // 3. Return a stream to the file on the RMI client
        File tempFile = new File( fileName );
        InputStreamResource resource;

        try {
            // System.out.println( "Transfering the temp file [" + fileName + "]
            // ..." );
            resource = new InputStreamResource( new FileInputStream( tempFile ) );
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
            throw new ApiServerException();
        }

        HttpHeaders headers = new HttpHeaders();

        headers.add( "Content-Disposition", String.format( "attachment; filename=\"%s\"", file.getName() ) );
        headers.add( "Cache-Control", "no-cache, no-store, must-revalidate" );
        headers.add( "Pragma", "no-cache" );
        headers.add( "Expires", "0" );

        ResponseEntity<Object> responseEntity = ResponseEntity.ok()
                .headers( headers )
                .contentLength( tempFile.length() )
                .contentType( MediaType.parseMediaType( file.getType() ) )
                .body( resource );

        return responseEntity;
    }

}
