package com.xs.dormTest.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport{
	private Integer totalNum;
	private Integer pageSize;
	private Integer pageIndex;
	private String submitUrl;
	
	//遇到结束标签后执行
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	//遇到开始标签后执行
	@Override
	public int doStartTag() throws JspException {
		JspWriter writer = pageContext.getOut();
		
		try {
			StringBuffer page = new StringBuffer();
			Integer totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1; 
			if (totalNum > pageSize) {
				//只有当查询出的数据量大于每一页展示的数据量时，才进行分页
				if(pageIndex == 1) {
					//当前页面是首页
					page.append("<a href='#'>首页</a>&nbsp;");
					page.append("<a href='#'>上一页</a>&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex+1)+"'>下一页</a>&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+totalPage+"'>尾页</a>");
				}else if(pageIndex == totalPage) {
					//当前页面是尾页
					page.append("<a href='"+submitUrl+"&pageIndex="+1+"'>首页</a>&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex-1)+"'>上一页</a>&nbsp;");
					page.append("<a href='#'>下一页</a>&nbsp;");
					page.append("<a href='#'>尾页</a>");
				}else {
					//当前页面是中间
					page.append("<a href='"+submitUrl+"&pageIndex="+1+"'>首页</a>&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex-1)+"'>上一页</a>&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex+1)+"'>下一页</a>&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+totalPage+"'>尾页</a>");
				}
				page.append("<br> 当前第"+pageIndex +"页&nbsp;/共"+totalPage+"页&nbsp;/共"+totalNum+"条数据");
			}
			System.out.println(totalNum);
			System.out.println(pageSize);
			System.out.println(totalPage);
			writer.print(page.toString());
			writer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}


	

}
