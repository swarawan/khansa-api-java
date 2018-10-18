package com.swarawan.khansapos.repository;

import com.swarawan.khansapos.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    Goods findBySecureId(String secureId);

}
