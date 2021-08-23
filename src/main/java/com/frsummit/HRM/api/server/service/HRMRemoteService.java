package com.frsummit.HRM.api.server.service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.config.Constances;
import com.frsummit.HRM.api.server.RemoteServiceInterface;
import com.frsummit.HRM.api.server.entity.Address;
import com.frsummit.HRM.api.server.entity.Attendance;
import com.frsummit.HRM.api.server.entity.HRRecord;
import com.frsummit.HRM.api.server.entity.Leaves;
import com.frsummit.HRM.api.server.entity.Links;
import com.frsummit.HRM.api.server.entity.Message;
import com.frsummit.HRM.api.server.entity.Payroll;
import com.frsummit.HRM.api.server.entity.Role;
import com.frsummit.HRM.api.server.entity.User;
import com.frsummit.HRM.api.server.model.LocalProxy;

@Service
public class HRMRemoteService {
    Registry               registry;
    RemoteServiceInterface hrmStub;

    public HRMRemoteService() {
        try {
            System.setProperty( "java.rmi.server.hostname", "192.168.56.1" );
            System.setSecurityManager( new SecurityManager() );

            registry = LocateRegistry.getRegistry( Constances.RMI_PORT );
            hrmStub = getStub();
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            System.out.println( "Error on the client" );
            e.printStackTrace();
        }
    }

    public RemoteServiceInterface getStub() {
        try {
            return (RemoteServiceInterface) registry.lookup( Constances.REMOTE_SERVICE_NAME );
        } catch ( RemoteException | NotBoundException e ) {
            // e.printStackTrace();
            return null;
        } catch ( Exception e ) {
            return null;
        }
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Role> getAllRoles() throws RemoteException {
        return (Iterable<Role>) ( new LocalProxy( hrmStub, Constances.ROLE_GET_ALL_PARAMS ) ).callWith( new Object[0] );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<HRRecord> getAllHRRecords() throws RemoteException {
        return (Iterable<HRRecord>) ( new LocalProxy( hrmStub, Constances.HRRECORD_GET_ALL_PARAMS ) )
                .callWith( new Object[0] );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Links> getAllLinks() throws RemoteException {
        return (Iterable<Links>) ( new LocalProxy( hrmStub, Constances.LINKS_GET_ALL_PARAMS ) )
                .callWith( new Object[0] );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Message> getAllMessages() throws RemoteException {
        return (Iterable<Message>) ( new LocalProxy( hrmStub, Constances.MESSAGE_GET_ALL_PARAMS ) )
                .callWith( new Object[0] );
    }

    // ==========================================
    // == User
    // ==========================================

    @SuppressWarnings( "unchecked" )
    public Iterable<User> getAllUsers() throws RemoteException {
        return (Iterable<User>) ( new LocalProxy( hrmStub, Constances.USER_GET_ALL ) ).callWith( new Object[0] );
    }

    public User getUserById( String userId ) throws RemoteException {
        return (User) ( new LocalProxy( hrmStub, Constances.USER_GET_BY_ID ) ).callWith( new Object[] { userId } );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<User> getUserByDepartment( String dept ) throws RemoteException {
        return (Iterable<User>) ( new LocalProxy( hrmStub, Constances.USER_GET_BY_DEPARTMENT ) )
                .callWith( new Object[] { dept } );
    }

    public User getUserByEmail( String email ) throws RemoteException {
        return (User) ( new LocalProxy( hrmStub, Constances.USER_GET_BY_EMAIL ) ).callWith( new Object[] { email } );
    }

    public User createUser( User user ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.USER_CREATE ) ).callWith( new Object[] { user } );
        return this.getUserById( user.getId() );
    }

    public User updateUser( String userId, String fn, String mn, String ln, String email, String dept, String desg,
            String dob, String sex, String phone, String bg, String father, String mother, String nid, String passport,
            Address psa, Address pma, String nationality, String role, String imageName, String incomeTexNo,
            int active )
            throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.USER_UPDATE ) )
                .callWith( new Object[] { userId, fn, mn, ln, email, dept, desg, dob, sex, phone, bg, father, mother,
                        nid, passport, psa, pma, nationality, role, imageName, incomeTexNo, active } );
        return this.getUserById( userId );
    }

