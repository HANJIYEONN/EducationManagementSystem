package exe.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import exe.common.ActionForward;
import exe.common.Command;
import exe.dao.SubjectDAO;
import exe.entity.SubjectEntity;
import exe.entity.TeacherEntity;

public class SubjectListCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		TeacherEntity teacher = (TeacherEntity)session.getAttribute("teacher");
		
		
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
			
			SubjectDAO dao = new SubjectDAO();
			ArrayList<SubjectEntity> result = dao.searchSubjectByDepartment(teacher.getDeptCode());
			request.setAttribute("subjectList", result);
		
			action.setPath("WEB-INF/subjectList.jsp");
			action.setSend(false);
		}
		
		
		return action;
	}

}
