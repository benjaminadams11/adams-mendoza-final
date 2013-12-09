/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;
import java.util.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JTextArea;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import javax.imageio.ImageIO;
import java.io.File;


/**
 *
 * @author Ben Adams
 */
public class OutputPrinter implements Printable {
    private String printData;
    private JTextArea printTextArea;
    private int reciptNum;
    private BufferedImage barcode, logo;

	public OutputPrinter(String printDataIn) {
		this.printData = printDataIn;
	}
        
        public OutputPrinter(JTextArea textAreaIn) {
		this.printTextArea = textAreaIn;
                try{
                    logo = ImageIO.read(new File("C:\\Users\\Ben Adams\\Documents\\Fall 2013\\CSC 130\\Temp\\logo.jpg"));
                } catch (IOException ex){
                    ex.printStackTrace();
                }
                Random r = new Random();
                reciptNum = r.nextInt(8999)+ 1000;
                String rN = ""+ reciptNum;
                MyBarCodeUtility barCodeUtil = new MyBarCodeUtility();
                barCodeUtil.createBarCode128(rN);
                try{
                    barcode = ImageIO.read(new File("C:\\Users\\Ben Adams\\Documents\\Fall 2013\\CSC 130\\Temp\\" + reciptNum +".jpg"));
                } catch (IOException ex){
                    ex.printStackTrace();
                }
	}
        
	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
	{
	    // Should only have one page, and page # is zero-based.
	    if (page > 0)
	    {
	        return NO_SUCH_PAGE;
	    }
            //trial
            Font font = new Font("Courier New", Font.PLAIN, 12);
            Font title = new Font("Courier New", Font.BOLD, 14);
            Font small = new Font("Courier New", Font.PLAIN, 10);
            Font bold = new Font("Courier New", Font.BOLD, 12);
            Font smallBold = new Font("Courier New", Font.BOLD, 10);
            

	    // Adding the "Imageable" to the x and y puts the margins on the page.
	    // To make it safe for printing.
	    Graphics2D g2d = (Graphics2D)g;
	    int x = (int) pf.getImageableX();
	    int y = (int) pf.getImageableY();        
	    g2d.translate(x, y); 

	    // Calculate the line height
	    
	    FontMetrics metrics = g.getFontMetrics(font);
	    int lineHeight = metrics.getHeight();
            FontMetrics titleMetrics = g.getFontMetrics(title);
            int titleHeight = titleMetrics.getHeight();
            FontMetrics smallMetrics = g.getFontMetrics(small);
            int smallHeight = smallMetrics.getHeight();
            FontMetrics smallBMetrics = g.getFontMetrics(smallBold);
            int smallBHeight = smallBMetrics.getHeight();
            FontMetrics boldMetrics = g.getFontMetrics(bold);
            int boldHeight = boldMetrics.getHeight();
            
            g2d.setFont(font);
            
            

	    BufferedReader br = new BufferedReader(new StringReader(printTextArea.getText()));

	    // Draw the page:
	    try
	    {
	        String line;
                y+=5;
                g2d.drawImage(logo, x+135, y, null);
	        // Just a safety net in case no margin was added.
	        x += 50;
	        y += 55;
                //title
                g2d.setFont(title);
                line=br.readLine();
                g2d.drawString(line,x,y);
                y+=titleHeight;
                //Location/Date/Time
                for(int i = 0; i <2; i++)
                {
                    g2d.setFont(small);
                    line=br.readLine();
                    g2d.drawString(line,x,y);
                    y+=smallHeight;
                }
                //Contact
                g2d.setFont(smallBold);
                line=br.readLine();
                g2d.drawString(line,x,y);
                
                g2d.setFont(font);
	        while ((line = br.readLine()) != null)
	        {
                    g2d.setFont(bold);
                    if(line.charAt(0)== '-')
                    {
                        g2d.setFont(font);
                    }
	            y += lineHeight;
	            g2d.drawString(line, x, y);
	        }
                g2d.drawString("Recipt: "+ reciptNum, x, y+ lineHeight);
                g2d.drawImage(barcode, x+30, y+ lineHeight*2, null);
	    }
	    catch (IOException e)
	    {
	        // 
	    }

	    return PAGE_EXISTS;
	}
    
}
