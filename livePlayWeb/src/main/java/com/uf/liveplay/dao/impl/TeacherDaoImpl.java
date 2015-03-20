package com.uf.liveplay.dao.impl;

import org.springframework.stereotype.Component;

import com.uf.liveplay.dao.TeacherDao;
import com.uf.liveplay.entity.Teacher;
@Component("teacherDao")
public class TeacherDaoImpl extends CommonDaoImpl<Teacher>  implements TeacherDao{

}
