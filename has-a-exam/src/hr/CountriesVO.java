package hr;

/**
 * @TableName COUNTRIES
 * @TableComment country table. Contains 25 rows. References with locations table.
 */
public class CountriesVO {

    /**
     * @ColumnName COUNTRY_ID
     * @ColumnType CHAR(2)
     * @ColumnComment Primary key of countries table.
     */
    private String countryId;

    /**
     * @ColumnName COUNTRY_NAME
     * @ColumnType VARCHAR2(40)
     * @ColumnComment Country name
     */
    private String countryName;

    /**
     * @ColumnName REGION_ID
     * @ColumnType NUMBER(, )
     * @ColumnComment Region ID for the country. Foreign key to region_id column in the departments table.
     */
    private int regionId;
    
    private RegionsVO regions;
    
    public RegionsVO getRegions() {
		return regions;
	}
    
	public void setRegions(RegionsVO regions) {
		this.regions = regions;
	}

    public String getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
    
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    public int getRegionId() {
        return this.regionId;
    }
    
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    
    @Override
    public String toString() {
        return "CountriesVO(countryId: " + countryId + ", countryName: " + countryName + ", regionId: " + regionId + ", )";
    }
}