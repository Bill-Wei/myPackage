/*
 * 编写自定义的拦截器,用于过滤未登录的用户
 */

package inteceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.restrant.entity.Admin;
import com.restrant.entity.Users;
//创建自定义拦截器过程：
//	① 用户自定义的拦截器必须实现Interceptor接口或继承AbstractInterceptor类
//	② 在struts.xml中定义自定义拦截器
//	③ 在struts.xml中的action中使用拦截器

/*AbstractInterceptor类提供了init（）和destroy（）方法的空实现，
 * 我们只需要实现intercept（）方法就可以创建自己的拦截器
 */
public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation innovation) throws Exception {
		// 取得请求的Action名
		String name = innovation.getInvocationContext().getName();
		if (name.equals("validateLogin")) {
			// 如果用户想登录 ，则 使之通过
			return innovation.invoke();
		} else {
			// 取得Session
			ActionContext ac = innovation.getInvocationContext();
			Map session = (Map) ac.get(ServletActionContext.SESSION);
			if (session == null) {
				// 如果session为空，则让用户登录
				return "login";
			} else {
				Users user = (Users) session.get("user");
				if (user == null) {
					Admin admin = (Admin) session.get("admin");
					if (admin == null) {
						// session不为空，但session中没有用户信息，则让用户登录
						return "login";
					} else {
						// 管理员登录，放行
						return innovation.invoke();
					}
				} else {
					// 用户登录 放行
					return innovation.invoke();
				}
			}
		}
	}

}
