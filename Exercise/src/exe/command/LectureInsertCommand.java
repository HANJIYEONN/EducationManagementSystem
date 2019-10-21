package exe.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import exe.common.ActionForward;
import exe.common.Command;
import exe.dao.LectureDAO;
import exe.entity.LectureEntity;
import exe.entity.TeacherEntity;

public class LectureInsertCommand implements Command {

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
			
			LectureDAO dao = new LectureDAO();
			LectureEntity result = dao.addLecture(subjectId, teacherId) 

			action.setPath("WEB-INF/subjectDetail.jsp");
			action.setSend(false);
		}
			
		return action;
	}

}
	