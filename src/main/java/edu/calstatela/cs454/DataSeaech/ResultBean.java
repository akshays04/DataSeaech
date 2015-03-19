package edu.calstatela.cs454.DataSeaech;

public class ResultBean {
	
		String id;
    	double titleRank;
    	String desc;
		double termFreq;
		int count;
		String url;
		double pageRank;
		
		public ResultBean() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public double getTitleRank() {
			return titleRank;
		}

		public void setTitleRank(double titleRank) {
			this.titleRank = titleRank;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public double getTermFreq() {
			return termFreq;
		}

		public void setTermFreq(double termFreq) {
			this.termFreq = termFreq;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public double getPageRank() {
			return pageRank;
		}

		public void setPageRank(double pageRank) {
			this.pageRank = pageRank;
		}
}
