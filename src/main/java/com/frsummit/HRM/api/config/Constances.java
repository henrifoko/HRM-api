package com.frsummit.HRM.api.config;

import java.lang.reflect.Array;
import java.util.List;

import com.frsummit.HRM.api.model.RemoteCallee;
import com.frsummit.HRM.api.rmi.entity.Address;
import com.frsummit.HRM.api.rmi.entity.Attendance;
import com.frsummit.HRM.api.rmi.entity.Leaves;
import com.frsummit.HRM.api.rmi.entity.Payroll;
import com.frsummit.HRM.api.rmi.entity.User;

/**
 * 
 * @author hfoko
 * 
 */
public class Constances {
	// ================== GENERAL CONSTANCE ==================

	public static final String SERVICE_PACKAGE = "com.frsummit.HRM.api.rmi.service";

	// ================== REMOTE CALLED SERVICES WITH THEIR ATTRIBUTES

	public static final RemoteCallee MESSAGE_GET_ALL_PARAMS = new RemoteCallee("com.frsummit.HRM.service",
			"MessageService", "loadALLMessage", new Class[0], List.class);
	// public static RemoteCallParams ATTENDANCE_GET_ALL_PARAMS = new
	// RemoteCallParams("AttendanceService", "");
	public static final RemoteCallee PAYROLL_GET_ALL_PARAMS = new RemoteCallee("com.frsummit.HRM.service",
			"PayrollService", "findAllUsersPayroll", new Class[0], List.class);
	public static final RemoteCallee LINKS_GET_ALL_PARAMS = new RemoteCallee("com.frsummit.HRM.service", "LinkService",
			"findAllLinks", new Class[0], List.class);
	public static final RemoteCallee ROLE_GET_ALL_PARAMS = new RemoteCallee("com.frsummit.HRM.service", "RoleService",
			"findAllRoles", new Class[0], List.class);
	public static final RemoteCallee HRRECORD_GET_ALL_PARAMS = new RemoteCallee("com.frsummit.HRM.service",
			"HRRecordService", "findAllHRRecords", new Class[0], List.class);
	public static final RemoteCallee REMARK_ATTENDANCES_PARAMS = new RemoteCallee("com.frsummit.HRM.service",
			"AttendanceService", "remarkAttendanceList", new Class[0], List.class);

	// ATTENDANCE
	public static final RemoteCallee ATTENDANCE_CREATE = new RemoteCallee(SERVICE_PACKAGE, "AttendanceServiceExposed",
			"saveAttendance", new Class[] { Attendance.class }, void.class);
	public static final RemoteCallee ATTENDANCE_SET_REMARK = new RemoteCallee(SERVICE_PACKAGE, "AttendanceServiceExposed",
			"remarkAttendance", new Class[] { String.class }, void.class);
	public static final RemoteCallee ATTENDANCE_REMOVE_FROM_REMARK_LIST = new RemoteCallee(SERVICE_PACKAGE,
			"AttendanceServiceExposed", "removeFromRemarkList", new Class[] { String.class }, void.class);
	public static final RemoteCallee ATTENDANCE_GET_ALL_SIGNED = new RemoteCallee(SERVICE_PACKAGE,
			"AttendanceServiceExposed", "allSignList", new Class[] { String.class }, List.class);
	public static final RemoteCallee ATTENDANCE_GET_ALL = new RemoteCallee(SERVICE_PACKAGE,
			"AttendanceServiceExposed", "UsersSignList", new Class[] {}, List.class);
	public static final RemoteCallee ATTENDANCE_GET_ALL_REMARK = new RemoteCallee(SERVICE_PACKAGE,
			"AttendanceServiceExposed", "remarkAttendanceList", new Class[] {}, List.class);
	public static final RemoteCallee ATTENDANCE_GET_BY_ID = new RemoteCallee(SERVICE_PACKAGE,
			"AttendanceServiceExposed", "findById", new Class[] {int.class }, Attendance.class);

