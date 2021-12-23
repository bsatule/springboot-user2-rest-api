package com.springboot.user2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringRunner;
import com.springboot.user2.entity.User;
import com.springboot.user2.repository.UserRepository;
import com.springboot.user2.service.UserService;
import com.springboot.user2.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class NeoSoftApplicationTests {





}

@SpringBootTest
class SpringbootUser2RestApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
