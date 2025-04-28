package com.ssg.my_wms2.Service;

import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.InventoryDTO;
import com.ssg.my_wms2.dto.OutboundDTO;
import com.ssg.my_wms2.mapper.InventoryMapper;
import com.ssg.my_wms2.mapper.OutboundMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
@RequiredArgsConstructor
public class InventoryService {
    //private final InventoryMapper inventroyMapper;
    private final ModelMapper modelMapper;

    private final InventoryMapper inventoryMapper; // ✅ 오타 수정



    public List<InventoryDTO> inventory_selectAll_S() {
        List<InventoryDTO> dtoList = inventoryMapper.inventory_selectAll_M().stream() // ✅ 여기도 수정
                .map(vo -> modelMapper.map(vo, InventoryDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public List<InventoryDTO> inventory_selectByUser_S(String userid) {
        return inventoryMapper.inventory_selectByUser_M(userid)
                .stream().map(vo -> modelMapper.map(vo, InventoryDTO.class))
                .collect(Collectors.toList());
    }


}
