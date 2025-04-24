package com.ssg.my_wms2.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundDTO {
    private int inbound_id;
    private String product_id;
    @Min(1)
    @Max(1000)
    private int inbound_quantity;
    @Future
    private LocalDate req_inbound_day;
    private String warehouse_id;
    private String status;
    private String userid;




}
