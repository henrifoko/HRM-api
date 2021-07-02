package com.frsummit.HRM.api.service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.config.Constances;
import com.frsummit.HRM.api.model.LocalProxy;
import com.frsummit.HRM.api.model.RemoteCallee;
import com.frsummit.HRM.api.rmi.RemoteServiceInterface;
import com.frsummit.HRM.api.rmi.model.Attendance;
import com.frsummit.HRM.api.rmi.model.HRRecord;
import com.frsummit.HRM.api.rmi.model.Leaves;
import com.frsummit.HRM.api.rmi.model.Links;
import com.frsummit.HRM.api.rmi.model.Message;
import com.frsummit.HRM.api.rmi.model.Payroll;
import com.frsummit.HRM.api.rmi.model.Role;
import com.frsummit.HRM.api.rmi.model.User;

@Service
public class HRMRemoteService {
	Registry registry; 

	public HRMRemoteService() {
		try {
			System.setProperty("java.rmi.server.hostname", "192.168.56.1");
			System.setSecurityManager(new SecurityManager());

			registry = LocateRegistry.getRegistry(Constances.RMI_PORT);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error on the client");
			e.printStackTrace();
		}
	}
	
	public RemoteServiceInterface getStub() {
		try {
			return (RemoteServiceInterface) registry.lookup(Constances.REMOTE_SERVICE_NAME);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Iterable<User> getAllUsers() throws RemoteException {
		Iterable<User> users;
		final RemoteCallee GET_ALL_PARAMS = Constances.USER_GET_ALL;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		users = (Iterable<User>) proxy.callWith(new Object[0]);

		return (Iterable<User>) (new LocalProxy(getStub(), Constances.USER_GET_ALL)).callWith(new Object[0]);
	}

	public User getUserById(String userId) throws RemoteException {
		User user;
		final RemoteCallee GET_BY_ID = Constances.USER_GET_BY_ID;
		LocalProxy proxy = new LocalProxy(getStub(), GET_BY_ID);

		user = (User) proxy.callWith(new Object[] { userId });

		return user;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Role> getAllRoles() throws RemoteException {
		Iterable<Role> roles;
		final RemoteCallee GET_ALL_PARAMS = Constances.ROLE_GET_ALL_PARAMS;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		roles = (Iterable<Role>) proxy.callWith(new Object[0]);

		return roles;
	}

	@SuppressWarnings("unchecked")
	public Iterable<HRRecord> getAllHRRecords() throws RemoteException {
		Iterable<HRRecord> allHRRecords;
		final RemoteCallee GET_ALL_PARAMS = Constances.HRRECORD_GET_ALL_PARAMS;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		allHRRecords = (Iterable<HRRecord>) proxy.callWith(new Object[0]);

		return allHRRecords;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Attendance> getRemarkAttendances() throws RemoteException {
		Iterable<Attendance> attendances;
		final RemoteCallee GET_ALL_PARAMS = Constances.REMARK_ATTENDANCES_PARAMS;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		attendances = (Iterable<Attendance>) proxy.callWith(new Object[0]);

		return attendances;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Leaves> getAllLeaves() throws RemoteException {
		Iterable<Leaves> leaves;
		final RemoteCallee GET_ALL_PARAMS = Constances.LEAVES_GET_ALL_PARAMS;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		leaves = (Iterable<Leaves>) proxy.callWith(new Object[0]);

		return leaves;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Links> getAllLinks() throws RemoteException {
		Iterable<Links> links;
		final RemoteCallee GET_ALL_PARAMS = Constances.LINKS_GET_ALL_PARAMS;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		links = (Iterable<Links>) proxy.callWith(new Object[0]);

		return links;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Message> getAllMessages() throws RemoteException {
		Iterable<Message> messages;
		final RemoteCallee GET_ALL_PARAMS = Constances.MESSAGE_GET_ALL_PARAMS;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		messages = (Iterable<Message>) proxy.callWith(new Object[0]);

		return messages;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Payroll> getAllPayrolls() throws RemoteException {
		Iterable<Payroll> payrolls;
		final RemoteCallee GET_ALL_PARAMS = Constances.PAYROLL_GET_ALL_PARAMS;
		LocalProxy proxy = new LocalProxy(getStub(), GET_ALL_PARAMS);

		payrolls = (Iterable<Payroll>) proxy.callWith(new Object[0]);

		return payrolls;
	}
	
	@SuppressWarnings("unchecked")
	public Iterable<User> getUserByDepartment(String dept) throws RemoteException {
		Iterable<User> users;
		final RemoteCallee GET_BY_DEPARTMENT = Constances.USER_GET_BY_DEPARTMENT;
		LocalProxy proxy = new LocalProxy(getStub(), GET_BY_DEPARTMENT);

		users = (Iterable<User>) proxy.callWith(new Object[] { dept });

		return users;
	}

	public User getUserByEmail(String email) throws RemoteException {
		User user;
		final RemoteCallee GET_BY_EMAIL = Constances.USER_GET_BY_EMAIL;
		LocalProxy proxy = new LocalProxy(getStub(), GET_BY_EMAIL);

		user = (User) proxy.callWith(new Object[] { email });

		return user;
	}

	public User createUser(User user) throws RemoteException {
		final RemoteCallee CREATE = Constances.USER_CREATE;
		LocalProxy proxy = new LocalProxy(getStub(), CREATE);

		user = (User) proxy.callWith(new Object[] { user });

		return user;
	}
	
	public User updateUser(String userId, User user) throws RemoteException {
		final RemoteCallee UPDATE = Constances.USER_UPDATE;
		LocalProxy proxy = new LocalProxy(getStub(), UPDATE);

		user = (User) proxy.callWith(new Object[] { user });

		return user;
	}
}
