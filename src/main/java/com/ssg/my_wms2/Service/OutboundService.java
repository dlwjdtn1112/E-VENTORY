package com.ssg.my_wms2.Service;

import com.ssg.my_wms2.Domain.InboundVO;
import com.ssg.my_wms2.Domain.OutboundVO;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.OutboundDTO;
import com.ssg.my_wms2.mapper.InboundMapper;
import com.ssg.my_wms2.mapper.InventoryMapper;
import com.ssg.my_wms2.mapper.OutboundMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
@RequiredArgsConstructor
public class OutboundService {

    private final OutboundMapper outboundMapper;
    private final InventoryMapper inventoryMapper;
    private final ModelMapper modelMapper;


    public List<OutboundDTO> outbound_selectAll_S() { // 전체 리스트를 조회하는 기능

        List<OutboundDTO> dtoList = outboundMapper.outbound_selectAll_M().stream()
                .map(vo->modelMapper.map(vo, OutboundDTO.class)).collect(Collectors.toList());
// 전체 리스트를 조회를 하는 기능이기 때문에 DB에서 vo를 Dto로 변환하여 사용자에게 보여준다.
        return dtoList;// 리스트 전체를 리턴한다.
    }

    public void outbound_request_S(OutboundDTO dto) {

        // 요청일과 상태는 서버에서 강제로 세팅
        dto.setReq_outbound_day(LocalDate.now());
        dto.setStatus("미승인");
        String warehouseId = dto.getWarehouse_id();
        String productId = dto.getProduct_id();
        int requestQuantity = dto.getOutbound_quantity();
        Integer checkInventory = inventoryMapper.inventory_checkExist_M(warehouseId, productId);

        Integer currentQuantity = inventoryMapper.inventory_quantityCheck_M(warehouseId, productId);
        if (currentQuantity == null || currentQuantity < requestQuantity || checkInventory == null ) {
            throw new IllegalArgumentException("요청 수량이 재고 수량을 초과합니다. 아니면 입력하신 제품이 창고에 없습니다.");
        }


        // 여기까지 왔다는 건 요청 가능하다는 뜻
        dto.setReq_outbound_day(LocalDate.now());
        dto.setStatus("미승인");


        // DTO -> VO 변환
        OutboundVO vo = modelMapper.map(dto, OutboundVO.class);

        // Mapper 호출
        outboundMapper.outbound_request_M(vo);

        log.info("✅ 출고 요청 등록 완료: {}", vo);
    }

    public List<OutboundDTO> outbound_selectByUser_S(String userid) {
        return outboundMapper.outbound_selectByUser_M(userid)
                .stream().map(vo -> modelMapper.map(vo, OutboundDTO.class))
                .collect(Collectors.toList());
    }



    public void outbound_approve_S(int outbound_id) {// 총관리자가 입고 승인
        // 1. 출고 상태를 "승인"으로 업데이트
        //outboundMapper.outbound_approveStatus_M(outbound_id);


// 2. 출고 정보 조회
        OutboundVO vo = outboundMapper.outbound_selectById_M(outbound_id); // 메서드명 수정됨

        if (vo == null) {
            throw new IllegalArgumentException("해당 출고 아이디 데이터가 존재 X");
        }

        String warehouseId = vo.getWarehouse_id();
        String productId = vo.getProduct_id();
        int quantity = vo.getOutbound_quantity();
        String userid = vo.getUserid();
        String status = vo.getStatus();

        log.info(warehouseId);
        log.info(productId);
        log.info(quantity);
        log.info(userid);
        log.info(status);




// 3. 재고 존재 여부 확인
        int q = inventoryMapper.inventory_quantityCheck_M(warehouseId, productId);


        if (q < quantity) {
            log.info("선택하신 제품의 출고 갯수를 초과했습니다. 다시 입력해주세여");
        } else {
            outboundMapper.outbound_approveStatus_M(outbound_id);
            inventoryMapper.inventory_subtractQuantity_M(warehouseId, productId, quantity);
        }

        // inventoryMapper.inventory_subtractQuantity_M(warehouseId,productId,quantity);


    }

    public List<OutboundDTO> outbound_SearchByWarehouse_S(String warehouseName) {
        return outboundMapper.outbound_SearchByWarehouse(warehouseName);
    }

    public List<OutboundDTO> outbound_SearchByDate_S(String startDate, String endDate) {
        return outboundMapper.outbound_SearchByDate(startDate, endDate);
    }


}
