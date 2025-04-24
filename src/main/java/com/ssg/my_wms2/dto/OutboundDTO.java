package com.ssg.my_wms2.dto;

import lombok.*;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutboundDTO {


    private int outbound_id;
    private String product_id;
    private int outbound_quantity;
    @Future
    private LocalDate req_outbound_day;
    private String warehouse_id;
    private String status;
    private String userid;
}
