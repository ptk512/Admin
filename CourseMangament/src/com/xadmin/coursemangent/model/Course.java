package com.xadmin.coursemangent.model;

public class Course {
	protected int courseId;
	protected String course_name;
	protected String course_desc;
	protected String course_fee;
	public Course() {
	}
	
	public Course(String course_name, String course_desc, String course_fee) {
		super();
		this.course_name = course_name;
		this.course_desc = course_desc;
		this.course_fee = course_fee;
	}
	public Course(int courseId, String course_name, String course_desc, String course_fee) {
		super();
		this.courseId = courseId;
		this.course_name = course_name;
		this.course_desc = course_desc;
		this.course_fee = course_fee;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_desc() {
		return course_desc;
	}

	public void setCourse_desc(String course_desc) {
		this.course_desc = course_desc;
	}

	public String getCourse_fee() {
		return course_fee;
	}

	public void setCourse_fee(String course_fee) {
		this.course_fee = course_fee;
	}

	
	
}
