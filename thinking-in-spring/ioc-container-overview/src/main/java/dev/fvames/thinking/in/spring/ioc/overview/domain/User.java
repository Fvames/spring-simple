package dev.fvames.thinking.in.spring.ioc.overview.domain;

import dev.fvames.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/2/25 9:55
 */

public class User implements BeanNameAware {
	private String id;
	private String name;
	private City city;
	private City[] workCities;
	private List<City> lifeCities;
	private Resource configFileLocation;
	private transient String beanName;

	public static User createUser() {
		User user = new User();
		user.setId("1");
		user.setName("static-user");
		return user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public City[] getWorkCities() {
		return workCities;
	}

	public void setWorkCities(City[] workCities) {
		this.workCities = workCities;
	}

	public List<City> getLifeCities() {
		return lifeCities;
	}

	public void setLifeCities(List<City> lifeCities) {
		this.lifeCities = lifeCities;
	}

	public Resource getConfigFileLocation() {
		return configFileLocation;
	}

	public void setConfigFileLocation(Resource configFileLocation) {
		this.configFileLocation = configFileLocation;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", city=" + city +
				", workCities=" + Arrays.toString(workCities) +
				", lifeCities=" + lifeCities +
				", configFileLocation=" + configFileLocation +
				'}';
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@PreDestroy
	public void destory() {
		System.out.println(beanName + "正在被销毁");
	}
}
