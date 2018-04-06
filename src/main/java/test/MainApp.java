package test;

import dao.ClassDao;
import dao.StudentDao;
import entity.Class;
import entity.Student;
import org.apache.ibatis.session.SqlSession;
import sqlsession.SqlSessionFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();//实例化session
        // 获取班级信息 Created by admin.
        ClassDao t_class = session.getMapper(dao.ClassDao.class); // 获取封装的映射信息

        // SelectByArray的数组参数查询方法 Created by YC.
        select_array(t_class);
        // DeleteByArray的数组参数删除方法 Created by YC.
        delete_array(session, t_class);
    }

    /**
     * 动态sql, 使用数组参数做删除
     *
     * @author YC
     * @create 2018/4/6 9:18.
     */
    private static void delete_array(SqlSession session, ClassDao t_class) {
        int[] deleteByArray = {1, 2, 3, 4};
        int deleteByArrayResult = t_class.deleteByArray(deleteByArray);
        session.commit();
        System.out.println(deleteByArrayResult);
    }

    /**
     * 动态sql, 使用数组参数做查询
     *
     * @author YC
     * @create 2018/4/6 9:19.
     */
    private static void select_array(ClassDao t_class) {
        int[] stunos = {1, 2, 3, 4};
        List<Class> selectByArray = t_class.selectByArray(stunos);
        System.out.println(selectByArray.get(0).getClassId());
    }

    /**
     * 多对一的操作
     *
     * @author admin
     * @create 2018/4/4 16:54.
     */
    private static void select_many_to_one(SqlSession session) {
        StudentDao t_student = session.getMapper(StudentDao.class); // 获取封装的映射信息
        List<Student> lst = t_student.findManyToOne();
        for (Student st : lst) {
            System.out.println("名字：" + st.getName() + "\t\t班级：" + st.getClassId() + "\t");
            System.out.println(st.getCl().getClassId());
        }
    }

    /**
     * 一对多的操作
     * 查找同一个班的学生！
     * 注:
     * 1. 在使用一对多的情况下, 在dao方法中对返回值存在过疑惑, 为什么是Class类型的, 而没有任何的Student信息, 通过测试得到Student对象作为Class的属性, 同样具有数据;
     *
     * @author YC
     * @create 2018/4/4 16:44.
     */
    private static void select_one_to_many(ClassDao t_class) {
        List<Class> lc = t_class.findOneToMany();
        for (Class cl : lc) {
            System.out.println("多表查阅同一个班级的学生：\t");
            for (Student s : cl.getStu()) {
                System.out.print(s.getName() + "\t\t");
            }
        }
    }

    /**
     * 查询_方法参数为集合.
     *
     * @author YC
     * @create 2018/4/5 23:29.
     */
    private static void select_map(ClassDao t_class) {
        Map class_map = new HashMap();
        class_map.put("classId", 1);
        class_map.put("className", "一班");
        Class t_classByMap = t_class.findByMap(class_map);
        System.out.println(t_classByMap.getClassId() + "\t" + t_classByMap.getClassName());
    }

    /**
     * 查询_参数类型为String
     * 注意: 当查询出来的结果是多个时, 是会报错的!
     *
     * @author admin
     * @create 2018/4/4 13:37.
     */
    private static void select_string(ClassDao t_class) {
        System.out.println(t_class.findString("一班").getClassId());
    }

    /**
     * 更新
     *
     * @author YC
     * @create 2018/4/5 23:30.
     */
    private static void update(SqlSession session, ClassDao t_class) {
        Class class_upd = new Class();
        class_upd.setClassId(1);
        class_upd.setClassName("修改二班");
        int update = t_class.update(class_upd);
        System.out.println("修改结果: " + update);
        session.commit();
        System.out.println("---------------------------------------");
    }

    /**
     * 删除
     *
     * @author YC
     * @create 2018/4/5 23:31.
     */
    private static void delete(ClassDao t_class) {
        System.out.println("##删除数据:");
        Class class_del = new Class();
        class_del.setClassId(12);
        int delete = t_class.delete(class_del);
        System.out.println("删除结果: " + delete);
        System.out.println("---------------------------------------");
    }

    /**
     * 添加
     *
     * @author YC
     * @create 2018/4/6 9:22.
     */
    private static void insert(SqlSession session, ClassDao t_class) {
        System.out.println("##插入数据:");

        Class class_ins = new Class();
        class_ins.setClassName("三班");
        int insert = t_class.insert(class_ins);
        System.out.println("插入值结果: " + insert);
        // 提交插入数据 Created by admin.
        session.commit();
        System.out.println("--------------------------------------");
    }

    /**
     * 查询_根据属性
     *
     * @author YC
     * @create 2018/4/6 9:22.
     */
    private static void findBy(ClassDao t_class) {
        System.out.println("##根据条件查询信息:");
        Class class_sel_by = new Class();
        class_sel_by.setClassId(10);
        Class t_classBy = t_class.findBy(class_sel_by);
        System.out.println(t_classBy.getClassId() + "\t" + t_classBy.getClassName());
        System.out.println("--------------------------------------");
    }

    /**
     * 查询
     *
     * @author YC
     * @create 2018/4/6 9:22.
     */
    private static void select(ClassDao t_class) {
        List<Class> t_classAll = t_class.findAll();
        System.out.println("##查询表中班级信息:");
        for (Class classInfo : t_classAll) {
            System.out.println(classInfo.getClassId() + "\t" + classInfo.getClassName());
        }
        System.out.println("-------------------------------------");
    }
}
