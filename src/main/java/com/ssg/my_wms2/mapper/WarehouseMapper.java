package com.ssg.my_wms2.mapper;

import com.ssg.my_wms2.dto.WarehouseDTO;

import java.util.List;

public interface WarehouseMapper {

    List<WarehouseDTO> selectAllWarehouse();
}
