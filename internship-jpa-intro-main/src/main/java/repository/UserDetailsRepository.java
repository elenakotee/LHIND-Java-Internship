package repository;

import model.entity.UserDetails;

import java.util.List;

public interface UserDetailsRepository extends Repository<UserDetails, Integer >{
	List<UserDetails> findByFirstName(String first_name);
	List<UserDetails> findByLastName(String last_name);
	List<UserDetails> findByEmail(String email);
	List<UserDetails> findByPhoneNumber(String phone_number);


}
