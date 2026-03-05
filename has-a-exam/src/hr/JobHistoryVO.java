package hr;

/**
 * @TableName JOB_HISTORY
 * @TableComment Table that stores job history of the employees. If an employee 
changes departments within the job or changes jobs within the department, 
new rows get inserted into this table with old job information of the 
employee. Contains a complex primary key: employee_id+start_date.
Contains 25 rows. References with jobs, employees, and departments tables.
 */
public class JobHistoryVO {

    /**
     * @ColumnName EMPLOYEE_ID
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment A not null column in the complex primary key employee_id+start_date.
Foreign key to employee_id column of the employee table
     */
    private int employeeId;

    /**
     * @ColumnName START_DATE
     * @ColumnType DATE
     * @ColumnComment A not null column in the complex primary key employee_id+start_date. 
Must be less than the end_date of the job_history table. (enforced by 
constraint jhist_date_interval)
     */
    private String startDate;

    /**
     * @ColumnName END_DATE
     * @ColumnType DATE
     * @ColumnComment Last day of the employee in this job role. A not null column. Must be 
greater than the start_date of the job_history table. 
(enforced by constraint jhist_date_interval)
     */
    private String endDate;

    /**
     * @ColumnName JOB_ID
     * @ColumnType VARCHAR2(10)
     * @ColumnComment Job role in which the employee worked in the past; foreign key to 
job_id column in the jobs table. A not null column.
     */
    private String jobId;

    /**
     * @ColumnName DEPARTMENT_ID
     * @ColumnType NUMBER(4, 0)
     * @ColumnComment Department id in which the employee worked in the past; foreign key to deparment_id column in the departments table
     */
    private int departmentId;
    
    private JobsVO jobs;
    public JobsVO getJobs() {
		return jobs;
	}

	public void setJobs(JobsVO jobs) {
		this.jobs = jobs;
	}

	public DepartmentsVO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentsVO department) {
		this.department = department;
	}

	private DepartmentsVO department;

    public int getEmployeeId() {
        return this.employeeId;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getJobId() {
        return this.jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    public int getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    @Override
    public String toString() {
        return "JobHistoryVO(employeeId: " + employeeId + ", startDate: " + startDate + ", endDate: " + endDate + ", jobId: " + jobId + ", departmentId: " + departmentId + ", )";
    }
}