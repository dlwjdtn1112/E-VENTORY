package com.ssg.my_wms2.Controller;

import com.ssg.my_wms2.Service.InboundService;
import com.ssg.my_wms2.Service.OutboundService;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.OutboundDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class OutboundController {
    private final OutboundService outboundService;


    @GetMapping("/outbound/admin")
    public String outbound_selectAll_C(Model model) {// 총관리자가 전체 회원 출고 데이터를 본다
        log.info("list1.........................check");
        List<OutboundDTO> dtoList = outboundService.outbound_selectAll_S(); //리스트로 해야 한다.
        log.info(dtoList.toString());
        model.addAttribute("dtoList2", dtoList);
        return "/todo/outbound";
    }

    @PostMapping("/outbound/request")
    public String outbound_request_C( //회원이 출고 요청
            @RequestParam("product_id") String productId,
            @RequestParam("outbound_quantity") int outboundQuantity,
            @RequestParam("warehouse_id") String warehouseId, HttpSession session) {
        String loginId = (String) session.getAttribute("login_id");

        OutboundDTO dto = OutboundDTO.builder()
                .product_id(productId)
                .outbound_quantity(outboundQuantity)
                .warehouse_id(warehouseId)
                .req_outbound_day(LocalDate.now()) // 오늘 날짜 자동 입력
                .status("미승인") // 기본 상태
                .userid(loginId)
                .build();

        outboundService.outbound_request_S(dto);  // ⬅️ 서비스 호출

        return "redirect:/todo/outbound"; // 리스트 페이지로 이동
    }

    @GetMapping("/outbound/requestForm")
    public String outbound_request_form_C() { //출고요청 홈페이지 이동
        return "/todo/outbound_request_form"; // /WEB-INF/views/todo/inbound_request_form.jsp
    }

    @GetMapping("/outbound")
    public String outbound_listByUser(HttpSession session,Model model) {//회원용 출고 데이터 목록
        String loginId = (String) session.getAttribute("login_id");
        log.info("loginId:" + loginId);
        List<OutboundDTO> list = outboundService.outbound_selectByUser_S(loginId);
        model.addAttribute("dtoList2", list);
        return "todo/outbound";
    }
}
