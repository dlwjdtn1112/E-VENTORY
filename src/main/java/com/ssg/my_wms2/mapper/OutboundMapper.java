package com.ssg.my_wms2.mapper;

import com.ssg.my_wms2.Domain.InboundVO;
import com.ssg.my_wms2.Domain.OutboundVO;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.OutboundDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutboundMapper {

    List<OutboundVO> outbound_selectAll_M();//출고 전체 조회

    void outbound_request_M(OutboundVO vo);//출고 요청

    void outbound_approve_M();//총 관리자가 출고 요청 승인

    void outbound_reject_M(); // 총관리자가 출고 요청 거부

    void outbound_delete_M(); // 회원이 출고 요청을 하다가 다시 취소

    List<InboundVO> outbound_SearchByProduct_M(String keyword); //회원이 특정 상품을 조회를 하면 그 상품만 조회한다.

    List<InboundVO> outbound_SearchByWarehouse_M(String keyword); // 회원이 특정 창고를 조회 하면 그 창고에 있는 전체 상품 조회를 한다.

    List<InboundVO> outbound_SearchByDayToDay_M(String keyword1, String keyword2);

    List<OutboundVO> outbound_selectByUser_M(String userid);

    void outbound_approveStatus_M(int outbound_id);

    OutboundVO outbound_selectById_M(int outbound_id);
    List<OutboundDTO> outbound_SearchByWarehouse(String warehouseName);

    List<OutboundDTO> outbound_SearchByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);


}
