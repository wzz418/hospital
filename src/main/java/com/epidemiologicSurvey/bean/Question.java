package com.epidemiologicSurvey.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("question")
public class Question {

	@Id
	private int id;

	@Column("createTime")
	@Comment("创建时间")
	private Date createTime;

	@Column("question")
	@Comment("问题")
	private String question;

	@Column("answer")
	@Comment("答案")
	private String answer;
	
	@Column("sequence")
	@Comment("序号")
	private int sequence;

	
	@Column("inputType")
	@Comment("0-输入框可为空，1-输入框不可为空")
	private int inputType;

	@Column("fsfqy")
	@Comment("0-未启用，1-已启用")
	private int fsfqy;
	
	@Column("fsfsc")
	@Comment("0-未删，1-已删")
	private int fsfsc;
	
	@Column("trueAnswer")
	@Comment("答案")
	private String trueAnswer;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getInputType() {
		return inputType;
	}

	public void setInputType(int inputType) {
		this.inputType = inputType;
	}

	public int getFsfsc() {
		return fsfsc;
	}

	public void setFsfsc(int fsfsc) {
		this.fsfsc = fsfsc;
	}

	public int getFsfqy() {
		return fsfqy;
	}

	public void setFsfqy(int fsfqy) {
		this.fsfqy = fsfqy;
	}

	public String getTrueAnswer() {
		return "否";
	}

	public void setTrueAnswer(String trueAnswer) {
		this.trueAnswer = trueAnswer;
	}
	
	
}
