package ac.kaist.cts.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`attached_item` (
//		  `id` INT NOT NULL AUTO_INCREMENT ,
//		  `filename` VARCHAR(100) NULL ,
//		  `filesize` VARCHAR(45) NULL ,
//		  `filetype` VARCHAR(45) NULL ,
//		  `modifieddate` VARCHAR(45) NULL ,
//		  `filepath` VARCHAR(400) NULL ,
//		  `state` VARCHAR(45) NULL ,
//		  `report_item_id` INT NOT NULL ,
//		  PRIMARY KEY (`id`) ,
//		  INDEX `fk_attached_item_report_item1_idx` (`report_item_id` ASC) ,
//		  CONSTRAINT `fk_attached_item_report_item1`
//		    FOREIGN KEY (`report_item_id` )
//		    REFERENCES `Report_DB`.`report_item` (`id` )
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB;

@JsonIgnoreProperties({"bytes"})
public class AttachedItem {

	public AttachedItem() {
		// TODO Auto-generated constructor stub
	}

	private int id = 0;
	private String filename = "";
	private String filesize = "";
	private String filetype = "";
	private String modifieddate = "";
	private String filepath = "";
	private String state = "";
	private int report_item_id = 0;
	
	private byte[] bytes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getReport_item_id() {
		return report_item_id;
	}

	public void setReport_item_id(int report_item_id) {
		this.report_item_id = report_item_id;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"filename\":\"" + filename
				+ "\", \"filesize\":\"" + filesize + "\", \"filetype\":\""
				+ filetype + "\", \"modifieddate\":\"" + modifieddate
				+ "\", \"filepath\":\"" + filepath + "\", \"state\":\"" + state
				+ "\", \"report_item_id\":\"" + report_item_id + "\"}";
	}
}
