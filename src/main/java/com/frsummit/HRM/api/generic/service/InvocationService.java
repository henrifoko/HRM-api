package com.frsummit.HRM.api.generic.service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.HrmApiApplication;
import com.frsummit.HRM.api.config.Constances;
import com.frsummit.HRM.api.server.shared.command.Command;
import com.frsummit.HRM.api.server.shared.invoker.InvokerInterface;

/**
 * 
 * @author hfoko
 *
 */
@Service
public class InvocationService {
    Registry         registry;
    InvokerInterface invoker;

    /**
     * 
     */
    public InvocationService() {
        try {
            System.setProperty( "java.rmi.server.hostname", "192.168.56.1" );
            System.setSecurityManager( new SecurityManager() );

            registry = LocateRegistry.getRegistry( Constances.RMI_PORT );
            invoker = getStub();
        } catch ( RemoteException e ) {
            e.printStackTrace();

            HrmApiApplication.exit();
        } catch ( Exception e ) {
            e.printStackTrace();

            HrmApiApplication.exit();
        }
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public InvokerInterface getStub() throws Exception {
        try {
            return (InvokerInterface) registry.lookup( Constances.REMOTE_SERVICE_NAME );
        } catch ( RemoteException | NotBoundException e ) {
            e.printStackTrace();

            throw e;
        }
    }

    /**
     * 
     * @param cmd
     * @return
     * @throws RemoteException
     */
    public Object invoke( Command cmd ) throws RemoteException {
        Object result = invoker.exec( cmd );
        return result;
    }
}
