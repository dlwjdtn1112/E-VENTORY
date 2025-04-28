
package com.ssg.my_wms2.mapper;


import com.ssg.my_wms2.Domain.InboundVO;
import com.ssg.my_wms2.dto.InboundDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface InboundMapper {
//    List<InboundVO> inbound_selectAll_M();//입고 전체 조회
//
//    void inbound_request_M(InboundVO vo);//입고 요청
//
//    void inbound_approveStatus_M(int inbound_id);//총 관리자가 입고 요청 승인을 하면 상태를 승인 수락
//
//    void inventory_addQuantity_M(int inbound_id); // 총 관리자가 입고 승인을 하면 재고 테이블 수량 업데이트
//
//    void inventory_insert_M(int inbound_id); // 총 관리자가 입고 승인을 하고 나서 갯수가 0이면 그 데이터를 삽입을 한다
//
//    void inbound_reject_M(int inbound_id); // 총관리자가 입고 요청 거부
//
//    void inbound_delete_M(int inbound_id); // 회원이 입고 요청을 하다가 다시 취소
//
//    List<InboundVO> inbound_SearchByProduct_M(String keyword); //회원이 특정 상품을 조회를 하면 그 상품만 조회한다.
//
//    List<InboundVO> inbound_SearchByWarehouse_M(String keyword); // 회원이 특정 창고를 조회 하면 그 창고에 있는 전체 상품 조회를 한다.
//
//    List<InboundVO> inbound_SearchByDayToDay_M(String keyword1, String keyword2); // 회원이 특정 기간을 정하면 그 기간안에 있는 목록을 조회한다.
//
//    int inventory_checkExist_M(String warehouse_id, String product_id);// 재고목록에 0인지 양수인지 판단
//
//    void inventory_insert_M(String warehouse_id, String product_id, int quantity); // 0이면 삽입
//
//    void inventory_addQuantity_M(String warehouse_id, String product_id, int quantity);// 양수이면 업데이트
    // 수정본

    // 기본 조회 및 요청 관련
    List<InboundVO> inbound_selectAll_M();               // 입고 전체 조회
    void inbound_request_M(InboundVO vo);                // 입고 요청

    // 상태 변경 및 승인/거절 처리
    void inbound_approveStatus_M(int inbound_id);        // 관리자 승인
    void inbound_reject_M(int inbound_id);               // 관리자 거부
    void inbound_delete_M(int inbound_id);               // 회원이 요청 취소

    // 조회 조건 검색
    List<InboundVO> inbound_SearchByProduct_M(String keyword);  // 상품명 검색
    List<InboundVO> inbound_SearchByWarehouse_M(String keyword); // 창고 검색
    List<InboundVO> inbound_SearchByDayToDay_M(String keyword1, String keyword2); // 기간 검색

    // 입고 상세 조회용
    InboundVO inbound_selectById_M(int inbound_id);  // 입고ID로 단일 조회 (승인 시 필요)

    List<InboundVO> inbound_selectByUser_M(String userid);

    List<InboundDTO> inbound_SearchByWarehouse(String warehouseName);

    List<InboundDTO> inbound_SearchByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);








}
