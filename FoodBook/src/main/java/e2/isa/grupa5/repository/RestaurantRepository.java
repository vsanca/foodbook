package e2.isa.grupa5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findById(Long id);
    List<Restaurant> findByName(String name);
}
