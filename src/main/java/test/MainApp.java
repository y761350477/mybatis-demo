package test;

import dao.ClassDao;
import dao.StudentDao;
import entity.Class;
import entity.Student;
import org.apache.ibatis.session.SqlSession;
import sqlsession.SqlSessionFactoryUtil;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();//实例化session
        // 获取班级信息 Created by admin.
        ClassDao t_class = session.getMapper(dao.ClassDao.class); // 获取封装的映射信息

        List<Class> t_classAll = t_class.findAll();
        System.out.println("##查询表中班级信息:");
        for (Class classInfo : t_classAll) {
            System.out.println(classInfo.getClassId() + "\t" + classInfo.getClassName());
        }
        System.out.println("-------------------------------------");

        System.out.println("##根据条件查询信息:");
        Class class_sel_by = new Class();
        class_sel_by.setClassId(10);
        Class t_classBy = t_class.findBy(class_sel_by);
        System.out.println(t_classBy.getClassId() + "\t" + t_classBy.getClassName());
        System.out.println("--------------------------------------");

        System.out.println("##插入数据:");

        Class class_ins = new Class();
        class_ins.setClassName("三班");
        int insert = t_class.insert(class_ins);
        System.out.println("插入值结果: " + insert);
        // 提交插入数据 Created by admin.
        session.commit();
        System.out.println("--------------------------------------");

        System.out.println("##删除数据:");
        Class class_del = new Class();
        class_del.setClassId(12);
        int delete = t_class.delete(class_del);
        System.out.println("删除结果: " + delete);
        System.out.println("---------------------------------------");

        System.out.println("##修改数据:");
        Class class_upd = new Class();
        class_upd.setClassId(1);
        class_upd.setClassName("修改二班");
        int update = t_class.update(class_upd);
        System.out.println("修改结果: " + update);
        session.commit();
        System.out.println("---------------------------------------");

        /**
         * 映射里面设置参数类型为String的效果!
         * 注意: 当查询出来的结果是多个时, 是会报错的!
         *
         * @author admin
         * @create 2018/4/4 13:37.
         */
        System.out.println("##根据Sting类型查询:");
        System.out.println(t_class.findString("一班").getClassId());

        /**
         * 一对多的操作
         * 查找同一个班的学生！
         * 注:
         *  1. 在使用一对多的情况下, 在dao方法中对返回值存在过疑惑, 为什么是Class类型的, 而没有任何的Student信息, 通过测试得到Student对象作为Class的属性, 同样具有数据;
         *
         * @author admin
         * @create 2018/4/4 16:44.
         */
        List<Class> lc = t_class.findOneToMany();
        for (Class cl : lc) {
            System.out.println("多表查阅同一个班级的学生：\t");
            for (Student s : cl.getStu()) {
                System.out.print(s.getName() + "\t\t");
            }
        }

        // 学生表多对一的操作 Created by admin.
        StudentDao t_student = session.getMapper(dao.StudentDao.class); // 获取封装的映射信息

        /**
         * 多对一的操作
         *
         * @author admin
         * @create 2018/4/4 16:54.
         */
        List<Student> lst = t_student.findManyToOne();
        for (Student st : lst) {
            System.out.println("名字：" + st.getName() + "\t\t班级：" + st.getClassId() + "\t");
            System.out.println(st.getCl().getClassId());
        }

    }
}
