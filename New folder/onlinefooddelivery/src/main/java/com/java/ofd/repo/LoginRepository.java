package com.java.ofd.repo;






import org.springframework.data.jpa.repository.JpaRepository;



import com.java.ofd.entity.Login;

public  interface LoginRepository extends JpaRepository<Login,Integer> {

	
}
