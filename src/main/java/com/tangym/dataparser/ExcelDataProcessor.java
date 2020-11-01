package com.tangym.dataparser;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author backtym@live.cn
 */
public class ExcelDataProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ExcelDataProcessor.class);

    private final String fileName;
    private final String sheetName;

    public ExcelDataProcessor(String fileName, String sheetName) {
        super(); // 调用构造函数 public ExcelData()
        this.fileName = fileName;
        this.sheetName = sheetName;
    }

    public List<Map<String, String>> readExcelWithTitle(String project, String env) throws IOException {
        String filepath = getPath(project, env);
        List<Map<String, String>> result = new ArrayList<>();
        try (FileInputStream is = new FileInputStream(filepath)) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
            if (xssfSheet == null) {
                return result;
            }
            Map<Integer, String> title = new HashMap<>(16);
            for (int rowNum = 0; rowNum < xssfSheet.getLastRowNum() + 1; rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                int minColIx = xssfRow.getFirstCellNum();
                int maxColIx = xssfRow.getLastCellNum();
                Map<String, String> rowList = new HashMap<>(16);
                // 处理一行中每个表格cell
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    XSSFCell cell = xssfRow.getCell(colIx);
                    if (cell == null || "".equals(cell.toString())) {
                        continue;
                    }
                    CellType cellType = cell.getCellType();
                    // 处理第一行的标题
                    if (rowNum == 0) {
                        title.put(colIx, cell.toString());
                    } else {
                        String cellValue = cell.toString();
                        // 如果是数值型转换为字符串型
                        if (cellType == CellType.NUMERIC) {
                            cell.setCellType(CellType.STRING);
                            cellValue = String.valueOf(cell.getRichStringCellValue());
                        }
                        if ("\"\"".equals(cellValue)) {
                            rowList.put(title.get(colIx), "");
                        } else {
                            rowList.put(title.get(colIx), cellValue);
                        }
                    }
                }
                if (rowList.size() != 0) {
                    result.add(rowList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result.isEmpty()) {
            logger.warn("没有可执行的测试用例");
            System.exit(1);
        }
        return result;
    }

    public String getPath(String project, String env) throws IOException {
        File directory = new File(".");
        return directory.getCanonicalPath() + "/src/test/resources/" + project + "/" + env + "/data/" + fileName + ".xlsx";
    }
}
