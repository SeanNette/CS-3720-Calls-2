/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.io.IOException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * ReportGenerator class for the creation of report documents
 *
 * @author Evan
 */
public class ReportGenerator
{
    // member data

    /**
     * Constructor
     */
    public ReportGenerator()
    {
    }

    public static void generateReportFromXML() throws JRException, IOException
    {
        // step 1: load the .jrxml file created with iReport
        JasperReport report1 = JasperCompileManager.compileReport("report1.jrxml");
        // step 2: use JasperFillManager to combine data and the .jasper file
        JasperPrint jPrint = JasperFillManager.fillReport(report1, new HashMap(), new JREmptyDataSource());
        // step 3: use JRExportManager to export the now populated .jasper file to a .pdf
        JasperExportManager.exportReportToPdf(jPrint);
    }
}
