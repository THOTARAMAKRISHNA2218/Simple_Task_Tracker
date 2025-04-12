package com.project.model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name="student_Details")
public class Student {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;

	    @Enumerated(EnumType.STRING)
	    private Status status;

	    @CreationTimestamp
	    private Date createdDate;
	    
	    public enum Status {
	        PENDING, DONE
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Student(Long id, String title, Status status, Date createdDate) {
			super();
			this.id = id;
			this.title = title;
			this.status = status;
			this.createdDate = createdDate;
		}

		public Student() {
			super();
		}

		@Override
		public String toString() {
			return "Student [id=" + id + ", title=" + title + ", status=" + status + ", createdDate=" + createdDate
					+ "]";
		}
	    
	
}
