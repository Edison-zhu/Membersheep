package cn.membersheep.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HTML���ʹ�����
 * @author tarena
 *
 */
public class HtmlAccessFilter implements Filter{
	
	/**
	 * ������
	 */
	private List<String> whiteList = new ArrayList<String>();
	
	public void init(FilterConfig arg0) throws ServletException {
		whiteList.add("index.html");
		whiteList.add("register.html");
		whiteList.add("login.html");
		whiteList.add("topTemplate.html");
		whiteList.add("leftTemplate.html");
		whiteList.add("footerTemplate.html");
	}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String uri = request.getRequestURI();
		String fileName = uri.substring(uri.lastIndexOf("/")+1);
		System.out.println("当前请求的页面"+fileName);

		//如果无需登录直接放行
		if(whiteList.contains(fileName)){
			// ����,����ִ�к����Ĺ���
			arg2.doFilter(request, response);
			return;
		}
		HttpSession session = request.getSession();
		//�ж��Ƿ���session
		if(session.getAttribute("uid") != null){
			arg2.doFilter(request, response);
			return;
		}
		//�ض��򵽵�¼ҳ��
		response.sendRedirect("login.html");
	}

	public void destroy() {
		
	}


	
	
}
