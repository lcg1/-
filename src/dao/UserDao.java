package dao;



import java.util.List;



import org.hibernate.SQLQuery;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import entity.SysUser;



/**

 * dao层

 * @author lxw

 *

 */

public class UserDao {

	private SessionFactory sessionFactory;

	

	public SessionFactory getSessionFactory() {

		return sessionFactory;

	}



	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;

	}

	

	//用户登陆
	@Transactional(readOnly=true)
	public SysUser login(String name,String password) {

		System.out.println("用户登录Dao");

		String sql = "select * from sys_user where userName=? and password=?";

		SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(sql);

		sqlQuery.setParameter(0, name);

		sqlQuery.setParameter(1, password);

		sqlQuery.addEntity(SysUser.class);

		return (SysUser) sqlQuery.list().get(0);

	}		

	

	//用户列表

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<SysUser> getUserList() {

		System.out.println("用户列表Dao");

		String sql = "select * from sys_user";

		SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(sql);

		sqlQuery.addEntity(SysUser.class);

		return sqlQuery.list();

	}

	

	//保存用户
	@Transactional
	public void saveUser(SysUser sysUser) {

		System.out.println("保存用户Dao");

		

		this.sessionFactory.getCurrentSession().save(sysUser);

		System.out.println("保存成功");

	}

	

	//更新用户
	@Transactional
	public void updateUser(SysUser sysUser) {

		System.out.println("更新用户Dao");

		

		this.sessionFactory.getCurrentSession().update(sysUser);

		System.out.println("更新用户成功");

	}

	

	//根据用户Id获取用户信息
	@Transactional(readOnly=true)
	public SysUser selectById(Integer id) {

		System.out.println("查询用户Dao");

		

		return (SysUser) this.sessionFactory.getCurrentSession().get(SysUser.class,id);

	}

	



	//删除用户
	@Transactional(readOnly=true)
	public void deleteUser(SysUser sysUser) {

		System.out.println("删除用户Dao");

		

		this.sessionFactory.getCurrentSession().delete(sysUser);

		System.out.println("删除用户成功");

	}

}