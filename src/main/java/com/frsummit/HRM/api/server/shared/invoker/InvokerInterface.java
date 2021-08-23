package com.frsummit.HRM.api.server.shared.invoker;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.frsummit.HRM.api.server.shared.command.Command;

public interface InvokerInterface extends Remote {

    public Object exec( Command command ) throws RemoteException;

}
