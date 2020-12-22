package com.epidemiologicSurvey.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("questionRecord")
public class QuestionRecord {
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
	
	@Column("inputText")
	@Comment("输入框数据")
	private String inputText;

	@Column("recordId")
	@Comment("记录表id")
	private int recordId;

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

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

}
