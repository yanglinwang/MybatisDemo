package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.Dao.StudentDaoImpl;
import com.bean.Student;
import com.configer.Desutil;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Desutil desutil = null;
		try {
			desutil = new Desutil();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// mybatis-config.xml
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        InputStream inproperties=Resources.getResourceAsStream("database.properties");
        Properties properties = new Properties();
        properties.load(inproperties);
        try {
			properties.setProperty("password",desutil.decrypt(properties.getProperty("password")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is,properties);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
		String sno = request.getParameter("Sno");
		StudentDaoImpl stdi = new StudentDaoImpl(sqlSession);
		Student std = stdi.queryStudentById(sno);
		String sname = std.getSname();
		String scno = std.getScno();
		String sage = std.getSage();
		request.setAttribute("sname",sname);
		request.setAttribute("scno",scno);
		String s = (String)request.getAttribute("scno");
		System.out.println(s);
		request.setAttribute("sage",sage);
		request.getRequestDispatcher("zhanshi.jsp").forward(request, response);
	}

}
/*Connection connection = null;
Statement prepareStatement = null;
ResultSet rs = null;
    // 加载驱动
    try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    // 获取连接
    String url = "jdbc:mysql://127.0.0.1:3306/atest";
    String user = "root";
    String password = "123456";
    try {
		connection = DriverManager.getConnection(url, user, password);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    // 获取statement，preparedStatement
    String sql = "select * from student where sno=";
    try {
    	prepareStatement = (Statement) connection.createStatement();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    // 执行查询
    try {
		rs = prepareStatement.executeQuery(sql+"'"+sno+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

try {
	while(rs.next()) {
	try {
		std.setSname(rs.getString("sname"));
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	String sname = std.getSname();
	try {
		std.setScno(rs.getString("scno"));
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String scno = std.getScno();
	try {
		std.setSage(rs.getString("sage"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String sage = std.getSage();
	}
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
String sname = std.getSname();
String scno = std.getScno();
String sage = std.getSage();
if(rs !=null)
	try {
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
if(prepareStatement !=null)
	try {
		prepareStatement.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
if(connection !=null)
	try {
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
*/