package com.youtube.ecommerce.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OrderExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CheckoutCart> listOrder;

    public OrderExcel(List<CheckoutCart> listOrder1) {
        this.listOrder = listOrder1;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Daftar Penjualan");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Kode Pesanan", style);
        createCell(row, 1, "Order ID", style);
        createCell(row, 2, "Metode Pembelian", style);
        createCell(row, 3, "Alamat", style);
        createCell(row, 4, "Jumlah", style);
        createCell(row, 5, "Harga", style);
        createCell(row, 6, "Tanggal", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else if (value instanceof Timestamp) {
            cell.setCellValue((Timestamp) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (CheckoutCart cc : listOrder) {
            Row row = sheet.createRow(rowCount);
            int columnCount = 0;
            createCell(row, columnCount++, cc.getId(), style);
            createCell(row, columnCount++, cc.getOrder_id(), style);
            createCell(row, columnCount++, cc.getPayment_type(), style);
            createCell(row, columnCount++, cc.getDelivery_address(), style);
            createCell(row, columnCount++, cc.getQty(), style);
            createCell(row, columnCount++, cc.getPrice(), style);
            createCell(row, columnCount++, cc.getCreatedAt(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
