package exe.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import exe.common.ActionForward;
import exe.common.Command;
import exe.dao.TeacherDAO;
import exe.entity.TeacherEntity;

public class TeacherUpdateCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request)
			throws IOException, ServletException {


		HttpSession session = request.getSession();
		Object teacher = session.getAttribute("teacher");
		
		
		ActionForward action = new ActionForward();
		
		if(teacher == null) {
			action.setPath("loginForm.do");
			action.setSend(true);
			
		} else {

			try {
				request.setCharacterEncoding("UTF-8");
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}

			TeacherEntity newTeacher = new TeacherEntity(); 
			newTeacher.setId(request.getParameter("id"));
			newTeacher.setPw(request.getParameter("pw"));
			newTeacher.setName(request.getParameter("name"));
			newTeacher.setDeptCode(request.getParameter("code"));
			newTeacher.setAddr(request.getParameter("addr"));
			
			TeacherDAO dao = new TeacherDAO();
			boolean result = dao.updateTeacher(newTeacher);
			
			if (result) {
				request.setAttribute("code", "20");
				
				session.setAttribute("teacher", newTeacher);
				
				action.setPath("WEB-INF/result.jsp");
				action.setSend(false);
				
			} else {
				request.setAttribute("code", "21");
				
				action.setPath("WEB-INF/result.jsp");
				action.setSend(false);
			}

		}

		return action;
	}
		
}
