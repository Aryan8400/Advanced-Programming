package util;

import java.io.File;

/**
 * @author prithivi
 */
public class StringUtils {

	// Start Region: Database Configuration
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/college_app";
	public static final String DB_USER_NAME = "root";
	public static final String DB_USER_PASSWORD = "";

	public static final String ADMIN = "admin";
	public static final String USER = "user";

	public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR;
	// End Region: Database Configuration

	// Start SQL Queries
	public static final String REGISTER_STUDENT = "INSERT INTO student_info "
			+ "(user_name, first_name, last_name, dob, gender, email, phone_number, subject, password, image) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_LOGIN_STUDENT_INFO = "SELECT * FROM student_info WHERE user_name = ?";
	public static final String GET_ALL_STUDENTS_INFO = "SELECT * FROM student_info";
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM student_info WHERE user_name = ?";
	public static final String GET_PHONE = "SELECT COUNT(*) FROM student_info WHERE phone_number = ?";
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM student_info WHERE email = ?";
	public static final String DELETE_USER = "DELETE FROM student_info WHERE user_name = ?";

	// End SQL Queries

	// Start Parameter names
	public static final String USER_NAME = "username";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "birthday";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SUBJECT = "subject";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	// End Parameter names

	// Start string messages
	// Start register page messages
	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered!";
	public static final String ERROR_REGISTER_MESSAGE = "Please correct the form data.";
	public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
	public static final String USERNAME_ERROR_MESSAGE = "Username is already registered.";
	public static final String EMAIL_ERROR_MESSAGE = "Email is already registered.";
	public static final String PHONE_NUMBER_ERROR_MESSAGE = "Phone number is already registered.";
	public static final String PASSWORD_UNMATCHED_ERROR_MESSAGE = "Password is not matched.";

	// End register page messages

	// Start login page message
	public static final String SUCCESS_LOGIN_MESSAGE = "Successfully LoggedIn!";
	public static final String ERROR_LOGIN_MESSAGE = "Either username or password is not correct!";
	// End login page message

	public static final String SUCCESS_DELETE_MESSAGE = "Successfully Deleted!";
	public static final String ERROR_DELETE_MESSAGE = "Cannot delete the user!";
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String ERROR_MESSAGE = "errorMessage";
	// End string messages

	// Start JSP Route
	public static final String LOGIN_PAGE = "/pages/login.jsp";
	public static final String REGISTER_PAGE = "/pages/register.jsp";
	public static final String WELCOME_PAGE = "/pages/welcome.jsp";
	public static final String HOME_PAGE = "/pages/home.jsp";
	// End JSP Route

	// Start Servlet Route
	public static final String REGISTER_SERVLET = "/RegisterServlet";
	// End Servlet Route
}
