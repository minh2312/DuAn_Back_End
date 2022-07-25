package ml.duAn.api.model;

public class Pagination {
	private int limit;
	private int page;
	private int offset;
	public Pagination() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pagination(int limit, int page, int offset) {
		super();
		this.limit = limit;
		this.page = page;
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	public int offSet() {
		return this.offset = (this.page * this.limit) - this.limit;
	}
	
	
}
