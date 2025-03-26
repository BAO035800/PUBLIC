import java.awt.*;
import java.awt.print.*;

public class Test implements Printable {
    private String invoiceText;

    public Test(String text) {
        this.invoiceText = text;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g.drawString(invoiceText, 100, 100);
        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        String invoice = "===== HÓA ĐƠN =====\n" +
                         "Món ăn: Cơm Gà\n" +
                         "Số lượng: 2\n" +
                         "Tổng tiền: 150,000 VND\n" +
                         "===================\n";
        
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Test(invoice));
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
