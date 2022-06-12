package com.spring.mvc.single.entity.controller;
import java.util.Arrays;
import java.util.List; 
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	// 查詢範例資料 1 
	@GetMapping("/test/findAll")
	@ResponseBody
	public List<User> testFindAll(){
		List<User> users = userRepository.findAll();
		return users; 
	}
	// 查詢範例資料 2
	@GetMapping("/test/findall_sort")
	@ResponseBody
	public List<User> testFindallSort(){
		// ASC 小->大 
		Sort sortByASC = new Sort(Sort.Direction.ASC, "name");
		// DESC 大->小
		// Sort sortByDESC = new Sort(Sort.Direction.DESC, "name");
		List<User> users = userRepository.findAll(sortByASC);
		return users;
	}
	// 查詢範例資料 3
	@GetMapping("/test/findall_ids")
	@ResponseBody
	public List<User> testFindallIds() { 
		Iterable<Long> ids = Arrays.asList(1L, 3L, 5L);
		List<User> users = userRepository.findAll(ids);
		return users;
	}	
	// 查詢範例資料 4
	@GetMapping("/test/findall_example")
	@ResponseBody
	public List<User> testFindallExample() {
		User user = new User();
		user.setId(1L);
		user.setPassword("0459");
		Example<User> example = Example.of(user);
		List<User> users = userRepository.findAll(example);
		return users;
	}
	// 查詢範例資料 5
	@GetMapping("/test/findall_example2")
	@ResponseBody
	public List<User> testFindallExample2() {
		User user = new User();
		user.setName("a");
		// 欄位 name 的內容是否有包含 "a"
		// 建立 ExampleMatcher 比對器
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains());
		Example<User> example = Example.of(user, matcher);
		List<User> users = userRepository.findAll(example);
		return users;
	}
	// 查詢範例資料 6
	@GetMapping("/test/find_one")
	@ResponseBody
	public User findOne() {		
		return userRepository.findOne(3L);
	}
	// 查詢分頁
	@GetMapping("/test/page/{no}")
	@ResponseBody
	public List<User> testPage(@PathVariable("no") Integer no) {
		int pageNo = no; 
		int pageSize = 10;
		// 分頁請求
		PageRequest pageRequest = new PageRequest(pageNo, pageSize);
		Page<User> page = userRepository.findAll(pageRequest);
		return page.getContent();
	}
}
