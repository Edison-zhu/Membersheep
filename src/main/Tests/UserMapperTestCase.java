import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.membersheep.entity.User;
import cn.membersheep.mappers.UserMapper;

public class UserMapperTestCase {
	
	private AbstractApplicationContext ac;
	private UserMapper userMapper;
	@Test
	public void find(){
		Integer id = 5;
		User user=userMapper.findUserById(id);
		System.out.println(user);
	}
	@Test
	public void updata(){
		User user=new User();
		user.setEmail("384815317@qq.com");
		user.setGender(1);
		user.setPhone("13812337537");
		user.setId(5);
		Integer rows = userMapper.updateInfo(user);
		System.out.println(rows);
	}
	
	@Before
	public void doBefore() {
		ac = new ClassPathXmlApplicationContext(
			"spring-dao.xml");
	
		userMapper = ac.getBean(
			"userMapper", UserMapper.class);
	}
	
	@After
	public void doAfter() {
		ac.close();
	}


	
}





