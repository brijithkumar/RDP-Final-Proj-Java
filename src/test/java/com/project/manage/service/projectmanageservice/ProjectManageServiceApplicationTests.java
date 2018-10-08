package com.project.manage.service.projectmanageservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectManageServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void applicationContextLoaded() {
	}
	
	@Test
	public void applicationContextTest() {
		ProjectManageServiceApplication.main(new String[] {});
	}

}
