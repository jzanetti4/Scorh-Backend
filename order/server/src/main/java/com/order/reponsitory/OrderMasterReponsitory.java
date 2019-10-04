package com.order.reponsitory;

import com.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterReponsitory extends JpaRepository<OrderMaster, String> {

}
