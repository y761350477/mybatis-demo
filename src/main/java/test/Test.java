package test;

import java.util.List;

import dao.ClassDao;
import dao.StudentDao;
import dao.UserDao;
import entity.Class;
import entity.Student;
import entity.User;
import org.apache.ibatis.session.SqlSession;

import sqlsession.SqlSessionFactoryUtil;

public class Test {
	public static void main(String[] args) {
		SqlSession session=SqlSessionFactoryUtil.getSqlSession();//实例化session
//		ClassDao c=session.getMapper(dao.ClassDao.class);//获取封装的映射信息
//		StudentDao sd=session.getMapper(dao.StudentDao.class);
		UserDao userDao = session.getMapper(UserDao.class);
		System.out.println("Demo_User：");

		User user = new User();
		user.setUserName("YangChen");
		userDao.insert(user);
		session.commit();

		List<User> list=userDao.findAll();
		for (User i:list
			 ) {
			System.out.println(i.getUserId()+"\t"+i.getUserName()+"\t"+i.getUserSex()+"\t"+i.getUserHeight());
		}

		//在查找Name值时，总是报错，找了个把小时，想了很久还是没有找到，在无意间，发现了构造的初始化String是null，int是0，
		//因为sql语句中判断id是null，而默认是0，数据库不存在同时id为0，name为yy的字段，所以会报错空指针！
		//从这个问题说明了，思考要基于基础，不能脱离基础，更不能急躁，应该认真分析，认真对待，仔细观察！
		/*
		 * 查询的操作
		 */
//		List<Class> cc=c.findAll();
//		System.out.println(cc.get(0).getClassName());
//		System.out.println(cc.get(1).getClassName());
		/*
		 * 遍历查询结果
		 */
//		for(Class a:cc){
//			System.out.println(a.getClassId()+"\t"+a.getClassName()+"\t"+a.getSubJect());
//		}

		/*
		 * 插入的操作
		 * 注意：重复插入是会报错的！
		 * 在进行修改、删除、插入时，需要提交SqlSession,不然数据不会在数据库中生效！
		 */
//		Class c1=new Class();
//		c1.setClassId(444);
//		c1.setClassName("yyy");
//		c1.setSubJect("java1");
//		System.out.println(c.insert(c1));
//		session.commit();

		/*
		 * 删除的操作
		 */
//		System.out.println(c.delete(2017));
//		session.commit();

		/*
		 * 修改的操作
		 */
//		Class c2=new Class();
//		c2.setClassId(2);
//		c2.setClassName("woehduxmmi");
//		System.out.println(c.update(c2));
//		session.commit();

		/*
		 * 条件查询
		 */
//		Class c3=new Class();
//		c3.setClassId(111);
//		c3.setClassName("yyy");
//		Class ca=c.findBy(c3);
//		System.out.println(ca.getClassName());

		/*
		 * 映射里面设置参数类型为String的效果！
		 * 注意：当查询出来的结果是多个时，是会报错的！
		 */
//		System.out.println(c.findString("nihs").getClassId());

		/*
		 * 一对多的操作
		 * 查找同一个班的学生！
		 */
//		List<Class> lc=c.findAll1();
//		for(Class cl:lc){
//			System.out.println("多表查阅同一个班级的学生：\t");
//			for(Student s:cl.getStu()){
//				 System.out.print(s.getName()+"\t\t");
//			}
//		}

		/*
		 * Student查询
		 */
//		List<Student> ls=sd.findAll();
//		System.out.println(ls.get(0).getName());

		/*
		 * 多对一的操作
		 */
//		List<Student> lst=sd.findAll1();
//		for(Student st:lst){
//			System.out.println("名字："+st.getName()+"\t\t班级："+st.getClassId()+"\t");
//			System.out.println(st.getCl().getClassId());
//		}

	}
}