    public User updateUserProperty( String userId, String prop, Object value ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.USER_UPDATE_PROPERTY ) ).callWith( new Object[] { userId, prop, value } );
        return this.getUserById( userId );
    }

    public User deleteUser( String userId ) throws RemoteException {
        User user = this.getUserById( userId );
        ( new LocalProxy( hrmStub, Constances.USER_DELETE ) ).callWith( new Object[] { userId } );
        return user;
    }

    // ==========================================
    // == Attendance
    // ==========================================

    @SuppressWarnings( "unchecked" )
    public Iterable<Attendance> getAllSignedAttendance( String userId ) throws RemoteException {
        return (Iterable<Attendance>) ( new LocalProxy( hrmStub, Constances.ATTENDANCE_GET_ALL_SIGNED ) )
                .callWith( new Object[] { userId } );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Attendance> getAllAttendances() throws RemoteException {
        return (Iterable<Attendance>) ( new LocalProxy( hrmStub, Constances.ATTENDANCE_GET_ALL ) )
                .callWith( new Object[] {} );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Attendance> getAllRemarkAttendance() throws RemoteException {
        return (Iterable<Attendance>) ( new LocalProxy( hrmStub, Constances.ATTENDANCE_GET_ALL_REMARK ) )
                .callWith( new Object[] {} );
    }

    public Attendance createAttendance( Attendance attendance ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.ATTENDANCE_CREATE ) ).callWith( new Object[] { attendance } );
        Attendance att = this.getAttendanceById( attendance.getAttendId() );
        return att;
    }

    public Attendance getAttendanceById( int attendId ) throws RemoteException {
        return (Attendance) ( new LocalProxy( hrmStub, Constances.ATTENDANCE_GET_BY_ID ) )
                .callWith( new Object[] { attendId } );
    }

    public Attendance setAttendanceRemark( String attendId ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.ATTENDANCE_SET_REMARK ) ).callWith( new Object[] { attendId } );
        return this.getAttendanceById( Integer.parseInt( attendId ) );
    }

    public Attendance removeAttendanceFromRemarkList( String attendId ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.ATTENDANCE_REMOVE_FROM_REMARK_LIST ) )
                .callWith( new Object[] { attendId } );
        return this.getAttendanceById( Integer.parseInt( attendId ) );
    }

    // ==========================================
    // == Payroll
    // ==========================================

    @SuppressWarnings( "unchecked" )
    public Iterable<Payroll> getAllPayrolls() throws RemoteException {
        return (Iterable<Payroll>) ( new LocalProxy( hrmStub, Constances.PAYROLL_GET_ALL ) )
                .callWith( new Object[] {} );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Payroll> getPayrollForSpecificUser( String userId ) throws RemoteException {
        return (Iterable<Payroll>) ( new LocalProxy( hrmStub, Constances.PAYROLL_GET_BY_USER_ID ) )
                .callWith( new Object[] { userId } );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Payroll> getCurrentPayrollForUser( String userId ) throws RemoteException {
        return (Iterable<Payroll>) ( new LocalProxy( hrmStub, Constances.PAYROLL_GET_CURRENT_PAYROLL_FOR_USER ) )
                .callWith( new Object[] { userId } );
    }

    public Payroll createPayroll( Payroll payroll ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.PAYROLL_CREATE ) ).callWith( new Object[] { payroll } );
        return payroll;
    }

    // ==========================================
    // == Leaves
    // ==========================================

    @SuppressWarnings( "unchecked" )
    public Iterable<Leaves> getAllLeaves() throws RemoteException {
        return (Iterable<Leaves>) ( new LocalProxy( hrmStub, Constances.LEAVE_GET_ALL ) ).callWith( new Object[0] );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Leaves> getGrantedLeavesForUser( String userId ) throws RemoteException {
        return (Iterable<Leaves>) ( new LocalProxy( hrmStub, Constances.LEAVE_GET_GRANTED_BY_USER_ID ) )
                .callWith( new Object[] { userId } );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Leaves> getAllLeavesForUser( String userId ) throws RemoteException {
        return (Iterable<Leaves>) ( new LocalProxy( hrmStub, Constances.LEAVE_GET_ALL_BY_USER_ID ) )
                .callWith( new Object[] { userId } );
    }

    @SuppressWarnings( "unchecked" )
    public Iterable<Leaves> getLeavesByRole( String role ) throws RemoteException {
        return (Iterable<Leaves>) ( new LocalProxy( hrmStub, Constances.LEAVE_GET_BY_ROLE ) )
                .callWith( new Object[] { role } );
    }

    @SuppressWarnings( "unchecked" )
    public Leaves getLeaveById( int leaveId ) throws RemoteException {
        return ( (Iterable<Leaves>) ( new LocalProxy( hrmStub, Constances.LEAVE_GET_BY_ID ) )
                .callWith( new Object[] { leaveId } ) ).iterator().next();
    }

    public Leaves cancelLeave( int leaveId ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.LEAVE_CANCEL ) ).callWith( new Object[] { leaveId } );
        return this.getLeaveById( leaveId );
    }

    public Leaves createLeaves( Leaves leaves ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.LEAVE_CREATE ) ).callWith( new Object[] { leaves } );
        return leaves;
    }

    public Leaves updateLeaveStatus( int leaveId, String status ) throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.LEAVE_UPDATE_STATUS ) ).callWith( new Object[] { leaveId, status } );
        return (Leaves) this.getLeaveById( leaveId );
    }

    public Leaves updateLeave( int leaveId, String selectStatus, String leaveActionBy, String modifyTo )
            throws RemoteException {
        ( new LocalProxy( hrmStub, Constances.LEAVE_UPDATE ) )
                .callWith( new Object[] { leaveId, selectStatus, leaveActionBy, modifyTo } );
        return (Leaves) this.getLeaveById( leaveId );
    }

    @SuppressWarnings( "unchecked" )
    public List<HRRecord> getLeaveReportConsolidated() throws RemoteException {
        return (List<HRRecord>) ( new LocalProxy( hrmStub, Constances.LEAVE_GET_REPORT_CONSOLIDATED ) )
                .callWith( new Object[] {} );
    }

    @SuppressWarnings( "unchecked" )
    public List<HRRecord> getLeaveReportConsolidatedSelectedByDate( String department, String dateFrom, String dateTo )
            throws RemoteException {
        return (List<HRRecord>) ( new LocalProxy( hrmStub, Constances.LEAVE_GET_REPORT_CONSOLIDATED_SELECTED_BY_DATE ) )
                .callWith( new Object[] { department, dateFrom, dateTo } );
    }

    public byte[] printIndividualReport( User user ) throws RemoteException {
        return (byte[]) ( new LocalProxy( hrmStub, Constances.LEAVE_PRINT_INDIVIDUAL_REPORT ) )
                .callWith( new Object[] { user } );
    }
}
