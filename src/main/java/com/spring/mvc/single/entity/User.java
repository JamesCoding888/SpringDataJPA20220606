package com.spring.mvc.single.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "User") // default User 會使用 MySQL 預設較高權限的資料表，裡面會加載很多資料欄位，建議不用 User
//@Table(name = "User2")  // 建議修改成其它 Table 名稱
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 50, name = "name")
	private String name;
	
	@Column
	private String password;
	
	@Temporal(TemporalType.DATE) // 存入資料庫的格式(DATE = 日期格式, TIME = 時間格式, TIMESTAMP = 時間日期格式
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd") // HTML form 表單呈現格式
	@JsonFormat(pattern = "yyyy/MM/dd") // Json 字串呈現格式
	private Date brith;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBrith() {
		return brith;
	}

	public void setBrith(Date brith) {
		this.brith = brith;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", brith=" + brith + "]";
	}
	
	
}
