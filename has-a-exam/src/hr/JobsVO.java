package hr;

/**
 * @TableName JOBS
 * @TableComment jobs table with job titles and salary ranges. Contains 19 rows.
References with employees and job_history table.
 */
public class JobsVO {

    /**
     * @ColumnName JOB_ID
     * @ColumnType VARCHAR2(10)
     * @ColumnComment Primary key of jobs table.
     */
    private String jobId;

    /**
     * @ColumnName JOB_TITLE
     * @ColumnType VARCHAR2(35)
     * @ColumnComment A not null column that shows job title, e.g. AD_VP, FI_ACCOUNTANT
     */
    private String jobTitle;

    /**
     * @ColumnName MIN_SALARY
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment Minimum salary for a job title.
     */
    private int minSalary;

    /**
     * @ColumnName MAX_SALARY
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment Maximum salary for a job title
     */
    private int maxSalary;

    public String getJobId() {
        return this.jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    public String getJobTitle() {
        return this.jobTitle;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public int getMinSalary() {
        return this.minSalary;
    }
    
    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }
    
    public int getMaxSalary() {
        return this.maxSalary;
    }
    
    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }
    
    @Override
    public String toString() {
        return "JobsVO(jobId: " + jobId + ", jobTitle: " + jobTitle + ", minSalary: " + minSalary + ", maxSalary: " + maxSalary + ", )";
    }
}