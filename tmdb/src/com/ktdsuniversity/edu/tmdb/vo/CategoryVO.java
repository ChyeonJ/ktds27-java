package com.ktdsuniversity.edu.tmdb.vo;

/**
 * @TableName CATEGORY
 * @TableComment null
 */
public class CategoryVO {

    /**
     * @ColumnName CATEGORY_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment CT-YYYYMMDD일련번호(6자리)
     */
    private String categoryId;

    /**
     * @ColumnName CATEGORY_NAME
     * @ColumnType VARCHAR2(10)
     * @ColumnComment null
     */
    private String categoryName;

    public String getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return this.categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

	@Override
	public String toString() {
		return "CategoryVO [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
    
    
}