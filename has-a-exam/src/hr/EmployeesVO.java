package hr;

import java.util.List;

/**
 * @TableName EMPLOYEES
 * @TableComment employees table. Contains 107 rows. References with departments, 
jobs, job_history tables. Contains a self reference.
 */
public class EmployeesVO {

	private JobsVO jobs;
	private List<JobHistoryVO> jobHistory;

    /**
     * @ColumnName EMPLOYEE_ID
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment Primary key of employees table.
     */
    private int employeeId;

    /**
     * @ColumnName FIRST_NAME
     * @ColumnType VARCHAR2(20)
     * @ColumnComment First name of the employee. A not null column.
     */
    private String firstName;

    /**
     * @ColumnName LAST_NAME
     * @ColumnType VARCHAR2(25)
     * @ColumnComment Last name of the employee. A not null column.
     */
    private String lastName;

    /**
     * @ColumnName EMAIL
     * @ColumnType VARCHAR2(25)
     * @ColumnComment Email id of the employee
     */
    private String email;

    /**
     * @ColumnName PHONE_NUMBER
     * @ColumnType VARCHAR2(20)
     * @ColumnComment Phone number of the employee; includes country code and area code
     */
    private String phoneNumber;

    /**
     * @ColumnName HIRE_DATE
     * @ColumnType DATE
     * @ColumnComment Date when the employee started on this job. A not null column.
     */
    private String hireDate;

    /**
     * @ColumnName JOB_ID
     * @ColumnType VARCHAR2(10)
     * @ColumnComment Current job of the employee; foreign key to job_id column of the 
jobs table. A not null column.
     */
    private String jobId;

    /**
     * @ColumnName SALARY
     * @ColumnType NUMBER(8, 2)
     * @ColumnComment Monthly salary of the employee. Must be greater 
than zero (enforced by constraint emp_salary_min)
     */
    private int salary;

    /**
     * @ColumnName COMMISSION_PCT
     * @ColumnType NUMBER(2, 2)
     * @ColumnComment Commission percentage of the employee; Only employees in sales 
department elgible for commission percentage
     */
    private int commissionPct;

    /**
     * @ColumnName MANAGER_ID
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment Manager id of the employee; has same domain as manager_id in 
departments table. Foreign key to employee_id column of employees table.
(useful for reflexive joins and CONNECT BY query)
     */
    private int managerId;

    /**
     * @ColumnName DEPARTMENT_ID
     * @ColumnType NUMBER(4, 0)
     * @ColumnComment Department id where employee works; foreign key to department_id 
column of the departments table
     */
    private int departmentId;
//has	
	private DepartmentsVO departments;
	public DepartmentsVO getDepartments() {
		return departments;
	}

	public void setDepartments(DepartmentsVO departments) {
		this.departments = departments;
	}

	public JobsVO getJobs() {
		return jobs;
	}

	public void setJobs(JobsVO jobs) {
		this.jobs = jobs;
	}

	public List<JobHistoryVO> getJobHistory() {
		return jobHistory;
	}

	public void setJobHistory(List<JobHistoryVO> jobHistory) {
		this.jobHistory = jobHistory;
	}
//has    
    public int getEmployeeId() {
        return this.employeeId;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getHireDate() {
        return this.hireDate;
    }
    
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
    
    public String getJobId() {
        return this.jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    public int getSalary() {
        return this.salary;
    }
    
    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    public int getCommissionPct() {
        return this.commissionPct;
    }
    
    public void setCommissionPct(int commissionPct) {
        this.commissionPct = commissionPct;
    }
    
    public int getManagerId() {
        return this.managerId;
    }
    
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    public int getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    @Override
    public String toString() {
        return "EmployeesVO(employeeId: " + employeeId + ", firstName: " + firstName + ", lastName: " + lastName + ", email: " + email + ", phoneNumber: " + phoneNumber + ", hireDate: " + hireDate + ", jobId: " + jobId + ", salary: " + salary + ", commissionPct: " + commissionPct + ", managerId: " + managerId + ", departmentId: " + departmentId + ", )";
    }
}