package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/8/15.
 */
public class FileBean {
	int fileID = 0;
	String fileCode = "";
	CaseClassBean fileCaseClass = null;
	ClientBean clientID = null;
	InstructionBean instructionCode = null;
	String notes = "";

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public CaseClassBean getFileCaseClass() {
		return fileCaseClass;
	}

	public void setFileCaseClass(CaseClassBean fileCaseClass) {
		this.fileCaseClass = fileCaseClass;
	}

	public ClientBean getClientID() {
		return clientID;
	}

	public void setClientID(ClientBean clientID) {
		this.clientID = clientID;
	}

	public InstructionBean getInstructionCode() {
		return instructionCode;
	}

	public void setInstructionCode(InstructionBean instructionCode) {
		this.instructionCode = instructionCode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
