package com.ssg.my_wms2.Service;

import com.ssg.my_wms2.Domain.InboundVO;
import com.ssg.my_wms2.Domain.OutboundVO;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.OutboundDTO;
import com.ssg.my_wms2.mapper.InboundMapper;
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

        // DTO -> VO 변환
        OutboundVO vo = modelMapper.map(dto, OutboundVO.class);

        // Mapper 호출
        //outboundMapper.outbound_selectByUser_M(vo);

        log.info("✅ 입고 요청 등록 완료: {}", vo);
    }

    public List<OutboundDTO> outbound_selectByUser_S(String userid) {
        return outboundMapper.outbound_selectByUser_M(userid)
                .stream().map(vo -> modelMapper.map(vo, OutboundDTO.class))
                .collect(Collectors.toList());
    }





}