	// EMERGENCY_CONTACT
//	public static final RemoteCallee EMEREGNCY_CONTACT_CREATE = new RemoteCallee(SERVICE_PACKAGE,
//			"EmergencyContactServiceExposed", "saveEmergencyContact", new Class[] { EmergencyContact.class },
//			void.class);

	// HR_RECORD
//	public static final RemoteCallee HR_RECORD_CREATE = new RemoteCallee(SERVICE_PACKAGE, "HRRecordServiceExposed",
//			"saveHRRecord", new Class[] { HRRecord.class }, void.class);
//	public static final RemoteCallee HR_RECORD_GET_ALL_RECORD = new RemoteCallee(SERVICE_PACKAGE,
//			"HRRecordServiceExposed", "getAllRecord", new Class[] { String.class }, List.class);
//	public static final RemoteCallee HR_RECORD_GET_ALL_RECORD_BY_DEPT = new RemoteCallee(SERVICE_PACKAGE,
//			"HRRecordServiceExposed", "getAllRecordByDept", new Class[] { String.class }, List.class);
//	public static final RemoteCallee HR_RECORD_GET_ALL_HR_RECORDS = new RemoteCallee(SERVICE_PACKAGE,
//			"HRRecordServiceExposed", "findAllHRRecords", new Class[] {}, List.class);
//	public static final RemoteCallee HR_RECORD_UPDATE = new RemoteCallee(SERVICE_PACKAGE, "HRRecordServiceExposed",
//			"updateHRRecord", new Class[] { String.class, String.class, int.class, int.class }, void.class);

	// LEAVE
	public static final RemoteCallee LEAVE_CREATE = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"saveLeave", new Class[] { Leaves.class }, void.class);
	public static final RemoteCallee LEAVE_UPDATE_STATUS = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"updateLeave", new Class[] { String.class, String.class }, void.class);
	public static final RemoteCallee LEAVE_GET_ALL = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"findAllLeaves", new Class[] {}, List.class);
	public static final RemoteCallee LEAVE_GET_BY_ROLE = new RemoteCallee(SERVICE_PACKAGE,
			"LeaveServiceExposed", "findAllLeavesByRole", new Class[] { String.class }, List.class);
	public static final RemoteCallee LEAVE_UPDATE = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"updateLeaveStatus", new Class[] { String.class, String.class, String.class, String.class }, void.class);
	public static final RemoteCallee LEAVE_GET_BY_ID = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"findLeavesByLeaveId", new Class[] { int.class }, List.class);
	public static final RemoteCallee LEAVE_GET_GRANTED_BY_USER_ID = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"findLeavesByUserId", new Class[] { String.class }, List.class);
	public static final RemoteCallee LEAVE_GET_ALL_BY_USER_ID = new RemoteCallee(SERVICE_PACKAGE,
			"LeaveServiceExposed", "findAllLeavesByUserId", new Class[] { String.class }, List.class);
	public static final RemoteCallee LEAVE_CANCEL = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"cancelLeave", new Class[] { int.class }, void.class);
	public static final RemoteCallee LEAVE_GET_REPORT_CONSOLIDATED = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"getLeaveReportConsolidated", new Class[] {}, List.class);
	public static final RemoteCallee LEAVE_GET_REPORT_CONSOLIDATED_SELECTED_BY_DATE = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"getLeaveReportConsolidatedSelectedByDate", new Class[] {String.class, String.class, String.class}, List.class);
	public static final RemoteCallee LEAVE_PRINT_INDIVIDUAL_REPORT = new RemoteCallee(SERVICE_PACKAGE, "LeaveServiceExposed",
			"printIndividualReport", new Class[] {User.class}, byte[].class);
	// LINK
//	public static final RemoteCallee LINK_CREATE = new RemoteCallee(SERVICE_PACKAGE, "LinkServiceExposed", "saveLink",
//			new Class[] { Links.class }, void.class);
//	public static final RemoteCallee LINK_GET_ALL_LINKS = new RemoteCallee(SERVICE_PACKAGE, "LinkServiceExposed",
//			"findAllLinks", new Class[] {}, List.class);

	// MESSAGE
