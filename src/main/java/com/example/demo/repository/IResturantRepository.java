package com.example.demo.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Resturant;


@Repository
public interface IResturantRepository extends JpaRepository<Resturant, UUID> {
	/**
	 * findByResturantName
	 * @param resturantName
	 * @return list of resturants
	 */
	@Query("SELECT r FROM Resturant r WHERE resturantName = ?1")
	Resturant findResturantName(String resturantName);
	/**
	 * findRecentDate
	 * @return resturant
	 */
	@Query("select r from Resturant r where createdTime =(select max(createdTime) from Resturant)")
	Resturant findRecentDate();
	
	/**
	 * find database empty or not
	 * @return
	 */
	@Query("select count(r) from Resturant r")
	int findempty();
	
	@Modifying
	@Query(value="delete from resturant i where i.restaurant_id=?1",nativeQuery = true)
	void deleteResturantOnId(String resturantId);
	
	@Query("select f from Resturant f  where resturantId=?1")
	Resturant findById(String resturantId);
	
	@Modifying
	@Query(value = "delete from address where address_id=?1", nativeQuery = true)
	void deleteAddressOfResturantId(UUID addressId);
	
	@Query("select f from Resturant f  where resturantName=?1")
	Resturant getResturantBasedOnresturantName(String resturantName);
	

}
