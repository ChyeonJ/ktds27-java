package com.ktdsuniversity.edu.tmdb.vo;

/**
 * @TableName PRODUCER
 * @TableComment null
 */
public class ProducerVO {

    /**
     * @ColumnName PRODUCER_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment PD-YYYYMMDD일련번호(6자리)
     */
    private String producerId;

    /**
     * @ColumnName PRODUCER_NAME
     * @ColumnType VARCHAR2(25)
     * @ColumnComment null
     */
    private String producerName;

    public String getProducerId() {
        return this.producerId;
    }
    
    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }
    
    public String getProducerName() {
        return this.producerName;
    }
    
    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

	@Override
	public String toString() {
		return "ProducerVO [producerId=" + producerId + ", producerName=" + producerName + "]";
	}
    

}