//	public static final RemoteCallee MESSAGE_CREATE = new RemoteCallee(SERVICE_PACKAGE, "MessageServiceExposed",
//			"saveMessage", new Class[] { Message.class }, void.class);
//	public static final RemoteCallee MESSAGE_GET_ALL = new RemoteCallee(SERVICE_PACKAGE, "MessageServiceExposed",
//			"loadALLMessage", new Class[] {}, List.class);
//	public static final RemoteCallee MESSAGE_GET_BY_ID = new RemoteCallee(SERVICE_PACKAGE, "MessageServiceExposed",
//			"findALLMessageById", new Class[] { String.class }, List.class);
//	public static final RemoteCallee MESSAGE_GET_UNCKECKED_BY_ID = new RemoteCallee(SERVICE_PACKAGE,
//			"MessageServiceExposed", "findMessage", new Class[] { String.class }, List.class);

	// PAYROLL
	public static final RemoteCallee PAYROLL_CREATE = new RemoteCallee(SERVICE_PACKAGE, "PayrollServiceExposed",
			"savePayroll", new Class[] { Payroll.class }, void.class);
	public static final RemoteCallee PAYROLL_GET_ALL = new RemoteCallee(SERVICE_PACKAGE, "PayrollServiceExposed",
			"findAllUsersPayroll", new Class[] {}, List.class);
	public static final RemoteCallee PAYROLL_GET_BY_USER_ID = new RemoteCallee(SERVICE_PACKAGE, "PayrollServiceExposed",
			"findSpecificUserPayroll", new Class[] { String.class }, List.class);
	public static final RemoteCallee PAYROLL_GET_CURRENT_PAYROLL_FOR_USER = new RemoteCallee(SERVICE_PACKAGE, "PayrollServiceExposed",
			"findUserCurrentPayroll", new Class[] { String.class }, List.class);
//	public static final RemoteCallee PAYROLL_GET_HISTORY_BY_USER_ID = new RemoteCallee(SERVICE_PACKAGE,
//			"PayrollServiceExposed", "findUserPayrollHistory", new Class[] { String.class }, List.class);

	// ROLE
//	public static final RemoteCallee ROLE_GET_ALL = new RemoteCallee(SERVICE_PACKAGE, "RoleServiceExposed",
//			"findAllRoles", new Class[] {}, List.class);

	// TEST_IMAGE
	public static final RemoteCallee TEST_IMAGE_GET = new RemoteCallee(SERVICE_PACKAGE, "TestImgServiceExposed",
			"findImg", new Class[] {}, List.class);

	// USER
	public static final RemoteCallee USER_GET_ALL = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed",
			"findAllUsers", new Class[0], List.class);
	public static final RemoteCallee USER_GET_BY_ID = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed",
			"findUserById", new Class[] { String.class }, User.class);
	public static final RemoteCallee USER_GET_BY_DEPARTMENT = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed",
			"findUserByDepartment", new Class[] { String.class }, List.class);
	public static final RemoteCallee USER_GET_BY_EMAIL = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed",
			"findUserByEmail", new Class[] { String.class }, User.class);
	public static final RemoteCallee USER_CREATE = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed", "saveUser",
			new Class[] { User.class }, void.class);
	public static final RemoteCallee USER_UPDATE = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed",
			"updateUser",
			new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class,
					Address.class, Address.class, String.class, String.class, String.class, String.class,
					int.class},
			void.class);
	public static final RemoteCallee USER_UPDATE_PROPERTY = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed",
			"updateUserProperty", new Class[] { String.class, String.class, Object.class }, void.class);
	public static final RemoteCallee USER_DELETE = new RemoteCallee(SERVICE_PACKAGE, "UserServiceExposed",
			"deleteUser", new Class[] { String.class }, void.class);

	// ================== AUTHENTICATION ==================

	public static final String[] ROLES = { "ADMIN", "USER", "VC" };

	// ================= OTHER PROPERTIES =================

	public static final int RMI_PORT = 9082;
	public static final String REMOTE_SERVICE_NAME = "hrm_generic_service";
	public static final int TIMEOUT_CHECK = 10000;
}
