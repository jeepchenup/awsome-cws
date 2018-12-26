package info.chen.awsome_cws.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import info.chen.awsome_cws.entity.composite_id.TitleID;

@Entity
@Table(name="title")
public class Title implements Serializable{

	@EmbeddedId
	@AttributeOverrides(value = { @AttributeOverride(column = @Column(name="emp_no"), name = "empNo"),
			                      @AttributeOverride(column = @Column(name="from_date"), name="fromDate")})
	private TitleID titleID;
	
	@Column(name="to_date")
	@Type(type="date")
	private Date toDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("emp_no")
	@JoinColumn(referencedColumnName="emp_no", name="emp_no")
	private Employee employee;

	public TitleID getTitleID() {
		return titleID;
	}

	public void setTitleID(TitleID titleID) {
		this.titleID = titleID;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Title [titleID=" + titleID + ", toDate=" + toDate + "]";
	}
	
}