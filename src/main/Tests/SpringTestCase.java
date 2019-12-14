import java.util.List;

import cn.membersheep.entity.User;
import cn.membersheep.mappers.UserMapper;
import cn.membersheep.service.UserService;
import cn.membersheep.service.ex.ServiceException;
import cn.membersheep.service.ex.UsernameConflictException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTestCase {

    private AbstractApplicationContext ac;
    private AddressService addressService;
    @Test
    public void insert() {
        try {
            User user = new User();
            user.setUsername("周杰伦");
            user.setPassword("1234");
            User result = userService.reg(user);
            System.out.println(result);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        try {
            String username="朱燕冰";
            String password="12344";
            User user = userService.login(username, password);
            System.out.println(user);

        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void change(){
        try {


            User user = new User();
            user.setId(5);
            user.setPhone("110");
            userService.changeInfo(user);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addnew(){
        Address address = new Address();
        address.set
    }

    @Before
    public void doBefore() {
        ac = new ClassPathXmlApplicationContext(
                "spring-dao.xml","spring-service.xml");

        userService = ac.getBean("addressService",addressService.class);
    }

    @After
    public void doAfter() {
        ac.close();
    }


}





