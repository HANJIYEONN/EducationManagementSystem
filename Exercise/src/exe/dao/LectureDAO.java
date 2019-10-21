package exe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import exe.entity.LectureEntity;

public class LectureDAO extends CommonDAO {

	public ArrayList<LectureEntity> searchLecture(String teacherId) {
		ArrayList<LectureEntity> lectureList = new ArrayList<LectureEntity>();
		
		String sql = "	select * "
				+ "		from tbl_lecture "
				+ "		join tbl_subject using (sub_id) "
				+ "		where t_id = ? ";
	
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, teacherId);

			rs = stmt.executeQuery();
			
		while(rs.next()) {
			LectureEntity lecture = new LectureEntity();
			lecture.setLecNum(rs.getInt("lec_num"));
			lecture.setSubjectName(rs.getString("sub_name"));
			
			lectureList.add(lecture);
		}
			
		} catch (Exception e) {
				e.printStackTrace();
				
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		
		return lectureList;
	}

	public boolean addLecture(String subjectId, String teacherId) {
		boolean result = false;

		return result;
	}

	public boolean isDuplication(String subjectId, String teacherId) {
		boolean isDups = false;

		return isDups;
	}

	public boolean cancelLecture(String lecNum) {
		boolean result = false;

		return result;		
	}

}
