package com.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.entity.Customer;

@Repository // Register this DAO to Spring.
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory; // Dependency Injection defined in spring-mvc-crud-demo-servlet.xml

	@Override
	public List<Customer> getCustomers() {

		Session mySession = sessionFactory.getCurrentSession();
		Query<Customer> myQuery = mySession.createQuery("FROM Customer ORDER BY lastName", Customer.class);
		List<Customer> customers = myQuery.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {

		Session mySession = sessionFactory.getCurrentSession();

		// [KEY]: Use saveOrUpdate()!!!
		mySession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int customerId) {

		Session mySession = sessionFactory.getCurrentSession();
		Customer customer = mySession.get(Customer.class, customerId);

		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {

		Session mySession = sessionFactory.getCurrentSession();

		Query myQuery = mySession.createQuery("DELETE FROM Customer WHERE id = :givenId");
		myQuery.setParameter("givenId", customerId);
		myQuery.executeUpdate();

		// Customer customer = getCustomer(customerId);
		// mySession.delete(customer);
	}

	@Override
	public List<Customer> searchCustomers(String criteria) {

		Session mySession = sessionFactory.getCurrentSession();

		Query<Customer> myQuery = mySession.createQuery(
				"FROM Customer WHERE lower(firstName) like :givenCriteria OR lower(lastName) like :givenCriteria OR lower(email) like :givenCriteria",
				Customer.class);
		myQuery.setParameter("givenCriteria", "%" + criteria.toLowerCase() + "%");

		List<Customer> customers = myQuery.getResultList();

		return customers;
	}

}
