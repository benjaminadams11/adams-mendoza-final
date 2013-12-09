/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JTextArea;
/**
 *
 * @author Ben Adams
 */
public class Printer {
    public static void printToPrinter(JTextArea toPrint)
    {
	    JTextArea printData = toPrint;
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(new OutputPrinter(printData));
	    boolean doPrint = job.printDialog();
	    if (doPrint)
	    { 
	        try 
	        {
	            job.print();
	        }
	        catch (PrinterException e)
	        {
	            System.out.println("No printer found!");
	        }
	    }
    }
    public static void printToPrinter(String toPrint)
    {
	    String printData = toPrint;
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(new OutputPrinter(printData));
	    boolean doPrint = job.printDialog();
	    if (doPrint)
	    { 
	        try 
	        {
	            job.print();
	        }
	        catch (PrinterException e)
	        {
	            System.out.println("No printer found!");
	        }
	    }
    }
    
}

