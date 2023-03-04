package com.sergioruy.sergiofoodapi.infrastructure.service.report;

import com.sergioruy.sergiofoodapi.domain.model.filter.DailySaleFilter;
import com.sergioruy.sergiofoodapi.domain.service.SaleQueryService;
import com.sergioruy.sergiofoodapi.domain.service.SaleReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfSaleReportService implements SaleReportService {

    @Autowired
    private SaleQueryService queryService;

    @Override
    public byte[] issueDailySales(DailySaleFilter filter, String timeOffset) {
        try {

        var inputStream = this.getClass().getResourceAsStream("/reports/daily-sales.jasper");

        var parameters = new HashMap<String, Object>();
//        parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));
        parameters.put("REPORT_LOCALE", new Locale("en", "US"));

        var dailySales = queryService.consultDiarySales(filter, timeOffset);

        var dataSource = new JRBeanCollectionDataSource(dailySales);

        var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Was not possible issue this report.", e);
        }
    }
}
