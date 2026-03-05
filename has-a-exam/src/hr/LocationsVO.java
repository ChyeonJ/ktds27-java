package hr;

/**
 * @TableName LOCATIONS
 * @TableComment Locations table that contains specific address of a specific office,
warehouse, and/or production site of a company. Does not store addresses /
locations of customers. Contains 23 rows; references with the
departments and countries tables. 
 */
public class LocationsVO {
	
    /**
     * @ColumnName LOCATION_ID
     * @ColumnType NUMBER(4, 0)
     * @ColumnComment Primary key of locations table
     */
    private int locationId;

    /**
     * @ColumnName STREET_ADDRESS
     * @ColumnType VARCHAR2(40)
     * @ColumnComment Street address of an office, warehouse, or production site of a company.
Contains building number and street name
     */
    private String streetAddress;

    /**
     * @ColumnName POSTAL_CODE
     * @ColumnType VARCHAR2(12)
     * @ColumnComment Postal code of the location of an office, warehouse, or production site 
of a company. 
     */
    private String postalCode;

    /**
     * @ColumnName CITY
     * @ColumnType VARCHAR2(30)
     * @ColumnComment A not null column that shows city where an office, warehouse, or 
production site of a company is located. 
     */
    private String city;

    /**
     * @ColumnName STATE_PROVINCE
     * @ColumnType VARCHAR2(25)
     * @ColumnComment State or Province where an office, warehouse, or production site of a 
company is located.
     */
    private String stateProvince;

    /**
     * @ColumnName COUNTRY_ID
     * @ColumnType CHAR(2)
     * @ColumnComment Country where an office, warehouse, or production site of a company is
located. Foreign key to country_id column of the countries table.
     */
    private String countryId;
    
    private CountriesVO countries;
    
    public CountriesVO getCountries() {
		return countries;
	}

	public void setCountries(CountriesVO countries) {
		this.countries = countries;
	}

	public int getLocationId() {
        return this.locationId;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
    public String getStreetAddress() {
        return this.streetAddress;
    }
    
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getStateProvince() {
        return this.stateProvince;
    }
    
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    
    public String getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
    
    @Override
    public String toString() {
        return "LocationsVO(locationId: " + locationId + ", streetAddress: " + streetAddress + ", postalCode: " + postalCode + ", city: " + city + ", stateProvince: " + stateProvince + ", countryId: " + countryId + ", )";
    }
}