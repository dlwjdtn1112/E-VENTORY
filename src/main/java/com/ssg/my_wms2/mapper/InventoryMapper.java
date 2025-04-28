package com.ssg.my_wms2.mapper;

import com.ssg.my_wms2.Domain.InventoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryMapper {

    List<InventoryVO> inventory_selectAll_M();//전체 재고 목록

    List<InventoryVO> inventory_SearchByWarehouse_M(); // 창고별 재고 목록

    List<InventoryVO> inventory_SearchByProduct_M(); //상품별 재고 목록

    List<InventoryVO> inventory_SearchByDayToDay_M(); // 특정기간 재고 목록


//    int inventory_checkExist_M(String warehouse_id, String product_id);

    Integer inventory_checkExist_M(
            @Param("warehouse_id") String warehouseId,
            @Param("product_id") String productId
    );

    Integer inventory_quantityCheck_M(
            @Param("warehouse_id") String warehouseId,
            @Param("product_id") String productId
    );


//    void inventory_insert_M(String warehouse_id, String product_id, int quantity);
//
//    void inventory_addQuantity_M(String warehouse_id, String product_id, int quantity);

    // 수량 삽입 (재고 없을 때)
    void inventory_insert_M(
            @Param("warehouse_id") String warehouseId,
            @Param("product_id") String productId,
            @Param("quantity") int quantity,
            @Param("userid") String userid
    );

    // 수량 증가 (재고 있을 때)
    void inventory_addQuantity_M(
            @Param("warehouse_id") String warehouseId,
            @Param("product_id") String productId,
            @Param("quantity") int quantity
    );

    void inventory_subtractQuantity_M(
            @Param("warehouse_id") String warehouseId,
            @Param("product_id") String productId,
            @Param("quantity") int quantity
    );

    List<InventoryVO> inventory_selectByUser_M(String userid);


}
