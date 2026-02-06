package com.jfs.operations.lite.controller.web.data;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.product.TableProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/web/app/table/product")
@RequiredArgsConstructor
public class TableProductController {
	private final RestClient restClient;
	private final HttpServletRequest httpServletRequest;

	@GetMapping
	public String tableProduct(Model model) {
		List<TableProductDto> tableProductDtos = (List<TableProductDto>) restClient.getSync("/api/product/table", List.class, httpServletRequest);
		model.addAttribute("tableProducts", tableProductDtos);
		return "table-product-form";
	}
}
