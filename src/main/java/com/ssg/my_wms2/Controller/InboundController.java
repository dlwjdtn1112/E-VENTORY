package com.ssg.my_wms2.Controller;
import com.ssg.my_wms2.Domain.InboundVO;
import com.ssg.my_wms2.Service.InboundService;
import com.ssg.my_wms2.dto.InboundDTO;
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
public class InboundController {
    private final InboundService inboundService;

    @GetMapping("/inbound")
    public String listByUser(HttpSession session,Model model) {
        String loginId = (String) session.getAttribute("login_id");
        log.info("loginId:" + loginId);
        List<InboundDTO> list = inboundService.inbound_selectByUser_S(loginId);
        model.addAttribute("dtoList1", list);
        return "todo/inbound";
    }

    @GetMapping("/inbound/admin")
    public String inbound_selectAll_C(Model model) {
        log.info("list1.........................check");
        List<InboundDTO> dtoList = inboundService.inbound_selectAll_S(); //리스트로 해야 한다.
        log.info(dtoList.toString());
        model.addAttribute("dtoList1", dtoList);
        return "/todo/inbound";
    }

    @GetMapping("/inbound/search")
    public String inbound_SearchByProduct_S(@RequestParam("keyword") String keyword, Model model) {
        log.info("입고 목록 검색 조회 - 상품명: {}", keyword);

        // ✅ 여기가 핵심! 빈 문자열 방지
        if (keyword == null || keyword.trim().isEmpty()) {
            return "redirect:/todo/inbound";
        }

        List<InboundDTO> dtoList = inboundService.inbound_SearchByProduct_S(keyword);
        model.addAttribute("dtoList1", dtoList);
        model.addAttribute("keyword", keyword);
        return "/todo/inbound";
    }

    @PostMapping("/inbound/request")
    public String inbound_request_C(
            @RequestParam("product_id") String productId,
            @RequestParam("inbound_quantity") int inboundQuantity,
            @RequestParam("warehouse_id") String warehouseId,HttpSession session) {
        String loginId = (String) session.getAttribute("login_id");

        InboundDTO dto = InboundDTO.builder()
                .product_id(productId)
                .inbound_quantity(inboundQuantity)
                .warehouse_id(warehouseId)
                .req_inbound_day(LocalDate.now()) // 오늘 날짜 자동 입력
                .status("미승인") // 기본 상태
                .userid(loginId)
                .build();

        inboundService.inbound_request_S(dto);  // ⬅️ 서비스 호출

        return "redirect:/todo/inbound"; // 리스트 페이지로 이동
    }
    @PostMapping("/inbound/update")
    public String inbound_approve_C(@RequestParam("inbound_id") int inboundId){
        inboundService.inbound_approve_S(inboundId);

        return "redirect:/todo/inbound";
    }

    @GetMapping("/inbound/requestForm")
    public String inbound_request_form_C() {
        return "/todo/inbound_request_form"; // /WEB-INF/views/todo/inbound_request_form.jsp
    }

    // 입고 승인 입력폼 페이지로 이동
    @GetMapping("/inbound/approveForm")
    public String inbound_approve_form_C() {
        return "/todo/inbound_approve_form"; // /WEB-INF/views/todo/inbound_approve_form.jsp
    }


}
