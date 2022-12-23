package com.Zijin.pojo;

public enum VoteType {
	SINGLE_ONE_TIME("单选只能选一次"),
	SINGLE_ONE_DAY("多选每天只能选一次"),
	MUILTE_ONE_TIME("多选只能选一次"),
	MUILTE_ONE_DAY("多选每天只能选一次");
	private String tips;
	private VoteType(String tips){
		this.tips = tips;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}

}
