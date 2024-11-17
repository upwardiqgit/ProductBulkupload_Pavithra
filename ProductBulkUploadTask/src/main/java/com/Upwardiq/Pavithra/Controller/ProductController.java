package com.Upwardiq.Pavithra.Controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Upwardiq.Pavithra.Model.Product;
import com.Upwardiq.Pavithra.Service.ProductService;


import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getUploadProductPage")
    public String getUploadPage() {
        return "Uploadfile";
    }

    @GetMapping("/getSerchpage")
    public String getSerchpage(Model model) {
//    	productService.
    	return "Searchpage";
    }
    @PostMapping("/loadFile")
    @ResponseBody
    public List<Product> loadFile(@RequestParam("file") MultipartFile file) {
        List<Product> products = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                Product product = new Product();
                product.setProductid((long) row.getCell(0).getNumericCellValue());
                product.setProductname(row.getCell(1).getStringCellValue());
                product.setProducttype(row.getCell(2).getStringCellValue());
                product.setProductprice(String.valueOf(row.getCell(3).getNumericCellValue()));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products; // Return the list of products as JSON
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<Product> products = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                Product product = new Product();
                product.setProductid((long) row.getCell(0).getNumericCellValue());
                product.setProductname(row.getCell(1).getStringCellValue());
                product.setProducttype(row.getCell(2).getStringCellValue());
                product.setProductprice(String.valueOf(row.getCell(3).getNumericCellValue()));
                products.add(product);
            }

            productService.saveAll(products); // Save all products
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload the file.";
        }
        return "File uploaded successfully!";
    }
}
