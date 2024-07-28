package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PasswordEncryptionWithAes;
import model.StudentModel;
import util.StringUtils;

/**
 * @author prithivi
 */
public class DatabaseController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/college_app";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}

	public int registerStudent(StudentModel studentModel) {
		try (Connection con = getConnection()) {
			// Check for existing username
			PreparedStatement checkUsernameSt = con.prepareStatement(StringUtils.GET_USERNAME);
			checkUsernameSt.setString(1, studentModel.getUsername());
			ResultSet checkUsernameRs = checkUsernameSt.executeQuery();
			checkUsernameRs.next();
			if (checkUsernameRs.getInt(1) > 0) {
				return -2; // Username already exists
			}

			// Check for existing email
			PreparedStatement checkEmailSt = con.prepareStatement(StringUtils.GET_EMAIL);
			checkEmailSt.setString(1, studentModel.getEmail());
			ResultSet checkEmailRs = checkEmailSt.executeQuery();
			checkEmailRs.next();
			if (checkEmailRs.getInt(1) > 0) {
				return -3; // Email already exists
			}

			// Check for existing phone number
			PreparedStatement checkPhoneSt = con.prepareStatement(StringUtils.GET_PHONE);
			checkPhoneSt.setString(1, studentModel.getPhoneNumber());
			ResultSet checkPhoneRs = checkPhoneSt.executeQuery();
			checkPhoneRs.next();
			if (checkPhoneRs.getInt(1) > 0) {
				return -4; // Phone number already exists
			}

			PreparedStatement st = con.prepareStatement(StringUtils.REGISTER_STUDENT);
			st.setString(1, studentModel.getUsername());
			st.setString(2, studentModel.getFirstName());
			st.setString(3, studentModel.getLastName());
			st.setDate(4, Date.valueOf(studentModel.getDob()));
			st.setString(5, studentModel.getGender());
			st.setString(6, studentModel.getEmail());
			st.setString(7, studentModel.getPhoneNumber());
			st.setString(8, studentModel.getSubject());
			st.setString(9, PasswordEncryptionWithAes.encrypt(
					studentModel.getUsername(), studentModel.getPassword()));
			st.setString(10, studentModel.getImageUrlFromPart());

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	public int getStudentLoginInfo(String username, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_STUDENT_INFO);
			st.setString(1, username);
			ResultSet result = st.executeQuery();

			if (result.next()) {
				// User name and password match in the database
				String userDb = result.getString("user_name");
				String passwordDb = result.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);
				
				if (decryptedPwd != null && userDb.equals(username) && decryptedPwd.equals(password))
					return 1;
				else
					return 0;
			}else {
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	public ArrayList<StudentModel> getAllStudentsInfo() {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_STUDENTS_INFO);
			ResultSet rs = st.executeQuery();

			ArrayList<StudentModel> students = new ArrayList<>();

			while (rs.next()) {
				StudentModel student = new StudentModel();

				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setDob(rs.getDate("dob").toLocalDate());
				student.setGender(rs.getString("gender"));
				student.setEmail(rs.getString("email"));
				student.setPhoneNumber(rs.getString("phone_number"));
				student.setSubject(rs.getString("subject"));
				student.setUsername(rs.getString("user_name"));

				students.add(student);
			}

			return students;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return null;
		}
	}
	
	public int deleteStudentInfo(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_USER);
			st.setString(1, username);
			return st.executeUpdate();			
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
}
