package com.spring.mvc.single.entity.controller;
import java.util.Random; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.javafaker.Faker;
import com.spring.mvc.single.entity.User;
import com.spring.mvc.single.entity.respository.UserRepository;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	// 新增範例資料
	@GetMapping("/test/create_sample_data")
	@ResponseBody
	public String testCreateSampleData() {
		Faker faker = new Faker();
		int count = 150;
		for(int i=0;i<count;i++) {
			Random r = new Random();
			User user = new User();
			user.setName(faker.name().lastName());
			user.setPassword(String.format("%04d", r.nextInt(10000))); // %04d 意思是不足四位補 0
			// 儲存到資料庫
			userRepository.saveAndFlush(user);
			
		}
		return "Create sample data ok !";
	}
}
