package com.ssg.my_wms2.Service;

import com.ssg.my_wms2.Domain.InboundVO;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.mapper.InboundMapper;
import com.ssg.my_wms2.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
@RequiredArgsConstructor
public class InboundService {
    private final InventoryMapper inventoryMapper;
    private final InboundMapper inboundMapper;
    private final ModelMapper modelMapper;


    public List<InboundDTO> inbound_selectAll_S() { // 전체 리스트를 조회하는 기능

        List<InboundDTO> dtoList = inboundMapper.inbound_selectAll_M().stream()
                .map(vo->modelMapper.map(vo,InboundDTO.class)).collect(Collectors.toList());
// 전체 리스트를 조회를 하는 기능이기 때문에 DB에서 vo를 Dto로 변환하여 사용자에게 보여준다.
        return dtoList;// 리스트 전체를 리턴한다.
    }


    public List<InboundDTO> inbound_SearchByProduct_S(String keyword) {
        return inboundMapper.inbound_SearchByProduct_M(keyword).stream()
                .map(vo -> modelMapper.map(vo, InboundDTO.class))
                .collect(Collectors.toList());
    }
    public void inbound_request_S(InboundDTO dto) {// 회원이 입고 요청

        // 요청일과 상태는 서버에서 강제로 세팅
        dto.setReq_inbound_day(LocalDate.now());
        dto.setStatus("미승인");

        // DTO -> VO 변환
        InboundVO vo = modelMapper.map(dto, InboundVO.class);

        // Mapper 호출
        inboundMapper.inbound_request_M(vo);

        log.info("✅ 입고 요청 등록 완료: {}", vo);
    }
    public void inbound_approve_S(int inbound_id) {// 총관리자가 입고 승인





// 2. 입고 정보 조회
        InboundVO vo = inboundMapper.inbound_selectById_M(inbound_id);

        if (vo == null) {
            throw new IllegalArgumentException("해당 입고 아이디 데이터가 존재X");
        }

        String warehouseId = vo.getWarehouse_id();
        String productId = vo.getProduct_id();
        int quantity = vo.getInbound_quantity();
        String userid = vo.getUserid();
        String status = vo.getStatus();


        // 1. 입고 상태를 "승인"으로 업데이트
        inboundMapper.inbound_approveStatus_M(inbound_id);

// 3. 재고 존재 여부 확인
        int exists = inventoryMapper.inventory_checkExist_M(warehouseId, productId);

// 4. 존재 여부에 따라 insert 또는 update
        if (exists == 0) {
            inventoryMapper.inventory_insert_M(warehouseId, productId, quantity,userid);
        } else {
            inventoryMapper.inventory_addQuantity_M(warehouseId, productId, quantity);
        }


    }

    public List<InboundDTO> inbound_selectByUser_S(String userid) {//회원용 입고 데이터 조회
        return inboundMapper.inbound_selectByUser_M(userid)
                .stream().map(vo -> modelMapper.map(vo, InboundDTO.class))
                .collect(Collectors.toList());
    }

    // InboundServiceImpl.java (구현체)

    public List<InboundDTO> inbound_SearchByWarehouse_S(String warehouseName) {
        return inboundMapper.inbound_SearchByWarehouse(warehouseName);
    }

    public List<InboundDTO> inbound_SearchByDate_S(String startDate, String endDate) {
        return inboundMapper.inbound_SearchByDate(startDate, endDate);
    }



}