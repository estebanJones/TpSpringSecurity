package fr.stazi.epsi.spring.boot.dto;

public class DtoManageCell {
	private Long userId;
	private Long cellId;
	
	public DtoManageCell() {
		// TODO Auto-generated constructor stub
	}

	public DtoManageCell(Long userId, Long cellId) {
		super();
		this.userId = userId;
		this.cellId = cellId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}
	
	
}
