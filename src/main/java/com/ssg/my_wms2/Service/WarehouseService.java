package com.ssg.my_wms2.Service;

import com.ssg.my_wms2.dto.WarehouseDTO;
import com.ssg.my_wms2.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseMapper warehouseMapper;

    public List<WarehouseDTO> getAllWarehouse() {
        return warehouseMapper.selectAllWarehouse();
    }

}
