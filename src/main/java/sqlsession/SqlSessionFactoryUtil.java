package sqlsession;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	private static SqlSessionFactory ssf;
	static{
		Reader reader=null;
			try {
				reader=Resources.getResourceAsReader("mybatis-config.xml");
				if(reader!=null){
					ssf=new SqlSessionFactoryBuilder().build(reader);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public static SqlSession getSqlSession(){
		return ssf.openSession();
	}
}
