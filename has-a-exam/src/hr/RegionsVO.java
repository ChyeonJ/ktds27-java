package hr;

/**
 * @TableName REGIONS
 * @TableComment Regions table that contains region numbers and names. Contains 4 rows; references with the Countries table.
 */
public class RegionsVO {

    /**
     * @ColumnName REGION_ID
     * @ColumnType NUMBER(, )
     * @ColumnComment Primary key of regions table.
     */
    private int regionId;

    /**
     * @ColumnName REGION_NAME
     * @ColumnType VARCHAR2(25)
     * @ColumnComment Names of regions. Locations are in the countries of these regions.
     */
    private String regionName;
    
	public int getRegionId() {
        return this.regionId;
    }
    
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    
    public String getRegionName() {
        return this.regionName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
    @Override
    public String toString() {
        return "RegionsVO(regionId: " + regionId + ", regionName: " + regionName + ", )";
    }
}