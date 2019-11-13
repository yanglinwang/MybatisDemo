package com.Dao;

import org.apache.ibatis.session.SqlSession;

import com.bean.Student;

public class StudentDaoImpl implements StudentDao {
	public SqlSession sqlSession;
	public StudentDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
	@Override
	public Student queryStudentById(String sno) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("StuMapper.selectStu",sno);
	}

}
