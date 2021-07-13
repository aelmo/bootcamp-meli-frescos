package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.request.GetDashBoardDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.DashboardDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.MostSoldProduct;
import com.mercadolibre.bootcampmelifrescos.model.Buyer;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;
import com.mercadolibre.bootcampmelifrescos.service.impl.DashboardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
public class DashboardServiceTests {

    @Mock
    private DashboardServiceImpl dashboardService;
    private final GetDashBoardDTO dashBoardDTORequisition = new GetDashBoardDTO(LocalDate.of(2021, 06, 01), LocalDate.now());
    private final MostSoldProduct mostSoldProduct = new MostSoldProduct(1L, "Banana", 10);
    private final DashboardDTO dashboardDTOResponse = new DashboardDTO(LocalDate.of(2021, 06, 01), LocalDate.now(), mostSoldProduct, 0);

    @BeforeEach
    void init() throws Exception {
        when(dashboardService.getDashboardByPeriod(notNull())).thenReturn(dashboardDTOResponse);
    }

    @Test
    void shouldReturnADasboardBetweenATime() throws Exception {
        DashboardDTO expectedResult = dashboardDTOResponse;
        DashboardDTO result = dashboardService.getDashboardByPeriod(dashBoardDTORequisition);

        assertThat(result).isSameAs(expectedResult);
    }
}
