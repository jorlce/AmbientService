package ambient.repository;

import org.springframework.data.repository.CrudRepository;

import ambient.model.LoginCredential;

	
public interface LoginRepository extends CrudRepository<LoginCredential, Long> {
	LoginCredential findByLogin(String user);
}

