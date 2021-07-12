package com.mercadolibre.bootcampmelifrescos.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private MostSoldProduct mostSoldProduct;
    private Integer QuantitySales;
}
