package com.example.ChicApiREST;

import com.example.ChicApiREST.dao.AuthDAOImpl;
import com.example.ChicApiREST.entities.Country;
import com.example.ChicApiREST.entities.Address;
import com.example.ChicApiREST.entities.Province;
import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.ProviderRepository;
import com.example.ChicApiREST.repositories.UserRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;


import org.hibernate.query.Query;


@SpringBootTest
class ChicApiRestApplicationTests {

	@Test
	public void testAuthenticateUser() {
		// Mock UserRepository y Session
		UserRepository userRepository = mock(UserRepository.class);
		ProviderRepository providerRepository = mock(ProviderRepository.class);
		Session session = mock(Session.class);

		User expectedUser = new User("usuario1",
				"123456",
				"usuario1@email.com",
				"60123456",
				"jose",
				new Address(null, "calle1", "sestao", Province.Bizkaia, "48910", Country.España));

		// Crea un mock de Query<User>
		Query<User> query = mock(Query.class);

		// Simula el comportamiento de existsById()
		when(userRepository.existsById("usuario1")).thenReturn(true);

		// Simula el comportamiento de createQuery()
		when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);

		// Simula el comportamiento de setParameter() y uniqueResult()

		when(query.setParameter("user", "usuario1")).thenReturn(query);
		when(query.setParameter("password", "123456")).thenReturn(query);
		when(query.uniqueResult()).thenReturn(expectedUser);

		// Crea una instancia de la clase que contiene el método authenticateUser
		AuthDAOImpl userAuthentication = new AuthDAOImpl(session, userRepository, providerRepository);

		// Ejecuta el método authenticateUser
		User authenticatedUser = userAuthentication.authenticateUser("usuario1", "contraseña");

		// Verifica que el usuario autenticado sea el esperado
		assertEquals(expectedUser, authenticatedUser);
	}

}
