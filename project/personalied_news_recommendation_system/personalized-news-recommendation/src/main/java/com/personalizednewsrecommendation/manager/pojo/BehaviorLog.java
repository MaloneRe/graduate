package com.personalizednewsrecommendation.manager.pojo;

public class BehaviorLog implements java.io.Serializable{
	
	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = -2153000831510993129L;
	private Long id;
	private Long uid;
	private Long nid;
	/**
	 * 相关行为记录
	 */
	private Integer click;
	private Integer read;
	private Integer collect;
	private Integer comment;
	private Integer forward;
	private Integer other;
	/**
	 * 创建时间
	 */
	private String creation;
	/**
	 * 更改时间
	 */
	private String modified;
	
	
	
	public BehaviorLog(Long uid, Long nid) {
		super();
		this.uid = uid;
		this.nid = nid;
		this.click = 0;
		this.read = 0;
		this.comment = 0;
		this.forward = 0;
		this.collect = 0;
		this.other = 0;
	}
	public BehaviorLog(){
		
		this.click = 0;
		this.read = 0;
		this.comment = 0;
		this.forward = 0;
		this.collect = 0;
		this.other = 0;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getNid() {
		return nid;
	}
	public void setNid(Long nid) {
		this.nid = nid;
	}
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}
	public Integer getCollect() {
		return collect;
	}
	public void setCollect(Integer collect) {
		this.collect = collect;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public Integer getForward() {
		return forward;
	}
	public void setForward(Integer forward) {
		this.forward = forward;
	}
	public Integer getOther() {
		return other;
	}
	public void setOther(Integer other) {
		this.other = other;
	}
	public String getCreation() {
		return creation;
	}
	public void setCreation(String creation) {
		this.creation = creation;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	
	
}
