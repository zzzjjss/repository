package com.uf.liveplay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.liveplay.dao.PublicMessageDao;
import com.uf.liveplay.dao.ServicerDao;
import com.uf.liveplay.dao.TeacherDao;
import com.uf.liveplay.dao.UserDao;
import com.uf.liveplay.dao.VoteDao;
import com.uf.liveplay.entity.PublicMessage;
import com.uf.liveplay.entity.Servicer;
import com.uf.liveplay.entity.Teacher;
import com.uf.liveplay.entity.User;
import com.uf.liveplay.entity.Vote;
import com.uf.liveplay.service.UserService;
import com.uf.liveplay.unit.Constant;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private VoteDao voteDao;
	
	@Autowired
	private ServicerDao servicerDao;
	
	@Autowired
	private PublicMessageDao publicMessageDao;
	
	@Autowired
	private TeacherDao teacherDao;
	
	
	
	public ServicerDao getServicerDao() {
		return servicerDao;
	}

	public void setServicerDao(ServicerDao servicerDao) {
		this.servicerDao = servicerDao;
	}

	public PublicMessageDao getPublicMessageDao() {
		return publicMessageDao;
	}

	public void setPublicMessageDao(PublicMessageDao publicMessageDao) {
		this.publicMessageDao = publicMessageDao;
	}

	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public VoteDao getVoteDao() {
		return voteDao;
	}
	public void setVoteDao(VoteDao voteDao) {
		this.voteDao = voteDao;
	}
	public void registNewUser(User user)throws Exception{
		userDao.insert(user);
	}
	public void vote(String voteItem){
		List<Vote> vote=voteDao.findByHql("select v from Vote v ");
		Vote voteRecord=null;
		if(vote==null||vote.size()==0){
			Vote voteNew=new Vote();
			voteNew.setDownCount(0l);
			voteNew.setUpCount(0l);
			voteNew.setEqualCount(0l);
			voteDao.insert(voteNew);
			voteRecord=voteNew;
		}else{
			voteRecord=vote.get(0);
		}
		
		if(Constant.VOTE_UP.equalsIgnoreCase(voteItem)){
			voteRecord.setUpCount(voteRecord.getUpCount()+1);
		}else if(Constant.VOTE_DOWN.equalsIgnoreCase(voteItem)){
			voteRecord.setDownCount(voteRecord.getDownCount()+1);
		}else if(Constant.VOTE_EQUAL.equalsIgnoreCase(voteItem)){
			voteRecord.setEqualCount(voteRecord.getEqualCount()+1);
		}
		voteDao.update(voteRecord);
	}
	
	public Map<String, String> statisticVote(){
		List<Vote> vote=voteDao.findByHql("select v from Vote v ");
		Map<String, String> statistic=new HashMap<String, String>();
		if(vote==null||vote.size()==0){
			statistic.put(Constant.VOTE_UP, "0%");
			statistic.put(Constant.VOTE_DOWN, "0%");
			statistic.put(Constant.VOTE_EQUAL, "0%");
		}else{
			Vote voteRecord=vote.get(0);
			long sum=voteRecord.getUpCount()+voteRecord.getDownCount()+voteRecord.getEqualCount();
			double up=(voteRecord.getUpCount()/(double)sum)*100;
			double down=(voteRecord.getDownCount()/(double)sum)*100;
			double equal=(voteRecord.getEqualCount()/(double)sum)*100;
			statistic.put(Constant.VOTE_UP, String.format("%.2f%%", up));
			statistic.put(Constant.VOTE_DOWN, String.format("%.2f%%", down));
			statistic.put(Constant.VOTE_EQUAL, String.format("%.2f%%", equal));
		}
		return statistic;
	}
	
	public boolean login(String userName,String password){
		List<User> users=userDao.findByHql("select u from User u where u.name=?", userName);
		if(users!=null&&users.size()>0&&password!=null&&password.equals(users.get(0).getPassword())){
			return true;
		}else{
			return false;
		}
	}
	public User findUserByName(String userName){
		List<User> users=userDao.findByHql("select u from User u where u.name=?", userName);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}
	public User findUserById(Integer userId){
		return userDao.findById(User.class, userId);
	}
	public void saveUserInfo(User user){
		userDao.saveOrUpdate(user);
	}
	public List<User> findAllCommonUser(){
		return userDao.findByHql("select u from User u where u.role=? order by u.createTime asc","commonUser");
	}
	
	public boolean servicerLogin(String userName,String password){
		List<Servicer> users=servicerDao.findByHql("select u from Servicer u where u.name=?", userName);
		if(users!=null&&users.size()>0&&password!=null&&password.equals(users.get(0).getPassword())){
			return true;
		}else{
			return false;
		}
	}
	public void saveServicerInfo(Servicer servicer){
		servicerDao.saveOrUpdate(servicer);
	}
	public Servicer findServicerByName(String userName){
		List<Servicer> users=servicerDao.findByHql("select u from Servicer u where u.name=?", userName);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}
	public Servicer findServicerById(Integer userId){
		return servicerDao.findById(Servicer.class, userId);
	}
	
	
	
	public boolean teacherLogin(String userName,String password){
		List<Teacher> users=teacherDao.findByHql("select u from Teacher u where u.userName=?", userName);
		if(users!=null&&users.size()>0&&password!=null&&password.equals(users.get(0).getPassword())){
			return true;
		}else{
			return false;
		}
	}
	public void saveTeacherInfo(Teacher teacher){
		teacherDao.saveOrUpdate(teacher);
	}
	public Teacher findTeacherByName(String userName){
		List<Teacher> users=teacherDao.findByHql("select u from Teacher u where u.userName=?", userName);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}
	public Teacher findTeacherById(Integer userId){
		return teacherDao.findById(Teacher.class, userId);
	}
	public void savePublicMessage(PublicMessage message){
		publicMessageDao.saveOrUpdate(message);
	}
	public PublicMessage findMessageByKey(String key){
		List<PublicMessage> messages=publicMessageDao.findByHql("select mess from PublicMessage mess  where mess.messageType=?", key);
		if(messages!=null&&messages.size()>0){
			return messages.get(0);
		}else{
			return null;
		}
	}
}
