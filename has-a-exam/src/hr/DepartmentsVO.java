package hr;

/**
 * @TableName DEPARTMENTS
 * @TableComment Departments table that shows details of departments where employees 
work. Contains 27 rows; references with locations, employees, and job_history tables.
 */
public class DepartmentsVO {
	
    /**
     * @ColumnName DEPARTMENT_ID
     * @ColumnType NUMBER(4, 0)
     * @ColumnComment Primary key column of departments table.
     */
    private int departmentId;

    /**
     * @ColumnName DEPARTMENT_NAME
     * @ColumnType VARCHAR2(30)
     * @ColumnComment A not null column that shows name of a department. Administration, 
Marketing, Purchasing, Human Resources, Shipping, IT, Executive, Public 
Relations, Sales, Finance, and Accounting. 
     */
    private String departmentName;

    /**
     * @ColumnName MANAGER_ID
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment Manager_id of a department. Foreign key to employee_id column of employees table. The manager_id column of the employee table references this column.
     */
    private int managerId;

    /**
     * @ColumnName LOCATION_ID
     * @ColumnType NUMBER(4, 0)
     * @ColumnComment Location id where a department is located. Foreign key to location_id column of locations table.
     */
    private int locationId;
    
    //has
    private LocationsVO locations;
    
    
    public LocationsVO getLocations() {
		return locations;
	}

	public void setLocations(LocationsVO locations) {
		this.locations = locations;
	}

	public int getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getDepartmentName() {
        return this.departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public int getManagerId() {
        return this.managerId;
    }
    
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    public int getLocationId() {
        return this.locationId;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
    @Override
    public String toString() {
        return "DepartmentsVO(departmentId: " + departmentId + ", departmentName: " + departmentName + ", managerId: " + managerId + ", locationId: " + locationId + ", )";
    }
}