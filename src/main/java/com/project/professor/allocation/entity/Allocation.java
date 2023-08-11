package com.project.professor.allocation.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
public class Allocation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty(access=Access.READ_ONLY)
	@Schema(accessMode=AccessMode.READ_ONLY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private DayOfWeek day;
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mmZ")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Schema(example="19:00-0300",type="string")
	private Date start;
	@JsonFormat(pattern = "HH:mmZ")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Temporal(TemporalType.TIME)
	@Schema(example="22:00-0300",type="string")
	private Date end;
	
	/*Foreign Keys*/
	@Column(nullable=false,name="FK_course_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	@Schema(accessMode=AccessMode.WRITE_ONLY)
	private Long courseId;
	@ManyToOne(optional=false)
	@JoinColumn(name="FK_course_id",nullable=false,insertable=false,updatable=false)
	@JsonProperty(access=Access.READ_ONLY)
	@Schema(accessMode=AccessMode.READ_ONLY,allOf=Course.class)
	private Course course;
	
	@Column(nullable=false,name="FK_professor_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	@Schema(accessMode=AccessMode.WRITE_ONLY)
	private Long professorId;
	@ManyToOne(optional=false)
	@JoinColumn(name="FK_professor_id",nullable=false,insertable=false,updatable=false)
	@JsonProperty(access=Access.READ_ONLY)
	@Schema(accessMode=AccessMode.READ_ONLY,allOf=Professor.class)
	private Professor professor;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the day
	 */
	public DayOfWeek getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(DayOfWeek day) {
		this.day = day;
	}
	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
	
	/**
	 * @return the courseId
	 */
	public Long getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the professorId
	 */
	public Long getProfessorId() {
		return professorId;
	}
	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	/**
	 * @return the professor
	 */
	public Professor getProfessor() {
		return professor;
	}
	/**
	 * @param professor the professor to set
	 */
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", start=" + start + ", end=" + end + ", courseId=" + courseId
				+ ", course=" + course + ", professorId=" + professorId + ", professor=" + professor + "]";
	}

}
